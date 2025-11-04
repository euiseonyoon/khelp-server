package com.luke.kHelperServer.application.auth.login.provided_port

import com.luke.kHelperServer.domain.login.RefreshToken
import jakarta.servlet.http.HttpServletResponse

interface LoginSuccessHandler {
    fun saveRefreshToken(accountId: Long, refreshToken: RefreshToken)
}
