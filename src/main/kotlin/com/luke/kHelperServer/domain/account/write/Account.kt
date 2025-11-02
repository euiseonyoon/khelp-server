package com.luke.kHelperServer.domain.account.write

import com.luke.kHelperServer.domain.BaseEntity
import com.luke.kHelperServer.domain.account.Email
import com.luke.kHelperServer.domain.account.OauthVendor
import com.luke.kHelperServer.domain.authority.write.Authority
import jakarta.persistence.*
import org.hibernate.Hibernate
import org.hibernate.annotations.NaturalId

@Entity
@EntityListeners(AccountEntityListener::class)
class Account(
    @Column(nullable = false, unique = true) @NaturalId @Embedded
    val email: Email,

    @Column(nullable = false)
    var passwordHash: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authority_id", nullable = false)
    var authority: Authority,

    @Column(nullable = false)
    var enabled: Boolean = true,

    @Column(nullable = true)
    var nickname: String? = null,

    @Column(nullable = true) @Enumerated(value = EnumType.STRING)
    val oauth: OauthVendor? = null,
) : BaseEntity() {

    @Id
    @GeneratedValue
    val id: Long = 0

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

