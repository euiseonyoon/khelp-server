package com.luke.kHelperServer.application.auth.refresh_token_security.provided_port

import com.luke.kHelperServer.domain.login.RefreshToken

interface RefreshTokenHandler {
    fun saveRefreshToken(accountId: Long, refreshToken: RefreshToken)
}
