package com.luke.kHelperServer.application.db_synchronizer

import com.luke.kHelperServer.application.db_synchronizer.provided_port.EntitySynchronizer
import com.luke.kHelperServer.application.db_synchronizer.required_port.DocumentRepository
import com.luke.kHelperServer.domain.EventType
import com.luke.kHelperServer.domain.WriteDbCommitedEvent
import com.luke.kHelperServer.domain.provider_language_skill.event.ProviderLanguageSkillCommittedEvent
import com.luke.kHelperServer.domain.provider_language_skill.event.ProviderLanguageSkillEvent
import com.luke.kHelperServer.domain.provider_language_skill.read.ProviderLanguageSkillDocument
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.Date


@Component
class ProviderLanguageSkillSynchronizer(
    private val providerLanguageSkillDocumentRepository: DocumentRepository<ProviderLanguageSkillDocument>
) : EntitySynchronizer<ProviderLanguageSkillCommittedEvent> {

    private val logger = LoggerFactory.getLogger(ProviderLanguageSkillSynchronizer::class.java)

    override fun getHandlingMessageType() = ProviderLanguageSkillCommittedEvent::class

    override fun synchronizeReadDb(event: WriteDbCommitedEvent) {
        val convertedEvent = event as ProviderLanguageSkillEvent
        when(convertedEvent.eventType) {
            EventType.CREATED -> handleCreated(convertedEvent)
            EventType.UPDATED -> handlerUpdated(convertedEvent)
            EventType.DELETED -> handleDeleted(convertedEvent)
        }
    }

    private fun handleCreated(event: ProviderLanguageSkillEvent) {
        val providerLanguageSkill = event.providerLanguageSkill
        logger.info("Syncing ProviderLanguageSkill created: id={}", providerLanguageSkill.id)

        val document = ProviderLanguageSkillDocument(
            providerLanguageSkillId = providerLanguageSkill.id,
            serviceProviderId = providerLanguageSkill.serviceProvider.id,
            serviceProviderApproved = providerLanguageSkill.serviceProvider.approved,
            languageId = providerLanguageSkill.supportingLanguage.id,
            languageName = providerLanguageSkill.supportingLanguage.language.name,
            level = providerLanguageSkill.level.name,
            createdAt = Date.from(providerLanguageSkill.createdAt.toInstant()),
            updatedAt = Date.from(providerLanguageSkill.updatedAt.toInstant()),
        )
        providerLanguageSkillDocumentRepository.save(document)
        logger.info("ProviderLanguageSkill synced to MongoDB: id={}", providerLanguageSkill.id)
    }

    private fun handlerUpdated(event: ProviderLanguageSkillEvent) {
        val providerLanguageSkill = event.providerLanguageSkill
        logger.info("Syncing ProviderLanguageSkill updated: id={}", providerLanguageSkill.id)

        val existing = providerLanguageSkillDocumentRepository.findByWriteEntityId(providerLanguageSkill.id)

        val document = ProviderLanguageSkillDocument(
            id = existing?.id, // MongoDB _id 유지 (업데이트)
            providerLanguageSkillId = providerLanguageSkill.id,
            serviceProviderId = providerLanguageSkill.serviceProvider.id,
            serviceProviderApproved = providerLanguageSkill.serviceProvider.approved,
            languageId = providerLanguageSkill.supportingLanguage.id,
            languageName = providerLanguageSkill.supportingLanguage.language.name,
            level = providerLanguageSkill.level.name,
            createdAt = Date.from(providerLanguageSkill.createdAt.toInstant()),
            updatedAt = Date.from(providerLanguageSkill.updatedAt.toInstant()),
        )
        providerLanguageSkillDocumentRepository.save(document)
        logger.info("ProviderLanguageSkill updated in MongoDB: id={}", providerLanguageSkill.id)
    }

    private fun handleDeleted(event: ProviderLanguageSkillEvent) {
        return providerLanguageSkillDocumentRepository.deleteByWriteEntityId(event.providerLanguageSkill.id)
    }
}
