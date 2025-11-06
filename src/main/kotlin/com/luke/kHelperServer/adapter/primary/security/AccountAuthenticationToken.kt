package com.luke.kHelperServer.adapter.primary.security

import com.luke.kHelperServer.domain.account.Email
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority

class AccountAuthenticationToken(
    val accountId: Long,
    val authorities: List<GrantedAuthority>,
    val email: Email,
): AbstractAuthenticationToken(authorities) {
    override fun getCredentials(): Any? = null

    override fun getPrincipal(): Long = accountId
}

