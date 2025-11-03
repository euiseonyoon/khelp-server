package com.luke.kHelperServer.application.jwt.required_port

import com.luke.kHelperServer.domain.account.write.Account
import com.luke.kHelperServer.domain.login.AccessToken
import com.luke.kHelperServer.domain.login.RefreshToken

interface JwtTokenGenerator {
    val accessTokenExpirationMs: Long

    val refreshTokenExpirationMs: Long

    fun generateAccessToken(account: Account): AccessToken

    fun generateRefreshToken(account: Account): RefreshToken
}
