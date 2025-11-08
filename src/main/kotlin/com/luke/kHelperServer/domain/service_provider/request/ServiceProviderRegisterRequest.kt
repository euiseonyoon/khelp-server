package com.luke.kHelperServer.domain.service_provider.request

import jakarta.validation.constraints.NotEmpty

data class ServiceProviderRegisterRequest(
    @NotEmpty
    val description: String,
)