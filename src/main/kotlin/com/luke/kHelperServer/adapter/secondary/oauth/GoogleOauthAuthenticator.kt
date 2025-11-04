package com.luke.kHelperServer.adapter.secondary.oauth

import com.luke.kHelperServer.application.auth.login.required_port.OauthAuthenticator
import com.luke.kHelperServer.domain.account.Email
import com.luke.kHelperServer.domain.account.OauthVendor
import com.luke.kHelperServer.domain.login.LogInFailedException
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
import org.springframework.stereotype.Component

@Component
class GoogleOauthAuthenticator : OauthAuthenticator {
    override val provider = OauthVendor.GOOGLE

    val jwkSetUri = "https://www.googleapis.com/oauth2/v3/certs"
    val decoder = NimbusJwtDecoder.withJwkSetUri(jwkSetUri).build()
    val nameAttributeKey = "email"

    override fun getEmailFromToken(token: String): Email {
        try {
            val jwt = decoder.decode(token)
            val emailAddress = jwt.claims[nameAttributeKey] as String
            return Email(emailAddress)
        } catch (e: Exception) {
            // JWT 검증 실패 (서명 오류, 만료, 유효하지 않은 클레임 등)
            throw LogInFailedException("Invalid ID Token: ${e.message}")
        }
    }
}
