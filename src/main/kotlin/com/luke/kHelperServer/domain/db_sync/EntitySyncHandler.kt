package com.luke.kHelperServer.domain.db_sync

import com.luke.kHelperServer.domain.WriteDbCommitedEvent
import kotlin.reflect.KClass

interface EntitySyncHandler<T : WriteDbCommitedEvent> {
    fun handle(event: WriteDbCommitedEvent)

    fun getHandlingMessageType(): KClass<T>
}
