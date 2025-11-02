package com.luke.kHelperServer.domain.account.read

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountMongoRepository : MongoRepository<AccountDocument, String> {
    fun findByAccountId(accountId: Long): AccountDocument?
    fun findByEmail(email: String): AccountDocument?
}
