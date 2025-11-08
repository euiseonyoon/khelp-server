package com.luke.kHelperServer.domain.service_provider.read


data class ServiceProviderReviewView(
    val serviceProviderReviewId: Long,
    val review: String,
    val rating: Int,
)

