package com.luke.kHelperServer.domain.login

data class OauthLoginRequest(
    val token: String,
    val oauthVendor: String,
)
