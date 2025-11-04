package com.luke.kHelperServer.adapter.primary.webapi.v1.auth.login

import com.luke.kHelperServer.adapter.primary.webapi.v1.V1_LOGIN_URL
import com.luke.kHelperServer.application.auth.login.provided_port.LoginService
import com.luke.kHelperServer.common.GlobalResponse
import com.luke.kHelperServer.domain.account.Email
import com.luke.kHelperServer.domain.login.EmailLoginRequest
import com.luke.kHelperServer.domain.login.LoginResponse
import com.luke.kHelperServer.domain.login.OauthLoginRequest
import com.luke.kHelperServer.infrastructure.RefreshTokenCookieSetter
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(V1_LOGIN_URL)
class LoginController(
    private val loginService: LoginService,
    private val refreshTokenCookieSetter: RefreshTokenCookieSetter
) {
    @PostMapping("/oauth")
    fun loginByOauth(
        @RequestBody(required = true) reqBody: OauthLoginRequest,
        response: HttpServletResponse,
    ): ResponseEntity<GlobalResponse<LoginResponse>> {
        val generatedTokens = loginService.loginByOauth(reqBody.token, reqBody.oauthVendor)

        refreshTokenCookieSetter.setRefreshTokenOnCookie(response, generatedTokens.refreshToken)

        return GlobalResponse.createResponse(LoginResponse(generatedTokens.accessToken.token))
    }

    @PostMapping("/email")
    fun loginByEmail(
        @RequestBody(required = true) reqBody: EmailLoginRequest,
        response: HttpServletResponse,
    ): ResponseEntity<GlobalResponse<LoginResponse>> {
        val generatedTokens = loginService.loginByEmailAndPassword(Email(reqBody.email), reqBody.password.trim())

        refreshTokenCookieSetter.setRefreshTokenOnCookie(response, generatedTokens.refreshToken)

        return GlobalResponse.createResponse(LoginResponse(generatedTokens.accessToken.token))
    }
}
