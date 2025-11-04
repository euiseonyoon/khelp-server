package com.luke.kHelperServer.application.auth.login

import com.luke.kHelperServer.application.auth.login.provided_port.CredentialValidator
import com.luke.kHelperServer.application.auth.login.provided_port.LoginService
import com.luke.kHelperServer.application.auth.refresh_token_security.provided_port.RefreshTokenHandler
import com.luke.kHelperServer.domain.account.Email
import com.luke.kHelperServer.domain.account.OauthVendor
import com.luke.kHelperServer.domain.login.GeneratedTokens
import com.luke.kHelperServer.domain.login.LoginResult
import com.luke.kHelperServer.domain.login.exception.LoginFailedException
import jakarta.validation.constraints.NotEmpty
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.validation.annotation.Validated

@Service
@Validated
class LoginServiceImpl(
    private val credentialValidator: CredentialValidator,
    private val refreshTokenHandler: RefreshTokenHandler,
): LoginService {

    @Transactional
    override fun loginByOauth(
        @NotEmpty
        token: String,
        oauthVendor: OauthVendor,
    ): GeneratedTokens {
        try {
            val loginResult = credentialValidator.loginByOauth(token, oauthVendor)
            return getTokensAndSaveRefreshToken(loginResult)
        } catch (e: Exception) {
            throw LoginFailedException("로그인 실패")
        }
    }

    @Transactional(readOnly = true)
    override fun loginByEmailAndPassword(
        email: Email,
        @NotEmpty
        rawPassword: String,
    ): GeneratedTokens {
        try {
            val loginResult = credentialValidator.loginByEmailPassword(email, rawPassword)
            return getTokensAndSaveRefreshToken(loginResult)
        } catch (e: Exception) {
            throw LoginFailedException("로그인 실패")
        }
    }

    private fun getTokensAndSaveRefreshToken(loginResult: LoginResult): GeneratedTokens {
        refreshTokenHandler.saveRefreshToken(loginResult.account.id, loginResult.generatedTokens.refreshToken)
        return loginResult.generatedTokens
    }
}
