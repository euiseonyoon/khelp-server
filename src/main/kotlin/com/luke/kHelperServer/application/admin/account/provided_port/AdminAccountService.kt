package com.luke.kHelperServer.application.admin.account.provided_port

import com.luke.kHelperServer.application.account.dto.AccountDto
import com.luke.kHelperServer.domain.account.request.SetToAdminRequest
import jakarta.validation.Valid
import org.springframework.validation.annotation.Validated

@Validated
interface AdminAccountService {
    fun setToAccountToAdmin(@Valid  request: SetToAdminRequest): AccountDto?
}
