package com.luke.kHelperServer.infrastructure

import com.luke.kHelperServer.application.login.required_port.Hasher
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.security.MessageDigest

@Component
class Sha256Hasher : Hasher{
    override fun hashString(input: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val hashBytes: ByteArray = digest.digest(input.toByteArray(StandardCharsets.UTF_8))
        return hashBytes.joinToString("") { String.format("%02x", it) }
    }
}
