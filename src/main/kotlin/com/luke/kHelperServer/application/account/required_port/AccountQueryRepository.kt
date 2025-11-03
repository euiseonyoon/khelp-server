package com.luke.kHelperServer.application.account.required_port

import com.luke.kHelperServer.domain.account.Email
import com.luke.kHelperServer.domain.account.read.AccountView

interface AccountQueryRepository {
    fun findById(id: Long): AccountView?
    fun findByEmail(email: Email): AccountView?
}
