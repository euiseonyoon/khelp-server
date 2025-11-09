package com.luke.kHelperServer.domain.supporting_language.write

import com.luke.kHelperServer.domain.BaseEntityListener
import com.luke.kHelperServer.domain.EventType
import com.luke.kHelperServer.domain.WriteDbCommitedEvent
import com.luke.kHelperServer.domain.supporting_language.event.SupportingLanguageEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class SupportingLanguageEntityListener(
    eventPublisher: ApplicationEventPublisher
): BaseEntityListener<SupportingLanguage>(eventPublisher){
    override fun createCreatedEvent(entity: SupportingLanguage): WriteDbCommitedEvent? {
        return SupportingLanguageEvent(EventType.CREATED, entity)
    }

    override fun createUpdatedEvent(entity: SupportingLanguage): WriteDbCommitedEvent? {
        return SupportingLanguageEvent(EventType.UPDATED, entity)
    }

    override fun createDeletedEvent(entity: SupportingLanguage): WriteDbCommitedEvent? {
        return SupportingLanguageEvent(EventType.DELETED, entity)
    }

}
