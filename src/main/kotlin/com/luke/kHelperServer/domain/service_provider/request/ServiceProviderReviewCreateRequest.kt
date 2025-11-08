package com.luke.kHelperServer.domain.service_provider.request

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty

data class ServiceProviderReviewCreateRequest(
    val serviceProviderId: Long,

    @field:NotEmpty
    val review: String,

    @field:Min(1) @field:Max(5)
    val rating: Int
)
