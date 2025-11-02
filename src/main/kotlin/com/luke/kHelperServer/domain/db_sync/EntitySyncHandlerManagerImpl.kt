package com.luke.kHelperServer.domain.db_sync

import com.luke.kHelperServer.domain.WriteDbCommitedEvent
import org.springframework.stereotype.Component
import kotlin.reflect.KClass

@Component
class EntitySyncHandlerManagerImpl(
    syncHandlers: List<EntitySyncHandler<*>>
) : EntitySyncHandlerManager {
    private val handlerMap2: Map<String, EntitySyncHandler<*>> = syncHandlers.mapNotNull { handler ->
        handler.getHandlingMessageType().qualifiedName?.let { name -> name to handler }
    }.toMap()

    override fun findHandler(event: WriteDbCommitedEvent): EntitySyncHandler<*>? {
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
