package com.luke.kHelperServer.domain.account.request

import com.luke.kHelperServer.domain.account.Email
import com.luke.kHelperServer.domain.account.OauthVendor
import com.luke.kHelperServer.domain.authority.write.Authority
import jakarta.validation.constraints.NotEmpty

data class AccountCreateRequest(
    val email: Email,
    val nickname: String?,
    @field:NotEmpty
    val rawPassword: String,
    val oauthVendor: OauthVendor?,
    val authority: Authority
)
