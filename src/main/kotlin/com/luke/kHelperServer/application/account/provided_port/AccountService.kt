package com.luke.kHelperServer.application.account.provided_port

import com.luke.kHelperServer.application.account.dto.AccountDto
import com.luke.kHelperServer.domain.account.Email
import com.luke.kHelperServer.domain.account.request.AccountUpdateNicknameRequest
import com.luke.kHelperServer.domain.account.request.AccountUpdatePasswordRequest

interface AccountService {
    fun updateAccountPassword(newRawPassword: String, email: Email): AccountDto?

    fun updateAccountPassword(request: AccountUpdatePasswordRequest, accountId: Long): AccountDto?

    fun updateAccountNickname(request: AccountUpdateNicknameRequest, accountId: Long): AccountDto?
}
