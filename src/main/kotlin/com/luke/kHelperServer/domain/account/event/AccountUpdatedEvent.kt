package com.luke.kHelperServer.domain.account.event

import com.luke.kHelperServer.domain.account.write.Account
import java.time.Instant

data class AccountUpdatedEvent(
    val account: Account,
    val timestamp: Instant = Instant.now()
) : AccountCommittedEvent
