package com.luke.kHelperServer.domain.authority.write

import com.luke.kHelperServer.domain.BaseEntity
import com.luke.kHelperServer.domain.account.write.Account
import com.luke.kHelperServer.domain.authority.Role
import jakarta.persistence.*
import org.hibernate.Hibernate

@Entity
class Authority(
    @Embedded
    @AttributeOverride(
        name = "name",  // Role의 필드명
        column = Column(name = "role", nullable = false, unique = true)  // 원하는 DB 컬럼명
    )
    var role: Role,

    @Column(nullable = false, unique = true)
    var hierarchy: Int

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
        return hierarchy.hashCode()
    }
}
