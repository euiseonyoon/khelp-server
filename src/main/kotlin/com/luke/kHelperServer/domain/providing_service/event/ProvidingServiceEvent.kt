package com.luke.kHelperServer.domain.providing_service.event

import com.luke.kHelperServer.domain.EventType
import com.luke.kHelperServer.domain.WriteDbCommitedEvent
import com.luke.kHelperServer.domain.providing_service.write.ProvidingService
import java.time.Instant

data class ProvidingServiceEvent(
    override val eventType: EventType,
    val providingService: ProvidingService,
    val timestamp: Instant = Instant.now()
): WriteDbCommitedEvent
