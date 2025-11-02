package com.luke.kHelperServer.domain.account.write

import org.springframework.stereotype.Component

@Component
class AccountJpaCommandRepository(
    private val accountJpaRepository: AccountJpaRepository,
): AccountCommandRepository {
    override fun save(account: Account): Account {
        return accountJpaRepository.save(account)
    }
}
