package com.luke.kHelperServer.domain.login

data class EmailLoginRequest(
    val email: String,
    val password: String,
)
