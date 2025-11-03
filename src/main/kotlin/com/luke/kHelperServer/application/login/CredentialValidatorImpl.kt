package com.luke.kHelperServer.application.login

import com.luke.kHelperServer.application.account.provided_port.AccountWriter
import com.luke.kHelperServer.application.auto_register.provided_port.AutoRegisterer
import com.luke.kHelperServer.application.jwt.provided_port.JwtTokenService
import com.luke.kHelperServer.application.login.provided_port.CredentialValidator
import com.luke.kHelperServer.domain.account.OauthVendor
import com.luke.kHelperServer.domain.login.GeneratedTokens
import jakarta.validation.constraints.NotEmpty
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.validation.annotation.Validated

@Service
@Validated
class CredentialValidatorImpl(
    private val accountWriter: AccountWriter,
    private val oauthAuthenticatorManager: OauthAuthenticatorManager,
    private val autoRegisterer: AutoRegisterer,
    private val jwtTokenService: JwtTokenService,
): CredentialValidator {
    @Transactional
    override fun loginByOauth(
        @NotEmpty
        token: String,
        oauthVendor: OauthVendor
    ): GeneratedTokens {
        val email = oauthAuthenticatorManager.getAuthenticator(oauthVendor).getEmailFromToken(token)
        val accountDto = accountWriter.findByEmail(email) ?: autoRegisterer.autoRegisterAccount(email, oauthVendor)

        return GeneratedTokens(
            accessToken = jwtTokenService.createAccessToken(accountDto.account),
            refreshToken = jwtTokenService.createRefreshToken(accountDto.account)
        )
    }
}
