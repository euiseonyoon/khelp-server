package com.luke.kHelperServer.domain.authority.write

import org.springframework.data.jpa.repository.JpaRepository

interface AuthorityRepository: JpaRepository<Authority, Long> {
}
