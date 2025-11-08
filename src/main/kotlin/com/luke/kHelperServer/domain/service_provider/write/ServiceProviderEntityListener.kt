package com.luke.kHelperServer.domain.service_provider.write

import com.luke.kHelperServer.domain.BaseEntityListener
import com.luke.kHelperServer.domain.EventType
import com.luke.kHelperServer.domain.service_provider.event.ServiceProviderEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class ServiceProviderEntityListener(
    eventPublisher: ApplicationEventPublisher
) : BaseEntityListener<ServiceProvider>(eventPublisher){
    override fun createCreatedEvent(entity: ServiceProvider): ServiceProviderEvent? {
        return ServiceProviderEvent(EventType.CREATED, entity)
    }

    override fun createUpdatedEvent(entity: ServiceProvider): ServiceProviderEvent? {
        return ServiceProviderEvent(EventType.UPDATED, entity)
    }

    override fun createDeletedEvent(entity: ServiceProvider): ServiceProviderEvent? {
        return ServiceProviderEvent(EventType.DELETED, entity)
    }
}