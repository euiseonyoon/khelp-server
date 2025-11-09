package com.luke.kHelperServer.domain.provider_language_skill.event

import com.luke.kHelperServer.domain.EventType
import com.luke.kHelperServer.domain.WriteDbCommitedEvent

interface ProviderLanguageSkillCommittedEvent: WriteDbCommitedEvent {
    val eventType: EventType
}
