package com.luke.kHelperServer.application.auth.login

import com.luke.kHelperServer.application.account.provided_port.AccountWriter
import com.luke.kHelperServer.application.auth.auto_register.provided_port.AutoRegisterer
import com.luke.kHelperServer.application.auth.jwt.provided_port.JwtTokenService
import com.luke.kHelperServer.application.auth.login.provided_port.CredentialValidator
import com.luke.kHelperServer.domain.account.Email
import com.luke.kHelperServer.domain.account.OauthVendor
import com.luke.kHelperServer.domain.account.PasswordEncoder
import com.luke.kHelperServer.domain.account.write.Account
import com.luke.kHelperServer.domain.exception.BizException
import com.luke.kHelperServer.domain.exception.ErrorMessages
import com.luke.kHelperServer.domain.login.GeneratedTokens
import com.luke.kHelperServer.domain.login.LoginResult
import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated

@Service
class CredentialValidatorImpl(
    private val accountWriter: AccountWriter,
    private val oauthAuthenticatorManager: OauthAuthenticatorManager,
    private val autoRegisterer: AutoRegisterer,
    private val jwtTokenService: JwtTokenService,
    private val passwordEncoder: PasswordEncoder,
): CredentialValidator {
    override fun loginByOauth(
        token: String,
        oauthVendor: OauthVendor
    ): LoginResult {
        val email = oauthAuthenticatorManager.getAuthenticator(oauthVendor).getEmailFromToken(token)
        val accountDto = accountWriter.findByEmail(email) ?: autoRegisterer.autoRegisterAccount(email, oauthVendor)

        return createLoginResult(accountDto.account)
    }

    override fun loginByEmailPassword(email: Email, rawPassword: String): LoginResult {
        val accountDto = accountWriter.findByEmail(email)
            ?: throw BizException(ErrorMessages.ACCOUNT_NOT_FOUND)
        val match = passwordEncoder.matches(rawPassword, accountDto.account.passwordHash)
        if (!match) throw BizException(ErrorMessages.INVALID_CREDENTIAL)

        return createLoginResult(accountDto.account)
    }

    private fun createLoginResult(account: Account): LoginResult {
        return LoginResult(
            account = account,
            generatedTokens = GeneratedTokens(
                accessToken = jwtTokenService.createAccessToken(account),
                refreshToken = jwtTokenService.createRefreshToken(account)
            )
        )
    }
}
