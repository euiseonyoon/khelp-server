package com.luke.kHelperServer.application.db_synchronizer

import com.luke.kHelperServer.application.db_synchronizer.provided_port.EntitySynchronizer
import com.luke.kHelperServer.application.db_synchronizer.required_port.DocumentRepository
import com.luke.kHelperServer.domain.EventType
import com.luke.kHelperServer.domain.WriteDbCommitedEvent
import com.luke.kHelperServer.domain.providing_service.event.ProvidingServiceCommittedEvent
import com.luke.kHelperServer.domain.providing_service.event.ProvidingServiceEvent
import com.luke.kHelperServer.domain.providing_service.read.ProvidingServiceDocument
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.Date


@Component
class ProvidingServiceSynchronizer(
    private val providingServiceDocumentRepository: DocumentRepository<ProvidingServiceDocument>
) : EntitySynchronizer<ProvidingServiceCommittedEvent> {

    private val logger = LoggerFactory.getLogger(ProvidingServiceSynchronizer::class.java)

    override fun getHandlingMessageType() = ProvidingServiceCommittedEvent::class

    override fun synchronizeReadDb(event: WriteDbCommitedEvent) {
        val convertedEvent = event as ProvidingServiceEvent
        when(convertedEvent.eventType) {
            EventType.CREATED -> handleCreated(convertedEvent)
            EventType.UPDATED -> handlerUpdated(convertedEvent)
            EventType.DELETED -> handleDeleted(convertedEvent)
        }
    }

    private fun handleCreated(event: ProvidingServiceEvent) {
        val providingService = event.providingService
        logger.info("Syncing ProvidingService created: id={}", providingService.id)

        val document = ProvidingServiceDocument(
            providingServiceId = providingService.id,
            serviceProviderId = providingService.serviceProvider.id,
            description = providingService.description,
            price = providingService.price,
            createdAt = Date.from(providingService.createdAt.toInstant()),
            updatedAt = Date.from(providingService.updatedAt.toInstant()),
        )
        providingServiceDocumentRepository.save(document)
        logger.info("ProvidingService synced to MongoDB: serviceProviderId={}", providingService.id)
    }

    private fun handlerUpdated(event: ProvidingServiceEvent) {
        val providingService = event.providingService
        logger.info("Syncing ProvidingService updated: id={}", providingService.id)

        val existing = providingServiceDocumentRepository.findByWriteEntityId(providingService.id)

        val document = ProvidingServiceDocument(
            id = existing?.id, // MongoDB _id 유지 (업데이트)
            providingServiceId = providingService.id,
            serviceProviderId = providingService.serviceProvider.id,
            description = providingService.description,
            price = providingService.price,
            createdAt = Date.from(providingService.createdAt.toInstant()),
            updatedAt = Date.from(providingService.updatedAt.toInstant()),
        )
        providingServiceDocumentRepository.save(document)
        logger.info("ServiceProvider updated in MongoDB: serviceProviderId={}", providingService.id)
    }

    private fun handleDeleted(event: ProvidingServiceEvent) {
        return providingServiceDocumentRepository.deleteByWriteEntityId(event.providingService.id)
    }
}
