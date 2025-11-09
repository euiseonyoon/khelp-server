package com.luke.kHelperServer.application.auth.login.provided_port

import com.luke.kHelperServer.domain.account.Email
import com.luke.kHelperServer.domain.account.OauthVendor
import com.luke.kHelperServer.domain.login.GeneratedTokens
import jakarta.validation.constraints.NotEmpty
import org.springframework.validation.annotation.Validated

@Validated
interface LoginService {
    fun loginByOauth(@NotEmpty token: String, oauthVendor: OauthVendor): GeneratedTokens

    fun loginByEmailAndPassword(email: Email, @NotEmpty rawPassword: String): GeneratedTokens
}

