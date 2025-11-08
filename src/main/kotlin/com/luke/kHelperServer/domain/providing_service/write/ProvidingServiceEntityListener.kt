package com.luke.kHelperServer.domain.providing_service.write

import com.luke.kHelperServer.domain.BaseEntityListener
import com.luke.kHelperServer.domain.EventType
import com.luke.kHelperServer.domain.providing_service.event.ProvidingServiceEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class ProvidingServiceEntityListener(
    eventPublisher: ApplicationEventPublisher
) : BaseEntityListener<ProvidingService>(eventPublisher) {
    override fun createCreatedEvent(entity: ProvidingService): ProvidingServiceEvent? {
        return ProvidingServiceEvent(EventType.CREATED, entity)
    }

    override fun createUpdatedEvent(entity: ProvidingService): ProvidingServiceEvent? {
        return ProvidingServiceEvent(EventType.UPDATED, entity)
    }

    override fun createDeletedEvent(entity: ProvidingService): ProvidingServiceEvent? {
        return ProvidingServiceEvent(EventType.DELETED, entity)
    }

}
