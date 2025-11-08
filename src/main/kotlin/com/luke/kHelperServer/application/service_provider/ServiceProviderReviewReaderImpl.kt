package com.luke.kHelperServer.application.service_provider

import com.luke.kHelperServer.application.service_provider.provided_port.ServiceProviderReviewReader
import com.luke.kHelperServer.application.service_provider.required_port.ServiceProviderReviewQueryRepository
import com.luke.kHelperServer.domain.service_provider.read.ServiceProviderReviewView
import jakarta.validation.constraints.Min
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.validation.annotation.Validated

@Service
@Validated
class ServiceProviderReviewReaderImpl(
    private val serviceProviderReviewQueryRepository: ServiceProviderReviewQueryRepository
) : ServiceProviderReviewReader {

    @Transactional(readOnly = true)
    override fun findByServiceProviderId(
        serviceProviderId: Long,
        @Min(1) perPage: Int,
        @Min(0) pageNumber: Int,
    ): Page<ServiceProviderReviewView> {
        return serviceProviderReviewQueryRepository.findByServiceProviderId(serviceProviderId, perPage, pageNumber).map {
            it.toView()
        }
    }

    @Transactional(readOnly = true)
    override fun findReviewByMe(
        accountId: Long,
        @Min(1) perPage: Int,
        @Min(0) pageNumber: Int,
    ): Page<ServiceProviderReviewView> {
        return serviceProviderReviewQueryRepository.findReviewByMe(accountId, perPage, pageNumber).map {
            it.toView()
        }
    }
}
