package com.luke.kHelperServer.adapter.secondary.db.service_provider.review

import com.luke.kHelperServer.domain.service_provider.read.ServiceProviderReviewDocument
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ServiceProviderReviewMongoRepository: MongoRepository<ServiceProviderReviewDocument, String> {

    fun findByServiceProviderReviewId(serviceProviderReviewId: Long): ServiceProviderReviewDocument?

    fun deleteByServiceProviderReviewId(serviceProviderReviewId: Long)

    fun findByServiceProviderId(serviceProviderId: Long, pageable: Pageable): Page<ServiceProviderReviewDocument>

    fun findByReviewerAccountId(reviewerAccountId: Long, pageable: Pageable): Page<ServiceProviderReviewDocument>
}