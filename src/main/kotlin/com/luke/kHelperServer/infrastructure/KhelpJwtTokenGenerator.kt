package com.luke.kHelperServer.infrastructure

import com.luke.kHelperServer.application.auth.jwt.required_port.JwtTokenGenerator
import com.luke.kHelperServer.domain.account.write.Account
import com.luke.kHelperServer.domain.login.AccessToken
import com.luke.kHelperServer.domain.login.RefreshToken
import org.springframework.stereotype.Component
import javax.crypto.SecretKey
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import java.util.UUID
import java.util.Date

@Component
class KhelpJwtTokenGenerator(
    @Value("\${jwt.access-secret}")
    private val accessSecret: String,

    @Value("\${jwt.refresh-secret}")
    private val refreshSecret: String,

    @Value("\${jwt.issuer}")
    private val issuer: String,

    @Value("\${jwt.access-duration-ms}")
    private val accessDurationMs: Long,

    @Value("\${jwt.refresh-duration-ms}")
    private val refreshDurationMs: Long,
): JwtTokenGenerator {

    companion object {
        const val EMAIL_CLAIM_KEY = "email"
        const val AUTH_CLAIM_KEY = "auth"
    }

    override val accessTokenExpirationMs: Long = accessDurationMs
    override val refreshTokenExpirationMs: Long = refreshDurationMs

    private val accessSecretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(accessSecret))
    private val refreshSecretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(refreshSecret))

    override fun generateAccessToken(account: Account): AccessToken {
        return createToken(
            accountId = account.id,
            durationMs = accessTokenExpirationMs,
            secretKey = accessSecretKey,
            claims = mapOf(
                AUTH_CLAIM_KEY to account.authority.role.name,
                EMAIL_CLAIM_KEY to account.email.address,
            ),
            jwtTokenId = null,
        ).let { AccessToken(it) }
    }

    override fun generateRefreshToken(account: Account): RefreshToken {
        return createToken(
            accountId = account.id,
            durationMs = refreshTokenExpirationMs,
            secretKey = refreshSecretKey,
            claims = null,
            jwtTokenId = UUID.randomUUID().toString()
        ).let { RefreshToken(it) }
    }

    private fun createToken(
        accountId: Long,
        durationMs: Long,
        secretKey: SecretKey,
        claims: Map<String, Any>?,
        jwtTokenId: String?
    ): String {
        val builder = Jwts.builder()

        claims?.let { builder.setClaims(it) }
        jwtTokenId?.let { builder.setId(it) }

        builder
            .setSubject(accountId.toString())
            .setIssuedAt(Date())
            .setIssuer(issuer)
            .setExpiration(Date(System.currentTimeMillis() + durationMs))
            .signWith(secretKey, SignatureAlgorithm.HS256)

        return builder.compact()
    }
}
