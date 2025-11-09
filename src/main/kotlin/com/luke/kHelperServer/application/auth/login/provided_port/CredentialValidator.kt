package com.luke.kHelperServer.application.auth.login.provided_port

import com.luke.kHelperServer.domain.account.Email
import com.luke.kHelperServer.domain.account.OauthVendor
import com.luke.kHelperServer.domain.login.LoginResult

interface CredentialValidator {
    fun loginByOauth(token: String, oauthVendor: OauthVendor): LoginResult

    fun loginByEmailPassword(email: Email, rawPassword: String): LoginResult
}
