package com.luke.kHelperServer.application.auth.login.required_port

interface Hasher {
    fun hashString(input: String): String
}
