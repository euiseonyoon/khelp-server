package com.luke.kHelperServer.application.auth.auto_register.required_port

interface PasswordGenerator {
    fun generatePassword(length: Int? = null): String
}
