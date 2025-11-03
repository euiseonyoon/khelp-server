package com.luke.kHelperServer.infrastructure

import com.luke.kHelperServer.domain.account.PasswordEncoder
import com.luke.kHelperServer.domain.account.PasswordHash
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class SafePasswordEncoder: PasswordEncoder {
    private val bCryptPasswordEncoder = BCryptPasswordEncoder()

    override fun encode(rawPassword: String): PasswordHash {
        return PasswordHash(bCryptPasswordEncoder.encode(rawPassword))
    }

    override fun matches(rawPassword: String, passwordHash: PasswordHash): Boolean {
        return bCryptPasswordEncoder.matches(rawPassword, passwordHash.hash)
    }
}
