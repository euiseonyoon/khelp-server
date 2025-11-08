package com.luke.kHelperServer.adapter.secondary.db.service_provider.review

import com.luke.kHelperServer.application.service_provider.required_port.ServiceProviderReviewQueryRepository
import com.luke.kHelperServer.domain.service_provider.read.ServiceProviderReviewDocument
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component

@Component
class ServiceProviderReviewMongodbQueryRepository(
    private val serviceProviderReviewMongoRepository: ServiceProviderReviewMongoRepository
) : ServiceProviderReviewQueryRepository {
    override fun findByServiceProviderId(
        serviceProviderId: Long,
        perPage: Int,
        pageNumber: Int,
    ): Page<ServiceProviderReviewDocument> {
        val pageable = PageRequest.of(
            pageNumber,
            perPage,
            Sort.by(Sort.Direction.DESC, "updated_at")
        )
        return serviceProviderReviewMongoRepository.findByServiceProviderId(serviceProviderId, pageable)
    }

    override fun findReviewByMe(accountId: Long, perPage: Int, pageNumber: Int): Page<ServiceProviderReviewDocument> {
        val pageable = PageRequest.of(
            pageNumber,
            perPage,
            Sort.by(Sort.Direction.DESC, "updated_at")
        )
        return serviceProviderReviewMongoRepository.findByReviewerAccountId(accountId, pageable)
    }
}
