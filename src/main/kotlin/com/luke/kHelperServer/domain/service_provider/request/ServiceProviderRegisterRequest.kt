package com.luke.kHelperServer.domain.service_provider.request

import jakarta.validation.constraints.NotEmpty

data class ServiceProviderRegisterRequest(
    @field:NotEmpty
    val description: String,
)