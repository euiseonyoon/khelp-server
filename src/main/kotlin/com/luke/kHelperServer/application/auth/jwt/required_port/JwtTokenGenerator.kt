package com.luke.kHelperServer.application.auth.jwt.required_port

import com.luke.kHelperServer.domain.account.write.Account
import com.luke.kHelperServer.domain.login.AccessToken
import com.luke.kHelperServer.domain.login.RefreshToken
import javax.crypto.SecretKey

interface JwtTokenGenerator {
    companion object {
        const val EMAIL_CLAIM_KEY = "email"
        const val AUTH_CLAIM_KEY = "auth"
    }

    val accessTokenExpirationMs: Long

    val refreshTokenExpirationMs: Long

    fun generateAccessToken(account: Account): AccessToken

    fun generateRefreshToken(account: Account): RefreshToken

    fun getRefreshTokenSecret(): SecretKey

    fun getAccessTokenSecret(): SecretKey
}
