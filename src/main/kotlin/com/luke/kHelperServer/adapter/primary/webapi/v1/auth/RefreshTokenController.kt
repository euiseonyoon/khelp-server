package com.luke.kHelperServer.adapter.primary.webapi.v1.auth

import com.luke.kHelperServer.adapter.primary.webapi.v1.V1_REFRESH_TOKEN_URL
import com.luke.kHelperServer.application.account.provided_port.AccountWriter
import com.luke.kHelperServer.application.auth.jwt.provided_port.JwtTokenService
import com.luke.kHelperServer.application.auth.login.required_port.Hasher
import com.luke.kHelperServer.application.auth.refresh_token_security.provided_port.RefreshTokenRepoHandler
import com.luke.kHelperServer.common.GlobalResponse
import com.luke.kHelperServer.domain.exception.BizException
import com.luke.kHelperServer.domain.exception.ErrorMessages
import com.luke.kHelperServer.domain.login.GeneratedTokens
import com.luke.kHelperServer.domain.login.HashedRefreshToken
import com.luke.kHelperServer.domain.login.RefreshToken
import com.luke.kHelperServer.domain.login.RefreshTokenResponse
import com.luke.kHelperServer.infrastructure.RefreshTokenCookieHelper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(V1_REFRESH_TOKEN_URL)
class RefreshTokenController(
    private val refreshTokenCookieHelper: RefreshTokenCookieHelper,
    private val jwtTokenService: JwtTokenService,
    private val hasher: Hasher,
    private val refreshTokenRepoHandler: RefreshTokenRepoHandler,
    private val accountWriter: AccountWriter,
) {
    @PostMapping
    fun refreshToken(
        request: HttpServletRequest,
        response: HttpServletResponse,
    ): ResponseEntity<GlobalResponse<RefreshTokenResponse>> {
        val (refreshTokenFromCookie, accountId) = parseGivenInfo(request, response)
        val hashedRefreshToken = refreshTokenRepoHandler.findHashedRefreshToken(accountId)
            ?: throw BizException(ErrorMessages.REFRESH_TOKEN_NOT_FOUND_ON_REPOSITORY)

        validateRefreshToken(refreshTokenFromCookie, hashedRefreshToken, response, accountId)

        // 새롭게 refresh token, access token을 발행하여 보안성을 향상한다. (토큰 로테이션)
        val generatedTokens = generateNewTokens(accountId)
        refreshTokenRepoHandler.saveRefreshToken(accountId, generatedTokens.refreshToken)
        refreshTokenCookieHelper.setRefreshTokenOnCookie(response, generatedTokens.refreshToken)

        return GlobalResponse.createResponse(RefreshTokenResponse(generatedTokens.accessToken.token))
    }

    private fun parseGivenInfo(
        request: HttpServletRequest,
        response: HttpServletResponse,
    ): Pair<RefreshToken, Long> {
        try {
            val refreshTokenFromCookie = refreshTokenCookieHelper.extractRefreshTokenFromCookie(request)
            val accountId = jwtTokenService.parseAccountIdFromRefreshToken(refreshTokenFromCookie)

            return refreshTokenFromCookie to accountId
        } catch (e: BizException) {
            expireRefreshTokenFromCookie(response)
            throw e
        }
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
        try {
            // 여기서 BizException 발생하면 refresh token 만료작업 (쿠키 & 저장소 에서 제거)
            if (hasher.hashString(refreshTokenFromCookie.token) != hashedRefreshToken.hash) {
                throw BizException(ErrorMessages.UNIDENTICAL_REFRESH_TOKEN)
            }
        } catch (e: BizException) {
            expireRefreshTokenFromCookie(response)
            deleteRefreshTokenFromRepo(accountId)
            throw e
        }
    }

    private fun generateNewTokens(accountId: Long): GeneratedTokens {
        val account = accountWriter.findByAccountId(accountId)?.account
            ?: throw BizException(ErrorMessages.ACCOUNT_NOT_FOUND)

        val refreshToken = jwtTokenService.createRefreshToken(account)
        val accessToken = jwtTokenService.createAccessToken(account)

        return GeneratedTokens(accessToken, refreshToken)
    }
}
