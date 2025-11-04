package com.luke.kHelperServer.adapter.secondary.db.account

import com.luke.kHelperServer.domain.account.Email
import com.luke.kHelperServer.domain.account.write.Account
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository

interface AccountJpaRepository: JpaRepository<Account, Long> {
    fun existsByEmail(email: Email): Boolean

    @EntityGraph(attributePaths = ["authority"])
    fun findByEmail(email: Email): Account?

    @EntityGraph(attributePaths = ["authority"])
    fun findAccountById(accountId: Long): Account?
}
