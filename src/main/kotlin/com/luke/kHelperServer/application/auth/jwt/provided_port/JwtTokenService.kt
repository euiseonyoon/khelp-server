package com.luke.kHelperServer.application.auth.jwt.provided_port

import com.luke.kHelperServer.domain.account.write.Account
import com.luke.kHelperServer.domain.login.AccessToken
import com.luke.kHelperServer.domain.login.RefreshToken

interface JwtTokenService {
    fun createAccessToken(account: Account): AccessToken

    fun createRefreshToken(account: Account): RefreshToken
}
