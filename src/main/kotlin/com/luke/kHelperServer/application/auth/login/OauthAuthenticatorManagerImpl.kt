package com.luke.kHelperServer.application.auth.login

import com.luke.kHelperServer.application.auth.login.required_port.OauthAuthenticator
import com.luke.kHelperServer.domain.account.OauthVendor
import com.luke.kHelperServer.domain.exception.BizException
import com.luke.kHelperServer.domain.exception.ErrorMessages
import org.springframework.stereotype.Component

@Component
class OauthAuthenticatorManagerImpl(
    oauthAuthenticator: List<OauthAuthenticator>
): OauthAuthenticatorManager {
    private val authenticatorMap: Map<OauthVendor, OauthAuthenticator> = oauthAuthenticator.associateBy { it.provider }

    override fun getAuthenticator(oauthVendor: OauthVendor): OauthAuthenticator {
        val authenticator = authenticatorMap[oauthVendor] ?: throw BizException(ErrorMessages.OAUTH_VENDOR_NOT_FOUND)
        return authenticator
    }
}
