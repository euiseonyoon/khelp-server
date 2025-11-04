package com.luke.kHelperServer.application.auth.refresh_token_security.provided_port

import com.luke.kHelperServer.domain.login.HashedRefreshToken
import com.luke.kHelperServer.domain.login.RefreshToken

interface RefreshTokenRepoHandler {
    fun saveRefreshToken(accountId: Long, refreshToken: RefreshToken)

    fun findHashedRefreshToken(accountId: Long): HashedRefreshToken?

    fun deleteHashedRefreshToken(accountId: Long)
}
