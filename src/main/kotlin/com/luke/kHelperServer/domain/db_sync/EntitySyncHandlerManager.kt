package com.luke.kHelperServer.domain.db_sync

import com.luke.kHelperServer.domain.WriteDbCommitedEvent

interface EntitySyncHandlerManager {
    fun findHandler(event: WriteDbCommitedEvent): EntitySyncHandler<*>?
}
