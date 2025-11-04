package com.luke.kHelperServer.application.auth.auto_register.provided_port

import com.luke.kHelperServer.application.account.dto.AccountDto
import com.luke.kHelperServer.domain.account.Email
import com.luke.kHelperServer.domain.account.OauthVendor

interface AutoRegisterer {
    fun autoRegisterAccount(email: Email, oauthVendor: OauthVendor): AccountDto
}
