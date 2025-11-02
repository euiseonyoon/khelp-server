package com.luke.kHelperServer.domain.account

import jakarta.persistence.Embeddable

@Embeddable
class PasswordHash(
    val hash: String
) {
    override fun toString(): String = hash
}
