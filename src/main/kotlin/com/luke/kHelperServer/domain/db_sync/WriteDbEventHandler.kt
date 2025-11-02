package com.luke.kHelperServer.domain.db_sync

import com.luke.kHelperServer.domain.WriteDbCommitedEvent

interface WriteDbEventHandler {
    fun handleWriteDbCommitedEvent(event: WriteDbCommitedEvent)

    fun fallBack(event: WriteDbCommitedEvent)
}