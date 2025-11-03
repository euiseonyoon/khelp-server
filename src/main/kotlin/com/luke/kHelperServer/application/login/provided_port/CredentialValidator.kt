package com.luke.kHelperServer.application.login.provided_port

import com.luke.kHelperServer.domain.account.OauthVendor
import com.luke.kHelperServer.domain.login.GeneratedTokens

interface CredentialValidator {
    fun loginByOauth(token: String, oauthVendor: OauthVendor): GeneratedTokens
}
