package com.luke.kHelperServer.domain.service_provider.event

import com.luke.kHelperServer.domain.EventType
import com.luke.kHelperServer.domain.WriteDbCommitedEvent

interface ServiceProviderCommittedEvent: WriteDbCommitedEvent {
    val eventType: EventType
}