package com.luke.kHelperServer.application.account.provided_port

import com.luke.kHelperServer.application.account.dto.AccountDto
import com.luke.kHelperServer.domain.account.Email
import com.luke.kHelperServer.domain.account.PASSWORD_MAX_LENGTH
import com.luke.kHelperServer.domain.account.PASSWORD_MIN_LENGTH
import com.luke.kHelperServer.domain.account.request.AccountUpdateNicknameRequest
import com.luke.kHelperServer.domain.account.request.AccountUpdatePasswordRequest
import jakarta.validation.Valid
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size
import org.springframework.validation.annotation.Validated

@Validated
interface AccountService {
    fun updateAccountPassword(
        @NotEmpty
        @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)
        newRawPassword: String,
        email: Email,
    ): AccountDto?

    fun updateAccountPassword(@Valid request: AccountUpdatePasswordRequest, accountId: Long): AccountDto?

    fun updateAccountNickname(@Valid request: AccountUpdateNicknameRequest, accountId: Long): AccountDto?
}
