package com.luke.kHelperServer.adapter.secondary.db.service_provider.review

import com.luke.kHelperServer.domain.service_provider.write.ServiceProviderReview
import org.springframework.data.jpa.repository.JpaRepository

interface ServiceProviderReviewJapRepository : JpaRepository<ServiceProviderReview, Long> {
}