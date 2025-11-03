package com.luke.kHelperServer.application.login.required_port

import com.luke.kHelperServer.domain.login.HashedRefreshToken

interface RefreshTokenRepository {
    fun saveRefreshToken(accountId: Long, hashedRefreshToken: HashedRefreshToken)
}
