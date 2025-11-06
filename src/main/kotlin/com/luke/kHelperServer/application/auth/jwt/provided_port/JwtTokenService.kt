package com.luke.kHelperServer.application.auth.jwt.provided_port

import com.luke.kHelperServer.domain.account.Email
import com.luke.kHelperServer.domain.account.write.Account
import com.luke.kHelperServer.domain.authority.Role
import com.luke.kHelperServer.domain.login.AccessToken
import com.luke.kHelperServer.domain.login.RefreshToken
import io.jsonwebtoken.Claims

interface JwtTokenService {
    fun createAccessToken(account: Account): AccessToken

    fun createRefreshToken(account: Account): RefreshToken

    fun parseAccountIdFromRefreshToken(refreshToken: RefreshToken): Long

    fun parseAccessToken(accessToken: AccessToken): Claims

    fun parseEmailFromAccessTokenClaim(claims: Claims): Email

    fun parseAccountIdFromAccessTokenClaims(claims: Claims): Long

    fun parseRoleFromAccessTokenClaims(claims: Claims): Role
}
