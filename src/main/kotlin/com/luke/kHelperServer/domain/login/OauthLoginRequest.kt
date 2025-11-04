package com.luke.kHelperServer.domain.login

import com.luke.kHelperServer.domain.account.OauthVendor

data class OauthLoginRequest(
    val token: String,
    val oauthVendor: OauthVendor,
)
