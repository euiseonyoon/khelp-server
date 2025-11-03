package com.luke.kHelperServer.application.login

import com.luke.kHelperServer.application.login.required_port.OauthAuthenticator
import com.luke.kHelperServer.domain.account.OauthVendor

interface OauthAuthenticatorManager {
    fun getAuthenticator(oauthVendor: OauthVendor): OauthAuthenticator
}
