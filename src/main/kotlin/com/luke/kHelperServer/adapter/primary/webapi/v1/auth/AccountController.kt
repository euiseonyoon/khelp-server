package com.luke.kHelperServer.adapter.primary.webapi.v1.auth

import com.luke.kHelperServer.adapter.primary.webapi.v1.V1_ACCOUNT_URL
import com.luke.kHelperServer.application.account.dto.AccountDto
import com.luke.kHelperServer.application.account.provided_port.AccountService
import com.luke.kHelperServer.common.GlobalResponse
import com.luke.kHelperServer.domain.account.request.AccountUpdateNicknameRequest
import com.luke.kHelperServer.domain.account.request.AccountUpdatePasswordRequest
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(V1_ACCOUNT_URL)
class AccountController (
    private val accountService: AccountService
) {
    @PutMapping("/password")
    fun updatePassword(
        @RequestBody(required = true) reqBody: AccountUpdatePasswordRequest,
        @AuthenticationPrincipal accountId: Long,
    ): ResponseEntity<GlobalResponse<AccountDto?>> {
        return accountService.updateAccountPassword(reqBody, accountId).let {
            GlobalResponse.createResponse(it)
        }
    }

    @PutMapping("/nickname")
    fun updateNickname(
        @RequestBody(required = true) reqBody: AccountUpdateNicknameRequest,
        @AuthenticationPrincipal accountId: Long,
    ): ResponseEntity<GlobalResponse<AccountDto?>> {
        return accountService.updateAccountNickname(reqBody, accountId).let {
            GlobalResponse.createResponse(it)
        }
    }
}
