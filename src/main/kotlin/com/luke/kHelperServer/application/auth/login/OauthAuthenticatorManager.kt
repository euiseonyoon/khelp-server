package com.luke.kHelperServer.application.auth.login

import com.luke.kHelperServer.application.auth.login.required_port.OauthAuthenticator
import com.luke.kHelperServer.domain.account.OauthVendor

interface OauthAuthenticatorManager {
    fun getAuthenticator(oauthVendor: OauthVendor): OauthAuthenticator
}
