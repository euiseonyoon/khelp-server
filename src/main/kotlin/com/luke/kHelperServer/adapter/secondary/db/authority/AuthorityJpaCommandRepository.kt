package com.luke.kHelperServer.adapter.secondary.db.authority

import com.luke.kHelperServer.application.auth.auto_register.required_port.AuthorityCommandRepository
import com.luke.kHelperServer.domain.authority.Role
import com.luke.kHelperServer.domain.authority.write.Authority
import org.springframework.stereotype.Component

@Component
class AuthorityJpaCommandRepository(
    private val authorityJpaRepository: AuthorityJpaRepository,
): AuthorityCommandRepository {
    override fun findByRole(role: Role): Authority? {
        return authorityJpaRepository.findByRole(role)
    }
}
