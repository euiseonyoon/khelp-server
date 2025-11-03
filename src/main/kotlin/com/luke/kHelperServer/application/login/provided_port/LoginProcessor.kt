package com.luke.kHelperServer.application.login.provided_port

import com.luke.kHelperServer.domain.account.OauthVendor
import com.luke.kHelperServer.domain.login.GeneratedTokens

interface LoginProcessor {
    fun loginByOauth(token: String, oauthVendor: OauthVendor): GeneratedTokens
}
