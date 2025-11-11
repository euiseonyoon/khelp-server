package com.luke.kHelperServer.domain.authority.write

import com.luke.kHelperServer.domain.BaseEntity
import com.luke.kHelperServer.domain.EventType
import com.luke.kHelperServer.domain.account.write.Account
import com.luke.kHelperServer.domain.authority.Role
import com.luke.kHelperServer.domain.authority.event.AuthorityEvent
import jakarta.persistence.*
import org.hibernate.Hibernate

@Entity
class Authority(
    role: Role,
    hierarchy: Int
) : BaseEntity() {
    init {
        this.registerEvent(AuthorityEvent(EventType.CREATED, this))
    }

    @Id @GeneratedValue
    val id: Long = 0

    @Embedded
    @AttributeOverride(
        name = "name",  // Role의 필드명
        column = Column(name = "role", nullable = false, unique = true)  // 원하는 DB 컬럼명
    )
    var role: Role = role
        private set

    @Column(nullable = false, unique = true)
    var hierarchy: Int = hierarchy
        private set

    fun updateRole(role: Role): Authority {
        this.registerEvent(AuthorityEvent(EventType.UPDATED, this))
        this.role = role
        return this
    }

    fun updateHierarchy(hierarchy: Int): Authority {
        this.registerEvent(AuthorityEvent(EventType.UPDATED, this))
        this.hierarchy = hierarchy
        return this
    }

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
