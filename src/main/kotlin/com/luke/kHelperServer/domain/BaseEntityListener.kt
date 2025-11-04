package com.luke.kHelperServer.domain

import jakarta.persistence.PostPersist
import jakarta.persistence.PostRemove
import jakarta.persistence.PostUpdate
import org.springframework.context.ApplicationEventPublisher

abstract class BaseEntityListener<T : BaseEntity>(
    private val eventPublisher: ApplicationEventPublisher
) {
    @PostPersist
    fun onPostPersist(entity: T) {
        createCreatedEvent(entity)?.let {
            eventPublisher.publishEvent(it)
        }
    }

    @PostUpdate
    fun onPostUpdate(entity: T) {
        createUpdatedEvent(entity)?.let {
            eventPublisher.publishEvent(it)
        }
    }

    @PostRemove
    fun onPostRemove(entity: T) {
        createDeletedEvent(entity)?.let {
            eventPublisher.publishEvent(it)
        }
    }

    protected abstract fun createCreatedEvent(entity: T): WriteDbCommitedEvent?
    protected abstract fun createUpdatedEvent(entity: T): WriteDbCommitedEvent?
    protected abstract fun createDeletedEvent(entity: T): WriteDbCommitedEvent?
}