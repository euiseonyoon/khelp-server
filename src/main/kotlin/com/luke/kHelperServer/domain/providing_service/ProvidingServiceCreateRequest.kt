package com.luke.kHelperServer.domain.providing_service

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty

data class ProvidingServiceCreateRequest(
    @field:Min(0)
    val price: Int,
    @field:NotEmpty
    val description: String,
)

