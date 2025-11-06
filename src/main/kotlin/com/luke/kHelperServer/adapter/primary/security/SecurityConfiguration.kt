package com.luke.kHelperServer.adapter.primary.security

import com.luke.kHelperServer.domain.authority.ROLE_ADMIN
import com.luke.kHelperServer.domain.authority.ROLE_SUPER_ADMIN
import com.luke.kHelperServer.domain.authority.ROLE_USER
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.access.hierarchicalroles.RoleHierarchy
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfiguration(
    private val jwtFilter: JwtFilter
) {
    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val config = CorsConfiguration().apply {
            allowedOriginPatterns = listOf(
                "http://localhost:*",        // 로컬 개발
                "https://*.yourdomain.com"   // 프로덕션 서브도메인
            )

            allowedMethods = listOf("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
            allowedHeaders = listOf("*")
            allowCredentials = true
            maxAge = 3600L
        }
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", config)
        return source
    }

    @Bean
    fun roleHierarchy(): RoleHierarchy {
        return RoleHierarchyImpl.fromHierarchy(
            """
            $ROLE_SUPER_ADMIN > $ROLE_ADMIN
            $ROLE_ADMIN > $ROLE_USER
            """.trimIndent()
        )
    }

    private fun makeBaseHttpSecurity(http: HttpSecurity): HttpSecurity {
        http.csrf { it.disable() }
            .cors { cors -> cors.configurationSource(corsConfigurationSource()) }
            .sessionManagement { session ->
                // Jwt를 사용하여 인증/인가 를 하기때문에 추가
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
        return http
    }

    /**
     * NOTE:
     *
     * 이렇게 모든걸 다 허용하는 필터체인을 따로 만드는게 더 효율적인 것 같다.
     *
     * 한개의 필터체인에
     *     .authorizeHttpRequests { auth ->
     *         auth
     *             .requestMatchers(*PERMIT_ALL_END_POINTS.toTypedArray()).permitAll()
     *             .requestMatchers("/admin/`**").hasRole(ADMIN_NAME)
     *             .anyRequest().authenticated()
     *     }
     *  처럼 넣어 놓는다면 허용되는 request도 결국 인증/인가를 담당하는 필터를 거치게 된다.
     * */
    @Bean
    @Order(1)
    fun permitAllFilterChain(http: HttpSecurity): SecurityFilterChain {
        return makeBaseHttpSecurity(http)
            .securityMatcher(*PERMIT_ALL_ENDPOINTS.toTypedArray())
            .authorizeHttpRequests { it.anyRequest().permitAll() }
            .build()
    }

    @Bean
    @Order(2)
    fun jwtAuthenticationFilterChain(http: HttpSecurity): SecurityFilterChain {
        return makeBaseHttpSecurity(http)
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
            .authorizeHttpRequests { auth ->
                auth.anyRequest().authenticated()
            }
            .build()
    }

}
