package com.luke.kHelperServer.adapter.primary.security

import com.luke.kHelperServer.application.auth.jwt.provided_port.JwtTokenService
import com.luke.kHelperServer.domain.login.AccessToken
import io.jsonwebtoken.JwtException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtFilter(
    private val jwtTokenService: JwtTokenService,
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val accessToken = getAccessTokenFromHeader(request)

            if (accessToken != null) {
                val claims = jwtTokenService.parseAccessToken(accessToken)
                val accountId = jwtTokenService.parseAccountIdFromAccessTokenClaims(claims)
                val email = jwtTokenService.parseEmailFromAccessTokenClaim(claims)
                val authority = jwtTokenService.parseRoleFromAccessTokenClaims(claims).let {
                    SimpleGrantedAuthority(it.name)
                }
                val authentication = AccountAuthenticationToken(accountId, listOf(authority), email).apply {
                    this.isAuthenticated = true
                }

                SecurityContextHolder.getContext().authentication = authentication
            }

        } catch (e: JwtException) {
            handleAuthenticationFailure(response, "Invalid JWT token: ${e.message}")
            return
        } catch (e: Exception) {
            logger.error("Exception during JWT authentication", e)
            handleAuthenticationFailure(response, "Authentication failed")
            return
        }

        filterChain.doFilter(request, response)
    }

    private fun handleAuthenticationFailure(
        response: HttpServletResponse,
        exceptionMessage: String
    ) {
        response.status = HttpStatus.UNAUTHORIZED.value()
        response.contentType = "application/json"
        response.writer.write(
            """{"error": "Unauthorized", "message": "$exceptionMessage"}"""
        )
    }

    private fun getAccessTokenFromHeader(request: HttpServletRequest): AccessToken? {
        val bearer = request.getHeader("Authorization")
        return if (bearer != null && bearer.startsWith("Bearer ")) {
            AccessToken(bearer.substring(7))
        } else {
            null
        }
    }
}
