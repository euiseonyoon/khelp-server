package com.luke.kHelperServer.domain.account.event

import com.luke.kHelperServer.domain.EventType
import com.luke.kHelperServer.domain.WriteDbCommitedEvent
import com.luke.kHelperServer.domain.account.write.Account
import java.time.Instant

data class AccountEvent(
    override val eventType: EventType,
    val account: Account,
    val timestamp: Instant = Instant.now()
) : WriteDbCommitedEvent
