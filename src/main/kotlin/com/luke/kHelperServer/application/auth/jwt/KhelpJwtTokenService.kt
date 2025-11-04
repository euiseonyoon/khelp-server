package com.luke.kHelperServer.application.auth.jwt

import com.luke.kHelperServer.application.auth.jwt.provided_port.JwtTokenService
import com.luke.kHelperServer.application.auth.jwt.required_port.JwtTokenGenerator
import com.luke.kHelperServer.domain.account.write.Account
import com.luke.kHelperServer.domain.login.AccessToken
import com.luke.kHelperServer.domain.login.RefreshToken
import org.springframework.stereotype.Service

@Service
class KhelpJwtTokenService(
    private val jwtTokenGenerator: JwtTokenGenerator,
) : JwtTokenService {
    override fun createAccessToken(account: Account): AccessToken {
        return jwtTokenGenerator.generateAccessToken(account)
    }

    override fun createRefreshToken(account: Account): RefreshToken {
        return jwtTokenGenerator.generateRefreshToken(account)
    }

}
