package com.luke.kHelperServer.adapter.primary.db_synchronizer

import com.luke.kHelperServer.application.db_synchronizer.provided_port.EntitySynchronizer
import com.luke.kHelperServer.domain.WriteDbCommitedEvent
import org.springframework.stereotype.Component
import kotlin.reflect.KClass

@Component
class EntitySynchronizerManagerImpl(
    syncHandlers: List<EntitySynchronizer<*>>
) : EntitySynchronizerManager {
    private val handlerMap2: Map<String, EntitySynchronizer<*>> = syncHandlers.mapNotNull { handler ->
        handler.getHandlingMessageType().qualifiedName?.let { name -> name to handler }
    }.toMap()

    override fun findSynchronizer(event: WriteDbCommitedEvent): EntitySynchronizer<*>? {
        val superTypes = (event::class as KClass<*>).supertypes
        superTypes
            .mapNotNull { kType -> kType.classifier as? KClass<*> }
            .forEach { superTypeKClass ->
                superTypeKClass.qualifiedName?.let { qualifiedName ->
                    handlerMap2[qualifiedName]?.let { return it }
                }
            }
        return null
    }
}
