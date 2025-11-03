package com.luke.kHelperServer.application.login.required_port

import com.luke.kHelperServer.domain.account.Email
import com.luke.kHelperServer.domain.account.OauthVendor

interface OauthAuthenticator {
    val provider: OauthVendor

    fun getEmailFromToken(token: String): Email
}
