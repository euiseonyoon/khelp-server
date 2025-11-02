package com.luke.kHelperServer.domain.db_sync

import com.luke.kHelperServer.domain.WriteDbCommitedEvent
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Component
class WriteDbEventHandlerImpl(
    private val syncHandlerManager: EntitySyncHandlerManager,
): WriteDbEventHandler {
    private val logger = LoggerFactory.getLogger(WriteDbEventHandlerImpl::class.java)

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    override fun handleWriteDbCommitedEvent(event: WriteDbCommitedEvent) {
        syncHandlerManager.findHandler(event)?.let { syncHandler ->
            syncHandler.handle(event)

            logger.info("Successfully synced Account to Read DB: {}", event::class.simpleName)
        } ?: run { fallBack(event) }
    }

    override fun fallBack(event: WriteDbCommitedEvent) {
        logger.error("Failed to sync Account to Read DB: {}", event::class.simpleName)
    }
}
