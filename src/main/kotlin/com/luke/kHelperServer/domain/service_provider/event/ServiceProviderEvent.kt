package com.luke.kHelperServer.domain.service_provider.event

import com.luke.kHelperServer.domain.EventType
import com.luke.kHelperServer.domain.WriteDbCommitedEvent
import com.luke.kHelperServer.domain.service_provider.write.ServiceProvider
import java.time.Instant

data class ServiceProviderEvent(
    override val eventType: EventType,
    val serviceProvider: ServiceProvider,
    val timestamp: Instant = Instant.now()
): WriteDbCommitedEvent
