package com.luke.kHelperServer.application.auth.jwt.required_port

import com.luke.kHelperServer.domain.account.write.Account
import com.luke.kHelperServer.domain.login.AccessToken
import com.luke.kHelperServer.domain.login.RefreshToken
import javax.crypto.SecretKey

interface JwtTokenGenerator {
    val accessTokenExpirationMs: Long

    val refreshTokenExpirationMs: Long

    fun generateAccessToken(account: Account): AccessToken

    fun generateRefreshToken(account: Account): RefreshToken

    fun getRefreshTokenSecret(): SecretKey
}
