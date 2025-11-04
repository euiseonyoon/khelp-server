package com.luke.kHelperServer.application.auth.jwt

import com.luke.kHelperServer.application.auth.jwt.provided_port.JwtTokenService
import com.luke.kHelperServer.application.auth.jwt.required_port.JwtTokenGenerator
import com.luke.kHelperServer.domain.account.write.Account
import com.luke.kHelperServer.domain.exception.BizException
import com.luke.kHelperServer.domain.exception.ErrorMessages
import com.luke.kHelperServer.domain.login.AccessToken
import com.luke.kHelperServer.domain.login.RefreshToken
import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Service
import javax.crypto.SecretKey

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

    override fun parseAccountIdFromRefreshToken(refreshToken: RefreshToken): Long {
        val refreshTokenSecret = jwtTokenGenerator.getRefreshTokenSecret()
        return paresToken(refreshToken.token, refreshTokenSecret).let {
            it.subject?.toLong() ?: throw BizException(ErrorMessages.TOKEN_PARSE_ERROR)
        }
    }

    private fun paresToken(token: String, secretKey: SecretKey): Claims {
        try {
            return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .body
        } catch (e: JwtException) {
            throw BizException(ErrorMessages.TOKEN_PARSE_ERROR)
        }
    }

}
