package com.luke.kHelperServer.domain.account.request

import com.luke.kHelperServer.domain.account.PASSWORD_MAX_LENGTH
import com.luke.kHelperServer.domain.account.PASSWORD_MIN_LENGTH
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

data class SetToAdminRequest(
    val targetEmail: String,
    @field:NotEmpty
    @field:Size(
        min = PASSWORD_MIN_LENGTH,
        max = PASSWORD_MAX_LENGTH,
        message = "비밀번호는 ${PASSWORD_MIN_LENGTH}자 이상 ${PASSWORD_MAX_LENGTH}자 이하여야 합니다.",
    )
    val newPassword: String?
)
