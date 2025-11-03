package com.luke.kHelperServer.application.auto_register.required_port

import com.luke.kHelperServer.domain.authority.Role
import com.luke.kHelperServer.domain.authority.write.Authority

interface AuthorityCommandRepository {
    fun findByRole(role: Role): Authority?
}
