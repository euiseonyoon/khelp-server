package com.luke.kHelperServer.application.admin.account.provided_port

import com.luke.kHelperServer.application.account.dto.AccountDto
import com.luke.kHelperServer.domain.account.request.SetToAdminRequest

interface AdminAccountService {
    fun setToAccountToAdmin(request: SetToAdminRequest): AccountDto?
}
