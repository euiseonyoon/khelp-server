package com.luke.kHelperServer.application.account.required_port

import com.luke.kHelperServer.domain.account.Email
import com.luke.kHelperServer.domain.account.write.Account

interface AccountCommandRepository {
    fun save(account: Account): Account

    fun existsByEmail(email: Email): Boolean

    fun findByEmail(email: Email): Account?
}
