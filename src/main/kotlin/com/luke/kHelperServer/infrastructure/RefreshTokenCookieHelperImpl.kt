package com.luke.kHelperServer.infrastructure

import com.luke.kHelperServer.infrastructure.RefreshTokenCookieHelper.Companion.REFRESH_TOKEN_COOKIE_KEY
import com.luke.kHelperServer.domain.login.RefreshToken
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class RefreshTokenCookieHelperImpl(
    @Value("\${jwt.refresh-duration-ms}")
    val refreshDurationMs: Long,
) : RefreshTokenCookieHelper {
    override fun setRefreshTokenOnCookie(response: HttpServletResponse, refreshToken: RefreshToken) {
        val refreshTokenCookie = Cookie(REFRESH_TOKEN_COOKIE_KEY, refreshToken.token)
        response.addCookie(
            setSecuredCookie(refreshTokenCookie, (refreshDurationMs / 1000).toInt())
        )
    }

    private fun setSecuredCookie(cookie: Cookie, maxAgeSec: Int): Cookie {
        cookie.apply {
            isHttpOnly = true // JavaScript 접근 방지
            path = "/"
            maxAge = maxAgeSec
            secure = true
        }
        return cookie
    }
}
