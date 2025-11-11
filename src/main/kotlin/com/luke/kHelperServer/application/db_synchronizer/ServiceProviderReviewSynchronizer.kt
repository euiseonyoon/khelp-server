package com.luke.kHelperServer.application.db_synchronizer

import com.luke.kHelperServer.application.db_synchronizer.provided_port.EntitySynchronizer
import com.luke.kHelperServer.application.db_synchronizer.required_port.DocumentRepository
import com.luke.kHelperServer.domain.EventType
import com.luke.kHelperServer.domain.WriteDbCommitedEvent
import com.luke.kHelperServer.domain.service_provider.event.ServiceProviderReviewEvent
import com.luke.kHelperServer.domain.service_provider.read.ServiceProviderReviewDocument
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.Date


@Component
class ServiceProviderReviewSynchronizer(
    private val reviewDocumentRepository: DocumentRepository<ServiceProviderReviewDocument>
) : EntitySynchronizer<ServiceProviderReviewEvent> {

    private val logger = LoggerFactory.getLogger(ServiceProviderReviewSynchronizer::class.java)

    override fun getHandlingMessageType() = ServiceProviderReviewEvent::class

    override fun synchronizeReadDb(event: WriteDbCommitedEvent) {
        val convertedEvent = event as ServiceProviderReviewEvent
        when(convertedEvent.eventType) {
            EventType.CREATED -> handleCreated(convertedEvent)
            EventType.UPDATED -> handlerUpdated(convertedEvent)
            EventType.DELETED -> handleDeleted(convertedEvent)
        }
    }

    private fun handleCreated(event: ServiceProviderReviewEvent) {
        val serviceProviderReview = event.serviceProviderReview
        logger.info("Syncing ServiceProviderReview created: id={}", serviceProviderReview.id)

        val document = ServiceProviderReviewDocument(
            serviceProviderReviewId = serviceProviderReview.id,
            serviceProviderId = serviceProviderReview.serviceProvider.id,
            review = serviceProviderReview.review,
            reviewerAccountId = serviceProviderReview.reviewerAccountId,
            rating = serviceProviderReview.rating,
            createdAt = Date.from(serviceProviderReview.createdAt.toInstant()),
            updatedAt = Date.from(serviceProviderReview.updatedAt.toInstant()),
        )
        reviewDocumentRepository.save(document)
        logger.info("ServiceProviderReview synced to MongoDB: serviceProviderId={}", serviceProviderReview.id)
    }

    private fun handlerUpdated(event: ServiceProviderReviewEvent) {
        val serviceProviderReview = event.serviceProviderReview
        logger.info("Syncing ServiceProviderReview updated: id={}", serviceProviderReview.id)

        val existing = reviewDocumentRepository.findByWriteEntityId(serviceProviderReview.id)

        val document = ServiceProviderReviewDocument(
            id = existing?.id, // MongoDB _id 유지 (업데이트)
            serviceProviderReviewId = serviceProviderReview.id,
            serviceProviderId = serviceProviderReview.serviceProvider.id,
            review = serviceProviderReview.review,
            reviewerAccountId = serviceProviderReview.reviewerAccountId,
            rating = serviceProviderReview.rating,
            createdAt = Date.from(serviceProviderReview.createdAt.toInstant()),
            updatedAt = Date.from(serviceProviderReview.updatedAt.toInstant()),
        )
        reviewDocumentRepository.save(document)
        logger.info("ServiceProviderReview updated in MongoDB: serviceProviderId={}", serviceProviderReview.id)
    }

    private fun handleDeleted(event: ServiceProviderReviewEvent) {
        return reviewDocumentRepository.deleteByWriteEntityId(event.serviceProviderReview.id)
    }
}
