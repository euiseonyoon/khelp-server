package com.luke.kHelperServer.domain.account.write

import com.luke.kHelperServer.domain.account.event.AccountCreatedEvent
import com.luke.kHelperServer.domain.account.event.AccountUpdatedEvent
import com.luke.kHelperServer.domain.BaseEntityListener
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component


@Component
class AccountEntityListener(
    eventPublisher: ApplicationEventPublisher
) : BaseEntityListener<Account>(eventPublisher) {

    override fun createCreatedEvent(entity: Account) = AccountCreatedEvent(entity)
    override fun createUpdatedEvent(entity: Account) = AccountUpdatedEvent(entity)
    override fun createDeletedEvent(entity: Account) = AccountUpdatedEvent(entity)
}
