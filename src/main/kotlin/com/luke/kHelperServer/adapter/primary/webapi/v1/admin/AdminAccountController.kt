package com.luke.kHelperServer.adapter.primary.webapi.v1.admin

import com.luke.kHelperServer.adapter.primary.webapi.v1.V1_ADMIN_ACCOUNT_URL
import com.luke.kHelperServer.application.account.dto.AccountDto
import com.luke.kHelperServer.application.admin.account.provided_port.AdminAccountService
import com.luke.kHelperServer.common.GlobalResponse
import com.luke.kHelperServer.domain.account.request.SetToAdminRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(V1_ADMIN_ACCOUNT_URL)
class AdminAccountController(
    private val adminAccountService: AdminAccountService,
) {
    @PutMapping
    fun setToAdmin(
        @RequestBody(required = true) reqBody: SetToAdminRequest
    ): ResponseEntity<GlobalResponse<AccountDto?>> {
        val accountDto = adminAccountService.setToAccountToAdmin(reqBody)
        return GlobalResponse.createResponse(accountDto)
    }
}
