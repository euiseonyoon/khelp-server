package com.luke.kHelperServer.application.service_provider.required_port

import com.luke.kHelperServer.domain.service_provider.write.ServiceProvider
import com.luke.kHelperServer.domain.service_provider.write.ServiceProviderReview

interface ServiceProviderReviewCommandRepository {

    fun addReview(serviceProvider: ServiceProvider, review: String, rating: Int, accountId: Long): ServiceProviderReview
}