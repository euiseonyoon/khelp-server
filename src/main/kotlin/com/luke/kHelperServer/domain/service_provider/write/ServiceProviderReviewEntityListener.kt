package com.luke.kHelperServer.domain.service_provider.write

import com.luke.kHelperServer.domain.BaseEntityListener
import com.luke.kHelperServer.domain.EventType
import com.luke.kHelperServer.domain.service_provider.event.ServiceProviderReviewEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component


@Component
class ServiceProviderReviewEntityListener(
    eventPublisher: ApplicationEventPublisher
) : BaseEntityListener<ServiceProviderReview>(eventPublisher){
    override fun createCreatedEvent(entity: ServiceProviderReview): ServiceProviderReviewEvent? {
        return ServiceProviderReviewEvent(EventType.CREATED, entity)
    }

    override fun createUpdatedEvent(entity: ServiceProviderReview): ServiceProviderReviewEvent? {
        return ServiceProviderReviewEvent(EventType.UPDATED, entity)
    }

    override fun createDeletedEvent(entity: ServiceProviderReview): ServiceProviderReviewEvent? {
        return ServiceProviderReviewEvent(EventType.DELETED, entity)
    }
}
