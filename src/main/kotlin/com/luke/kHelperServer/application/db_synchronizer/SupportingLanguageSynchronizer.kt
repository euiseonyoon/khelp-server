package com.luke.kHelperServer.application.db_synchronizer

import com.luke.kHelperServer.application.db_synchronizer.provided_port.EntitySynchronizer
import com.luke.kHelperServer.application.db_synchronizer.required_port.DocumentRepository
import com.luke.kHelperServer.domain.EventType
import com.luke.kHelperServer.domain.WriteDbCommitedEvent
import com.luke.kHelperServer.domain.supporting_language.event.SupportingLanguageEvent
import com.luke.kHelperServer.domain.supporting_language.read.SupportingLanguageDocument
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.*

@Component
class SupportingLanguageSynchronizer(
    private val supportingLanguageDocumentRepository: DocumentRepository<SupportingLanguageDocument>
): EntitySynchronizer<SupportingLanguageEvent> {
    private val logger = LoggerFactory.getLogger(SupportingLanguageSynchronizer::class.java)

    override fun getHandlingMessageType() = SupportingLanguageEvent::class

    override fun synchronizeReadDb(event: WriteDbCommitedEvent) {
        val convertedEvent = event as SupportingLanguageEvent
        when(convertedEvent.eventType) {
            EventType.CREATED -> handleCreated(convertedEvent)
            EventType.UPDATED -> handlerUpdated(convertedEvent)
            EventType.DELETED -> handleDeleted(convertedEvent)
        }
    }

    private fun handleCreated(event: SupportingLanguageEvent) {
        val supportingLanguage = event.supportingLanguage
        logger.info("Syncing SupportingLanguage created: id={}", supportingLanguage.id)

        val document = SupportingLanguageDocument(
            supportingLanguageId = supportingLanguage.id,
            language = supportingLanguage.language.name,
            createdAt = Date.from(supportingLanguage.createdAt.toInstant()),
            updatedAt = Date.from(supportingLanguage.updatedAt.toInstant()),
        )
        supportingLanguageDocumentRepository.save(document)
        logger.info("SupportingLanguage synced to MongoDB: supportingLanguageId={}", supportingLanguage.id)
    }

    private fun handlerUpdated(event: SupportingLanguageEvent) {
        val supportingLanguage = event.supportingLanguage
        logger.info("Syncing SupportingLanguage updated: id={}", supportingLanguage.id)

        val existing = supportingLanguageDocumentRepository.findByWriteEntityId(supportingLanguage.id)

        val document = SupportingLanguageDocument(
            id = existing?.id, // MongoDB _id 유지 (업데이트)
            supportingLanguageId = supportingLanguage.id,
            language = supportingLanguage.language.name,
            createdAt = Date.from(supportingLanguage.createdAt.toInstant()),
            updatedAt = Date.from(supportingLanguage.updatedAt.toInstant()),
        )
        supportingLanguageDocumentRepository.save(document)
        logger.info("SupportingLanguage updated in MongoDB: supportingLanguageId={}", supportingLanguage.id)
    }

    private fun handleDeleted(event: SupportingLanguageEvent) {
        return supportingLanguageDocumentRepository.deleteByWriteEntityId(event.supportingLanguage.id)
    }


}