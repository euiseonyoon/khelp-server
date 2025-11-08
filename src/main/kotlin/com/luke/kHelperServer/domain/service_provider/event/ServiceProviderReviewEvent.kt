package com.luke.kHelperServer.domain.service_provider.event

import com.luke.kHelperServer.domain.EventType
import com.luke.kHelperServer.domain.service_provider.write.ServiceProviderReview
import java.time.Instant

data class ServiceProviderReviewEvent(
    override val eventType: EventType,
    val serviceProviderReview: ServiceProviderReview,
    val timestamp: Instant = Instant.now()
): ServiceProviderCommittedEvent
