package com.luke.kHelperServer.adapter.secondary.db.authority

import com.luke.kHelperServer.domain.authority.Role
import com.luke.kHelperServer.domain.authority.write.Authority
import org.springframework.data.jpa.repository.JpaRepository

interface AuthorityJpaRepository: JpaRepository<Authority, Long> {
    fun findByRole(role: Role): Authority?
}
