package com.luke.kHelperServer.domain.account.read

import com.luke.kHelperServer.domain.account.event.AccountCommittedEvent
import com.luke.kHelperServer.domain.account.event.AccountCreatedEvent
import com.luke.kHelperServer.domain.account.event.AccountUpdatedEvent
import com.luke.kHelperServer.domain.authority.read.AuthorityDocument
import com.luke.kHelperServer.domain.db_sync.EntitySyncHandler
import com.luke.kHelperServer.domain.WriteDbCommitedEvent
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.Date


@Component
class AccountSyncHandler(
    private val accountMongoRepository: AccountMongoRepository
) : EntitySyncHandler<AccountCommittedEvent> {

    private val logger = LoggerFactory.getLogger(AccountSyncHandler::class.java)

    override fun getHandlingMessageType() = AccountCommittedEvent::class

    override fun handle(event: WriteDbCommitedEvent) {
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
            passwordHash = account.passwordHash,
            authority = AuthorityDocument.fromAuthority(account.authority),
            nickname = account.nickname,
            enabled = account.enabled,
            oauthVendor = account.oauth?.name,
            createdAt = Date.from(account.createdAt.toInstant()),
            updatedAt = Date.from(account.updatedAt.toInstant())
        )

        accountMongoRepository.save(document)
        logger.info("Account synced to MongoDB: accountId={}", account.id)
    }

    private fun handleAccountUpdated(event: AccountUpdatedEvent) {
        val account = event.account
        logger.info("Syncing Account updated: id={}, email={}", account.id, account.email)

        val existing = accountMongoRepository.findByAccountId(account.id)

        val document = AccountDocument(
            id = existing?.id, // MongoDB _id 유지 (업데이트)
            accountId = account.id,
            email = account.email.address,
            passwordHash = account.passwordHash,
            authority = AuthorityDocument.fromAuthority(account.authority),
            nickname = account.nickname,
            enabled = account.enabled,
            oauthVendor = account.oauth?.name,
            createdAt = Date.from(account.createdAt.toInstant()),
            updatedAt = Date.from(account.updatedAt.toInstant()),
        )

        accountMongoRepository.save(document)
        logger.info("Account updated in MongoDB: accountId={}", account.id)
    }
}
