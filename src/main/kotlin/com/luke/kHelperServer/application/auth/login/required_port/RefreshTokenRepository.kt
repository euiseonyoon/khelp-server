package com.luke.kHelperServer.application.auth.login.required_port

import com.luke.kHelperServer.domain.login.HashedRefreshToken

interface RefreshTokenRepository {
    fun saveRefreshToken(accountId: Long, hashedRefreshToken: HashedRefreshToken)
}
