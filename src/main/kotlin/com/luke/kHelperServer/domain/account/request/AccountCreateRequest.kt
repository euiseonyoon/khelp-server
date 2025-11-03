package com.luke.kHelperServer.domain.account.request

import com.luke.kHelperServer.domain.account.Email
import com.luke.kHelperServer.domain.account.OauthVendor
import com.luke.kHelperServer.domain.account.PASSWORD_MAX_LENGTH
import com.luke.kHelperServer.domain.account.PASSWORD_MIN_LENGTH
import com.luke.kHelperServer.domain.authority.write.Authority
import jakarta.validation.constraints.Size

data class AccountCreateRequest(
    val email: Email,
    val nickname: String?,
    @field:Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)
    val rawPassword: String,
    val oauthVendor: OauthVendor?,
    val authority: Authority
)
