package com.luke.kHelperServer.domain.account.write

import org.springframework.data.jpa.repository.JpaRepository

interface AccountJpaRepository: JpaRepository<Account, Long> {
}
