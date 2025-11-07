package com.luke.kHelperServer.infrastructure

import com.luke.kHelperServer.domain.exception.BizException
import com.luke.kHelperServer.domain.exception.ErrorMessages
import com.luke.kHelperServer.infrastructure.RefreshTokenCookieHelper.Companion.REFRESH_TOKEN_COOKIE_KEY
import com.luke.kHelperServer.domain.login.RefreshToken
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
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

    override fun deleteRefreshTokenFromCookie(response: HttpServletResponse) {
        val refreshTokenCookie = Cookie(REFRESH_TOKEN_COOKIE_KEY, null)
        response.addCookie(
            setSecuredCookie(refreshTokenCookie, 0)
        )
    }

    override fun extractRefreshTokenFromCookie(request: HttpServletRequest): RefreshToken? {
        val refreshTokenString = request.cookies.firstOrNull { it.name == REFRESH_TOKEN_COOKIE_KEY }?.value

        return refreshTokenString?.let { RefreshToken(it) }
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
