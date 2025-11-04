package com.luke.kHelperServer.application.auth.login

import com.luke.kHelperServer.application.auth.login.provided_port.LoginSuccessHandler
import com.luke.kHelperServer.application.auth.login.required_port.Hasher
import com.luke.kHelperServer.application.auth.login.required_port.RefreshTokenRepository
import com.luke.kHelperServer.domain.login.HashedRefreshToken
import com.luke.kHelperServer.domain.login.RefreshToken
import org.springframework.stereotype.Service

@Service
class LoginSuccessHandlerImpl(
    private val refreshTokenRepository: RefreshTokenRepository,
    private val hasher: Hasher,
) : LoginSuccessHandler {
    override fun saveRefreshToken(accountId: Long, refreshToken: RefreshToken) {
        val hashedRefreshToken = HashedRefreshToken(hasher.hashString(refreshToken.token))
        refreshTokenRepository.saveRefreshToken(accountId, hashedRefreshToken)
    }
}
