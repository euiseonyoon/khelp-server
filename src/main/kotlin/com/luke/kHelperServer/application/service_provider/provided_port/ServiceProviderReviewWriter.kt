package com.luke.kHelperServer.application.service_provider.provided_port

import com.luke.kHelperServer.application.service_provider.dto.ServiceProviderReviewDto
import com.luke.kHelperServer.domain.service_provider.request.ServiceProviderReviewCreateRequest

interface ServiceProviderReviewWriter {
    fun addReview(request: ServiceProviderReviewCreateRequest, accountId: Long): ServiceProviderReviewDto
}
