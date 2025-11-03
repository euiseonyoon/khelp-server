package com.luke.kHelperServer.application.auto_register.required_port

interface PasswordGenerator {
    fun generatePassword(length: Int? = null): String
}
