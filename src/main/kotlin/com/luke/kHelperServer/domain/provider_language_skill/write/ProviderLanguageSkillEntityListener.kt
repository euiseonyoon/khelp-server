package com.luke.kHelperServer.domain.provider_language_skill.write

import com.luke.kHelperServer.domain.BaseEntityListener
import com.luke.kHelperServer.domain.EventType
import com.luke.kHelperServer.domain.provider_language_skill.event.ProviderLanguageSkillEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component


@Component
class ProviderLanguageSkillEntityListener(
    eventPublisher: ApplicationEventPublisher
) : BaseEntityListener<ProviderLanguageSkill>(eventPublisher){
    override fun createCreatedEvent(entity: ProviderLanguageSkill): ProviderLanguageSkillEvent? {
        return ProviderLanguageSkillEvent(EventType.CREATED, entity)
    }

    override fun createUpdatedEvent(entity: ProviderLanguageSkill): ProviderLanguageSkillEvent? {
        return ProviderLanguageSkillEvent(EventType.UPDATED, entity)
    }

    override fun createDeletedEvent(entity: ProviderLanguageSkill): ProviderLanguageSkillEvent? {
        return ProviderLanguageSkillEvent(EventType.DELETED, entity)
    }
}
