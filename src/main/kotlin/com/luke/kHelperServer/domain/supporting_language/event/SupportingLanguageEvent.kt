package com.luke.kHelperServer.domain.supporting_language.event

import com.luke.kHelperServer.domain.EventType
import com.luke.kHelperServer.domain.WriteDbCommitedEvent
import com.luke.kHelperServer.domain.supporting_language.write.SupportingLanguage
import java.time.Instant

data class SupportingLanguageEvent(
    override val eventType: EventType,
    val supportingLanguage: SupportingLanguage,
    val timestamp: Instant = Instant.now()
): WriteDbCommitedEvent

