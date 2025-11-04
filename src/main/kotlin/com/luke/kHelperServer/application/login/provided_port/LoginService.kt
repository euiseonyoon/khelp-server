package com.luke.kHelperServer.application.login.provided_port

import com.luke.kHelperServer.domain.account.Email
import com.luke.kHelperServer.domain.account.OauthVendor
import com.luke.kHelperServer.domain.login.GeneratedTokens

interface LoginService {
    fun loginByOauth(token: String, oauthVendor: OauthVendor): GeneratedTokens

    fun loginByEmailAndPassword(email: Email, rawPassword: String): GeneratedTokens
}

