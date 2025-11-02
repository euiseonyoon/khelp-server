package com.luke.kHelperServer.domain.account.read

import com.luke.kHelperServer.domain.account.Email
import org.springframework.stereotype.Component

@Component
class AccountMongodbQueryRepository(
    private val accountMongoRepository: AccountMongoRepository
) : AccountQueryRepository {

    override fun findById(id: Long): AccountView? {
        return accountMongoRepository.findByAccountId(id)?.toAccountView()
    }

    override fun findByEmail(email: Email): AccountView? {
        return accountMongoRepository.findByEmail(email.address)?.toAccountView()
    }
}
