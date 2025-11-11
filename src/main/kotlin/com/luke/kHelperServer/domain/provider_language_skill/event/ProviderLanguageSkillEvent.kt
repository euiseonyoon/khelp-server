package com.luke.kHelperServer.domain.provider_language_skill.event

import com.luke.kHelperServer.domain.EventType
import com.luke.kHelperServer.domain.WriteDbCommitedEvent
import com.luke.kHelperServer.domain.provider_language_skill.write.ProviderLanguageSkill
import java.time.Instant

data class ProviderLanguageSkillEvent(
    override val eventType: EventType,
    val providerLanguageSkill: ProviderLanguageSkill,
    val timestamp: Instant = Instant.now()
): WriteDbCommitedEvent
