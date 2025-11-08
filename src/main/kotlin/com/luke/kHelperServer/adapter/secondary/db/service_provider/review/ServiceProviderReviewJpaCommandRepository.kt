package com.luke.kHelperServer.adapter.secondary.db.service_provider.review

import com.luke.kHelperServer.application.service_provider.required_port.ServiceProviderReviewCommandRepository
import com.luke.kHelperServer.domain.service_provider.write.ServiceProvider
import com.luke.kHelperServer.domain.service_provider.write.ServiceProviderReview
import org.springframework.stereotype.Component

@Component
class ServiceProviderReviewJpaCommandRepository(
    private val serviceProviderReviewJpaRepository: ServiceProviderReviewJapRepository
) : ServiceProviderReviewCommandRepository {
    override fun addReview(serviceProvider: ServiceProvider, review: String, rating: Int, accountId: Long): ServiceProviderReview {
        return serviceProviderReviewJpaRepository.save(ServiceProviderReview(review, accountId, rating, serviceProvider))
    }
}
