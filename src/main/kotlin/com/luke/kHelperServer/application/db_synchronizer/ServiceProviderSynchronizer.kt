package com.luke.kHelperServer.application.db_synchronizer

import com.luke.kHelperServer.application.db_synchronizer.provided_port.EntitySynchronizer
import com.luke.kHelperServer.application.db_synchronizer.required_port.DocumentRepository
import com.luke.kHelperServer.domain.EventType
import com.luke.kHelperServer.domain.WriteDbCommitedEvent
import com.luke.kHelperServer.domain.provider_language_skill.read.ProviderLanguageSkillDocument
import com.luke.kHelperServer.domain.service_provider.event.ServiceProviderEvent
import com.luke.kHelperServer.domain.service_provider.read.ServiceProviderDocument
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.Date


@Component
class ServiceProviderSynchronizer(
    private val serviceProviderDocumentRepository: DocumentRepository<ServiceProviderDocument>
) : EntitySynchronizer<ServiceProviderEvent> {

    private val logger = LoggerFactory.getLogger(ServiceProviderSynchronizer::class.java)

    override fun getHandlingMessageType() = ServiceProviderEvent::class

    override fun synchronizeReadDb(event: WriteDbCommitedEvent) {
        val convertedEvent = event as ServiceProviderEvent
        when(convertedEvent.eventType) {
            EventType.CREATED -> handleCreated(convertedEvent)
            EventType.UPDATED -> handlerUpdated(convertedEvent)
            EventType.DELETED -> handleDeleted(convertedEvent)
        }
    }

    private fun handleCreated(event: ServiceProviderEvent) {
        val serviceProvider = event.serviceProvider
        logger.info("Syncing ServiceProvider created: id={}", serviceProvider.id)

        val languageSkills = serviceProvider.providerLanguageSkills.map { skill ->
            ProviderLanguageSkillDocument(
                languageId = skill.supportingLanguage.id,
                languageName = skill.supportingLanguage.language.name,
                level = skill.level
            )
        }

        val document = ServiceProviderDocument(
            serviceProviderId = serviceProvider.id,
            accountId = serviceProvider.accountId,
            description = serviceProvider.description,
            approved = serviceProvider.approved,
            createdAt = Date.from(serviceProvider.createdAt.toInstant()),
            updatedAt = Date.from(serviceProvider.updatedAt.toInstant()),
            languageSkills = languageSkills
        )
        serviceProviderDocumentRepository.save(document)
        logger.info("ServiceProvider synced to MongoDB: serviceProviderId={}, languageSkills count={}",
            serviceProvider.id, languageSkills.size)
    }

    private fun handlerUpdated(event: ServiceProviderEvent) {
        val serviceProvider = event.serviceProvider
        logger.info("Syncing ServiceProvider updated: id={}", serviceProvider.id)

        val existing = serviceProviderDocumentRepository.findByWriteEntityId(serviceProvider.id)

        val languageSkills = serviceProvider.providerLanguageSkills.map { skill ->
            ProviderLanguageSkillDocument(
                languageId = skill.supportingLanguage.id,
                languageName = skill.supportingLanguage.language.name,
                level = skill.level
            )
        }

        val document = ServiceProviderDocument(
            id = existing?.id, // MongoDB _id 유지 (업데이트)
            serviceProviderId = serviceProvider.id,
            accountId = serviceProvider.accountId,
            description = serviceProvider.description,
            approved = serviceProvider.approved,
            createdAt = Date.from(serviceProvider.createdAt.toInstant()),
            updatedAt = Date.from(serviceProvider.updatedAt.toInstant()),
            languageSkills = languageSkills
        )
        serviceProviderDocumentRepository.save(document)
        logger.info("ServiceProvider updated in MongoDB: serviceProviderId={}, languageSkills count={}",
            serviceProvider.id, languageSkills.size)
    }

    private fun handleDeleted(event: ServiceProviderEvent) {
        return serviceProviderDocumentRepository.deleteByWriteEntityId(event.serviceProvider.id)
    }
}
