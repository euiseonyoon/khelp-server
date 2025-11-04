package com.luke.kHelperServer.application.db_synchronizer

import com.luke.kHelperServer.domain.account.event.AccountCommittedEvent
import com.luke.kHelperServer.domain.account.event.AccountCreatedEvent
import com.luke.kHelperServer.domain.account.event.AccountUpdatedEvent
import com.luke.kHelperServer.domain.authority.read.AuthorityDocument
import com.luke.kHelperServer.application.db_synchronizer.provided_port.EntitySynchronizer
import com.luke.kHelperServer.application.db_synchronizer.required_port.DocumentRepository
import com.luke.kHelperServer.domain.WriteDbCommitedEvent
import com.luke.kHelperServer.domain.account.read.AccountDocument
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.Date


@Component
class AccountSynchronizer(
    private val accountDocumentRepository: DocumentRepository<AccountDocument>
) : EntitySynchronizer<AccountCommittedEvent> {

    private val logger = LoggerFactory.getLogger(AccountSynchronizer::class.java)

    override fun getHandlingMessageType() = AccountCommittedEvent::class

    override fun synchronizeReadDb(event: WriteDbCommitedEvent) {
        when (val convertedEvent = event as AccountCommittedEvent) {
            is AccountCreatedEvent -> handleAccountCreated(convertedEvent)
            is AccountUpdatedEvent -> handleAccountUpdated(convertedEvent)
        }
    }

    private fun handleAccountCreated(event: AccountCreatedEvent) {
        val account = event.account
        logger.info("Syncing Account created: id={}, email={}", account.id, account.email)

        val document = AccountDocument(
            accountId = account.id,
            email = account.email.address,
            passwordHash = account.passwordHash.hash,
            authority = AuthorityDocument.fromAuthority(account.authority),
            nickname = account.nickname,
            enabled = account.enabled,
            oauthVendor = account.oauth?.name,
            createdAt = Date.from(account.createdAt.toInstant()),
            updatedAt = Date.from(account.updatedAt.toInstant())
        )

        accountDocumentRepository.save(document)
        logger.info("Account synced to MongoDB: accountId={}", account.id)
    }

    private fun handleAccountUpdated(event: AccountUpdatedEvent) {
        val account = event.account
        logger.info("Syncing Account updated: id={}, email={}", account.id, account.email)

        val existing = accountDocumentRepository.findByWriteEntityId(account.id)

        val document = AccountDocument(
            id = existing?.id, // MongoDB _id 유지 (업데이트)
            accountId = account.id,
            email = account.email.address,
            passwordHash = account.passwordHash.hash,
            authority = AuthorityDocument.fromAuthority(account.authority),
            nickname = account.nickname,
            enabled = account.enabled,
            oauthVendor = account.oauth?.name,
            createdAt = Date.from(account.createdAt.toInstant()),
            updatedAt = Date.from(account.updatedAt.toInstant()),
        )

        accountDocumentRepository.save(document)
        logger.info("Account updated in MongoDB: accountId={}", account.id)
    }
}
