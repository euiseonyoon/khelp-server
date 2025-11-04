package com.luke.kHelperServer.adapter.primary.db_synchronizer

import com.luke.kHelperServer.domain.WriteDbCommitedEvent

interface ReadDbSynchronizer {
    fun handleWriteDbCommitedEvent(event: WriteDbCommitedEvent)

    fun fallBack(event: WriteDbCommitedEvent)
}