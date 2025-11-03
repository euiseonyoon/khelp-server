package com.luke.kHelperServer.application.login.required_port

import com.luke.kHelperServer.domain.login.RefreshToken
import jakarta.servlet.http.HttpServletResponse

interface RefreshTokenCookieSetter {
    companion object {
        const val REFRESH_TOKEN_COOKIE_KEY: String = "refreshToken"
    }

    fun setRefreshTokenOnCookie(response: HttpServletResponse, refreshToken: RefreshToken)
}
