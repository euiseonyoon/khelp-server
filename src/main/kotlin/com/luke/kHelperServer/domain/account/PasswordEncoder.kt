package com.luke.kHelperServer.domain.account

interface PasswordEncoder {
    fun encode(rawPassword: String): PasswordHash

    fun matches(rawPassword: String, passwordHash: PasswordHash): Boolean
}
