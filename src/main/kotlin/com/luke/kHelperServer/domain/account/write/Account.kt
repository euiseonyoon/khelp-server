package com.luke.kHelperServer.domain.account.write

import com.luke.kHelperServer.domain.BaseEntity
import com.luke.kHelperServer.domain.EventType
import com.luke.kHelperServer.domain.account.Email
import com.luke.kHelperServer.domain.account.OauthVendor
import com.luke.kHelperServer.domain.account.PasswordEncoder
import com.luke.kHelperServer.domain.account.PasswordHash
import com.luke.kHelperServer.domain.account.event.AccountEvent
import com.luke.kHelperServer.domain.authority.write.Authority
import jakarta.persistence.*
import org.hibernate.Hibernate
import org.hibernate.annotations.NaturalId

@Entity
class Account(
    email: Email,
    passwordHash: PasswordHash,
    authority: Authority,
    enabled: Boolean = true,
    nickname: String? = null,
    oauth: OauthVendor? = null,
) : BaseEntity() {
    @Id @GeneratedValue
    val id: Long = 0

    @NaturalId @Embedded
    @AttributeOverride(
        name = "address",
        column = Column(name = "email", nullable = false, unique = true)
    )
    val email: Email = email

    @Embedded
    @AttributeOverride(
        name = "hash",
        column = Column(name = "password_hash", nullable = false)
    )
    var passwordHash: PasswordHash = passwordHash
        private set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authority_id", nullable = false)
    var authority: Authority = authority
        private set

    @Column(nullable = false)
    var enabled: Boolean = enabled
        private set

    @Column(nullable = true)
    var nickname: String? = nickname
        private set

    @Column(nullable = true) @Enumerated(value = EnumType.STRING)
    val oauth: OauthVendor? = oauth

    init {
        this.registerEvent(AccountEvent(EventType.CREATED, this))
    }

    fun updatePassword(passwordEncoder: PasswordEncoder, newRawPassword: String): Account {
        this.registerEvent(AccountEvent(EventType.UPDATED, this))
        this.passwordHash = passwordEncoder.encode(newRawPassword)
        return this
    }

    fun updateNickname(newNickname: String): Account {
        this.registerEvent(AccountEvent(EventType.UPDATED, this))
        this.nickname = newNickname
        return this
    }

    fun updateAuthority(authority: Authority) : Account {
        this.registerEvent(AccountEvent(EventType.UPDATED, this))
        this.authority = authority
        return this
    }

    fun updateEnabled(enabled: Boolean) : Account {
        this.registerEvent(AccountEvent(EventType.UPDATED, this))
        this.enabled = enabled
        return this
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        val account = other as Account
        return id != 0L && id == account.id
    }

    override fun hashCode(): Int {
        return email.address.hashCode()
    }
}

