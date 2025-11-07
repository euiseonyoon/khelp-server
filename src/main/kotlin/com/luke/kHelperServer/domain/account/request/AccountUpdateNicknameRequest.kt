package com.luke.kHelperServer.domain.account.request

import jakarta.validation.constraints.NotEmpty

data class AccountUpdateNicknameRequest(
    @field:NotEmpty
    val newNickname: String,
)
