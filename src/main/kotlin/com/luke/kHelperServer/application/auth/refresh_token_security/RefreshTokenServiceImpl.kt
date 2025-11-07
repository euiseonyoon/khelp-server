package com.luke.kHelperServer.application.auth.refresh_token_security

import com.luke.kHelperServer.application.account.provided_port.AccountWriter
import com.luke.kHelperServer.application.auth.jwt.provided_port.JwtTokenService
import com.luke.kHelperServer.application.auth.login.required_port.Hasher
import com.luke.kHelperServer.application.auth.refresh_token_security.provided_port.RefreshTokenRepoHandler
import com.luke.kHelperServer.application.auth.refresh_token_security.provided_port.RefreshTokenService
import com.luke.kHelperServer.domain.exception.BizException
import com.luke.kHelperServer.domain.exception.ErrorMessages
import com.luke.kHelperServer.domain.login.AccessToken
import com.luke.kHelperServer.domain.login.GeneratedTokens
import com.luke.kHelperServer.domain.login.HashedRefreshToken
import com.luke.kHelperServer.domain.login.RefreshToken
import com.luke.kHelperServer.infrastructure.RefreshTokenCookieHelper
import io.jsonwebtoken.JwtException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Service

@Service
class RefreshTokenServiceImpl(
    private val refreshTokenCookieHelper: RefreshTokenCookieHelper,
    private val jwtTokenService: JwtTokenService,
    private val refreshTokenRepoHandler: RefreshTokenRepoHandler,
    private val hasher: Hasher,
    private val accountWriter: AccountWriter,
) : RefreshTokenService {
    override fun executeTokenRotation(request: HttpServletRequest, response: HttpServletResponse): AccessToken {
        val refreshToken = getRefreshTokenFromCookie(request)
        val accountId = getAccountIdFromFreshToken(refreshToken, response)
        val hashedRefreshToken = getHashedRefreshTokenFromRepo(accountId, response)

        validateRefreshToken(refreshToken, hashedRefreshToken, response, accountId)

        // 새롭게 refresh token, access token을 발행하여 보안성을 향상한다. (토큰 로테이션)
        val generatedTokens = generateNewTokens(accountId)

        saveNewRefreshToken(accountId, generatedTokens.refreshToken, response)

        return generatedTokens.accessToken
    }

    private fun getRefreshTokenFromCookie(request: HttpServletRequest): RefreshToken {
        return refreshTokenCookieHelper.extractRefreshTokenFromCookie(request)
            ?: throw BizException(ErrorMessages.REFRESH_TOKEN_ABSENT_FROM_COOKIE)
    }

    private fun getAccountIdFromFreshToken(refreshTokenFromCookie: RefreshToken, response: HttpServletResponse): Long {
        try {
            return jwtTokenService.parseAccountIdFromRefreshToken(refreshTokenFromCookie)
        } catch (e: JwtException) {
            expireRefreshTokenFromCookie(response)
            throw e
        }
    }

    private fun getHashedRefreshTokenFromRepo(accountId: Long, response: HttpServletResponse): HashedRefreshToken {
        val hashedRefreshToken = refreshTokenRepoHandler.findHashedRefreshToken(accountId)

        if (hashedRefreshToken == null) {
            expireRefreshTokenFromCookie(response)

            throw BizException(ErrorMessages.REFRESH_TOKEN_NOT_FOUND_ON_REPOSITORY)
        }

        return hashedRefreshToken
    }

    private fun expireRefreshTokenFromCookie(response: HttpServletResponse) {
        refreshTokenCookieHelper.deleteRefreshTokenFromCookie(response)
    }

    private fun deleteRefreshTokenFromRepo(accountId: Long) {
        refreshTokenRepoHandler.deleteHashedRefreshToken(accountId)
    }

    private fun validateRefreshToken(
        refreshTokenFromCookie: RefreshToken,
        hashedRefreshToken: HashedRefreshToken,
        response: HttpServletResponse,
        accountId: Long,
    ) {
        if (hasher.hashString(refreshTokenFromCookie.token) != hashedRefreshToken.hash) {
            expireRefreshTokenFromCookie(response)
            deleteRefreshTokenFromRepo(accountId)

            throw BizException(ErrorMessages.UNIDENTICAL_REFRESH_TOKEN)
        }
    }

    private fun generateNewTokens(accountId: Long): GeneratedTokens {
        val account = accountWriter.findByAccountId(accountId)?.account
            ?: throw BizException(ErrorMessages.ACCOUNT_NOT_FOUND)

        val refreshToken = jwtTokenService.createRefreshToken(account)
        val accessToken = jwtTokenService.createAccessToken(account)

        return GeneratedTokens(accessToken, refreshToken)
    }

    private fun saveNewRefreshToken(accountId: Long, refreshToken: RefreshToken, response: HttpServletResponse) {
        refreshTokenRepoHandler.saveRefreshToken(accountId, refreshToken)
        refreshTokenCookieHelper.setRefreshTokenOnCookie(response, refreshToken)
    }
}
