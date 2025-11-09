package com.luke.kHelperServer.application.account.provided_port

import com.luke.kHelperServer.application.account.dto.AccountDto
import com.luke.kHelperServer.domain.account.Email
import com.luke.kHelperServer.domain.account.request.AccountCreateRequest
import com.luke.kHelperServer.domain.account.write.Account
import jakarta.validation.Valid
import org.springframework.validation.annotation.Validated

@Validated
interface AccountWriter {
    fun registerAccount(@Valid request: AccountCreateRequest): AccountDto

    fun findByEmail(email: Email): AccountDto?

    fun findByAccountId(accountId: Long): AccountDto?

    fun saveAccount(account: Account): AccountDto

    fun existsByAccountId(accountId: Long): Boolean
}
