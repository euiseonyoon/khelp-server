package com.luke.kHelperServer.domain.providing_service.event

import com.luke.kHelperServer.domain.EventType
import com.luke.kHelperServer.domain.WriteDbCommitedEvent

interface ProvidingServiceCommittedEvent: WriteDbCommitedEvent {
    val eventType: EventType
}
