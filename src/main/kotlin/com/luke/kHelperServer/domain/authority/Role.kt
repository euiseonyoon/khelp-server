package com.luke.kHelperServer.domain.authority

import jakarta.persistence.Embeddable

@Embeddable
class Role(
    val name: String
) {
    init {
        if (!name.startsWith(ROLE_PREFIX)) {
            throw IllegalArgumentException("${name}은 잘못된 권한 이름입니다.")
        }
    }

    override fun toString(): String = name

    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        if (other !is Role) return false
        return other.name == this.name
    }

    override fun hashCode(): Int = name.hashCode()
}
