package com.luke.kHelperServer.domain.account.read

import com.luke.kHelperServer.domain.account.Email

interface AccountQueryRepository {
    fun findById(id: Long): AccountView?
    fun findByEmail(email: Email): AccountView?
}
