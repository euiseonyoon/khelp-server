package com.luke.kHelperServer.domain.account.read

import com.luke.kHelperServer.domain.account.Email
import com.luke.kHelperServer.domain.account.OauthVendor
import com.luke.kHelperServer.domain.authority.read.AuthorityDocument

data class AccountView(
    val accountId: Long,
    val email: Email,
    val passwordHash: String,
    val authorityDoc: AuthorityDocument,
    val nickName: String?,
    val enabled: Boolean,
    val oauthVendor: OauthVendor?
)
