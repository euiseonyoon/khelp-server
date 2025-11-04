package com.luke.kHelperServer.application.auth.refresh_token_security

import com.luke.kHelperServer.application.auth.refresh_token_security.provided_port.RefreshTokenRepoHandler
import com.luke.kHelperServer.application.auth.login.required_port.Hasher
import com.luke.kHelperServer.application.auth.refresh_token_security.required_port.RefreshTokenRepository
import com.luke.kHelperServer.domain.login.HashedRefreshToken
import com.luke.kHelperServer.domain.login.RefreshToken
import org.springframework.stereotype.Service

@Service
class RefreshTokenRepoHandlerImpl(
    private val refreshTokenRepository: RefreshTokenRepository,
    private val hasher: Hasher,
) : RefreshTokenRepoHandler {
    override fun saveRefreshToken(accountId: Long, refreshToken: RefreshToken) {
        val hashedRefreshToken = HashedRefreshToken(hasher.hashString(refreshToken.token))
        refreshTokenRepository.saveRefreshToken(accountId, hashedRefreshToken)
    }
}
