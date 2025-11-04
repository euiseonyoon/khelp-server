package com.luke.kHelperServer.domain.account

import jakarta.persistence.Embeddable

@Embeddable
class Email(
    val address: String
) {
    init {
        if (!EMAIL_REGEX.matches(address)) {
            throw IllegalArgumentException(address)
        }
    }

    override fun toString(): String = address

    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        if (other !is Email) return false
        return other.address == this.address
    }

    override fun hashCode(): Int = address.hashCode()
}
