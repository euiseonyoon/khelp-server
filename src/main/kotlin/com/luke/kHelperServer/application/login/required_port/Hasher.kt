package com.luke.kHelperServer.application.login.required_port

interface Hasher {
    fun hashString(input: String): String
}
