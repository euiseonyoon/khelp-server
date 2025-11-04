package com.luke.kHelperServer.adapter.primary.db_synchronizer

import com.luke.kHelperServer.application.db_synchronizer.provided_port.EntitySynchronizer
import com.luke.kHelperServer.domain.WriteDbCommitedEvent

interface EntitySynchronizerManager {
    fun findSynchronizer(event: WriteDbCommitedEvent): EntitySynchronizer<*>?
}
