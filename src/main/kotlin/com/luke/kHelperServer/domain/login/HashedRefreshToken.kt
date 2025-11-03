package com.luke.kHelperServer.domain.login

import kotlinx.serialization.Serializable

@Serializable
data class HashedRefreshToken(
    val hash: String
)

