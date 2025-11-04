package com.luke.kHelperServer.application.db_synchronizer.provided_port

import com.luke.kHelperServer.domain.WriteDbCommitedEvent
import kotlin.reflect.KClass

interface EntitySynchronizer<T : WriteDbCommitedEvent> {
    fun synchronizeReadDb(event: WriteDbCommitedEvent)

    fun getHandlingMessageType(): KClass<T>
}
