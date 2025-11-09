package com.luke.kHelperServer.domain.supporting_language.event

import com.luke.kHelperServer.domain.EventType
import com.luke.kHelperServer.domain.WriteDbCommitedEvent

interface SupportingLanguageCommittedEvent: WriteDbCommitedEvent {
    val eventType: EventType
}