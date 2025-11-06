package com.luke.kHelperServer.application.auth.jwt

import com.luke.kHelperServer.application.auth.jwt.provided_port.JwtTokenService
import com.luke.kHelperServer.application.auth.jwt.required_port.JwtTokenGenerator
import com.luke.kHelperServer.application.auth.jwt.required_port.JwtTokenGenerator.Companion.AUTH_CLAIM_KEY
import com.luke.kHelperServer.application.auth.jwt.required_port.JwtTokenGenerator.Companion.EMAIL_CLAIM_KEY
import com.luke.kHelperServer.domain.account.Email
import com.luke.kHelperServer.domain.account.write.Account
import com.luke.kHelperServer.domain.authority.Role
import com.luke.kHelperServer.domain.login.AccessToken
import com.luke.kHelperServer.domain.login.RefreshToken
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
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

    override fun parseAccessToken(accessToken: AccessToken): Claims {
        val accessTokenSecret = jwtTokenGenerator.getAccessTokenSecret()

        return paresToken(accessToken.token, accessTokenSecret)
    }

    private fun parseAccountIdFromClaims(claims: Claims): Long {
        return claims.subject?.toLong() ?: throw MalformedJwtException("Missing subject from jwt.")
    }

    override fun parseAccountIdFromRefreshToken(refreshToken: RefreshToken): Long {
        val refreshTokenSecret = jwtTokenGenerator.getRefreshTokenSecret()
        return paresToken(refreshToken.token, refreshTokenSecret).let {
            parseAccountIdFromClaims(it)
        }
    }

    override fun parseAccountIdFromAccessTokenClaims(claims: Claims): Long {
        return parseAccountIdFromClaims(claims)
    }

    private fun parseInfoFromAccessTokenClaims(claims: Claims, key: String): String {
        return claims[key] as? String ?: throw MalformedJwtException("Missing key from claims.")
    }

    override fun parseEmailFromAccessTokenClaim(claims: Claims): Email {
        val emailAddress = parseInfoFromAccessTokenClaims(claims, EMAIL_CLAIM_KEY)
        return Email(emailAddress)
    }

    override fun parseRoleFromAccessTokenClaims(claims: Claims): Role {
        val roleName = parseInfoFromAccessTokenClaims(claims, AUTH_CLAIM_KEY)
        return Role(roleName)
    }

    private fun paresToken(token: String, secretKey: SecretKey): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .body
    }

}
