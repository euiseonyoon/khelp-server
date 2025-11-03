package com.luke.kHelperServer.infrastructure

interface PasswordGenerator {
    fun generatePassword(length: Int? = null): String
}
