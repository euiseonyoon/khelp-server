package com.luke.kHelperServer.adapter.primary.security

import com.luke.kHelperServer.adapter.primary.webapi.v1.V1_LOGIN_URL
import com.luke.kHelperServer.adapter.primary.webapi.v1.V1_REFRESH_TOKEN_URL

val LOGIN_ENDPOINTS = listOf(V1_LOGIN_URL)

val REFRESH_TOKEN_ENDPOINTS = listOf(V1_REFRESH_TOKEN_URL)

val SWAGGER_ENDPOINTS = listOf(
    "/v3/api-docs/**",
    "/swagger-ui",
    "/swagger-ui/**",
    "/swagger-ui.html",
    "/swagger-resources/**",
    "/webjars/**",
    "/favicon**",
)

val PERMIT_ALL_ENDPOINTS = listOf("/") + REFRESH_TOKEN_ENDPOINTS + SWAGGER_ENDPOINTS + LOGIN_ENDPOINTS
