package com.luke.kHelperServer.adapter.secondary.db.account

import com.luke.kHelperServer.application.account.required_port.AccountCommandRepository
import com.luke.kHelperServer.domain.account.Email
import com.luke.kHelperServer.domain.account.write.Account
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class AccountJpaCommandRepository(
    private val accountJpaRepository: AccountJpaRepository,
): AccountCommandRepository {
    @Transactional
    override fun save(account: Account): Account {
        return accountJpaRepository.save(account)
    }

    @Transactional(readOnly = true)
    override fun existsByEmail(email: Email): Boolean {
        return accountJpaRepository.existsByEmail(email)
    }

    override fun existsByAccountId(accountId: Long): Boolean {
        return accountJpaRepository.existsById(accountId)
    }

    @Transactional(readOnly = true)
    override fun findByEmail(email: Email): Account? {
        return accountJpaRepository.findByEmail(email)
    }

    @Transactional(readOnly = true)
    override fun findByAccountId(accountId: Long): Account? {
        return accountJpaRepository.findAccountById(accountId)
    }
}
