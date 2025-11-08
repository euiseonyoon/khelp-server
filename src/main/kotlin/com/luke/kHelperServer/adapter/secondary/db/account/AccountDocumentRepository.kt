package com.luke.kHelperServer.adapter.secondary.db.account

import com.luke.kHelperServer.application.db_synchronizer.required_port.DocumentRepository
import com.luke.kHelperServer.domain.account.read.AccountDocument
import org.springframework.stereotype.Service
import java.util.*

@Service
class AccountDocumentRepository(
    private val accountMongoRepository: AccountMongoRepository
): DocumentRepository<AccountDocument> {
    override fun save(document: AccountDocument) {
        accountMongoRepository.save(document)
    }

    override fun findByWriteEntityId(writeEntityId: Long): AccountDocument? {
        return accountMongoRepository.findByAccountId(writeEntityId)
    }

    override fun deleteByWriteEntityId(writeEntityId: Long) {
        accountMongoRepository.findByAccountId(writeEntityId)?.let { document ->
            document.enabled = false
            document.updatedAt = Date()
            accountMongoRepository.save(document)
        }
    }
}
