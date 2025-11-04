package com.luke.kHelperServer.application.auth.refresh_token_security.required_port

import com.luke.kHelperServer.domain.login.HashedRefreshToken

interface RefreshTokenRepository {
    fun saveRefreshToken(accountId: Long, hashedRefreshToken: HashedRefreshToken)
}
