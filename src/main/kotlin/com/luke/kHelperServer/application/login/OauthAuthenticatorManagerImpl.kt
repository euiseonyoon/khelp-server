package com.luke.kHelperServer.application.login

import com.luke.kHelperServer.application.login.required_port.OauthAuthenticator
import com.luke.kHelperServer.domain.account.OauthVendor
import com.luke.kHelperServer.domain.account.excpetions.OauthAuthenticatorNotFoundException
import org.springframework.stereotype.Component

@Component
class OauthAuthenticatorManagerImpl(
    oauthAuthenticator: List<OauthAuthenticator>
): OauthAuthenticatorManager{
    private val authenticatorMap: Map<OauthVendor, OauthAuthenticator> = oauthAuthenticator.associateBy { it.provider }

    override fun getAuthenticator(oauthVendor: OauthVendor): OauthAuthenticator {
        val authenticator = authenticatorMap[oauthVendor] ?: throw OauthAuthenticatorNotFoundException(oauthVendor)
        return authenticator
    }
}
