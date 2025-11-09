package com.luke.kHelperServer.application.service_provider.provided_port

import com.luke.kHelperServer.domain.service_provider.read.ServiceProviderReviewView
import jakarta.validation.constraints.Min
import org.springframework.data.domain.Page
import org.springframework.validation.annotation.Validated

@Validated
interface ServiceProviderReviewReader {
    fun findByServiceProviderId(
        serviceProviderId: Long,
        @Min(1) perPage: Int,
        @Min(0) pageNumber: Int,
    ): Page<ServiceProviderReviewView>

    fun findReviewByMe(
        accountId: Long,
        @Min(1) perPage: Int,
        @Min(0) pageNumber: Int,
    ): Page<ServiceProviderReviewView>
}