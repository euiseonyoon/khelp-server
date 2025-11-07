package com.luke.kHelperServer.application.auth.refresh_token_security.provided_port

import com.luke.kHelperServer.domain.login.AccessToken
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

interface RefreshTokenService {
    fun executeTokenRotation(request: HttpServletRequest, response: HttpServletResponse): AccessToken
}