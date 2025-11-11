package com.luke.kHelperServer.domain.authority.event

import com.luke.kHelperServer.domain.EventType
import com.luke.kHelperServer.domain.WriteDbCommitedEvent
import com.luke.kHelperServer.domain.authority.write.Authority
import java.time.Instant

data class AuthorityEvent(
    override val eventType: EventType,
    val authority: Authority,
    val timestamp: Instant = Instant.now()
) : WriteDbCommitedEvent
