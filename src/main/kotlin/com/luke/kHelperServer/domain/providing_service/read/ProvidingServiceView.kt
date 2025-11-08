package com.luke.kHelperServer.domain.providing_service.read

data class ProvidingServiceView (
    val providingServiceId: Long,
    val description: String,
    val price: Int,
    val serviceProviderId: Long,
)
