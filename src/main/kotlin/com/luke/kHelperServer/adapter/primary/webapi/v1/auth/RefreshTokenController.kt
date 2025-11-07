package com.luke.kHelperServer.adapter.primary.webapi.v1.auth

import com.luke.kHelperServer.adapter.primary.webapi.v1.V1_REFRESH_TOKEN_URL
import com.luke.kHelperServer.application.auth.refresh_token_security.provided_port.RefreshTokenService
import com.luke.kHelperServer.common.GlobalResponse
import com.luke.kHelperServer.domain.login.RefreshTokenResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(V1_REFRESH_TOKEN_URL)
class RefreshTokenController(
    private val refreshTokenService: RefreshTokenService,
) {
    @PostMapping
    fun refreshToken(
        request: HttpServletRequest,
        response: HttpServletResponse,
    ): ResponseEntity<GlobalResponse<RefreshTokenResponse>> {
        val newAccessToken = refreshTokenService.executeTokenRotation(request, response)
        return GlobalResponse.createResponse(RefreshTokenResponse(newAccessToken.token))
    }
}
