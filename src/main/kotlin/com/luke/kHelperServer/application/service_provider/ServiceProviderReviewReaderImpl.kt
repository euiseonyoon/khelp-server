package com.luke.kHelperServer.application.service_provider

import com.luke.kHelperServer.application.service_provider.provided_port.ServiceProviderReviewReader
import com.luke.kHelperServer.application.service_provider.required_port.ServiceProviderReviewQueryRepository
import com.luke.kHelperServer.domain.service_provider.read.ServiceProviderReviewView
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ServiceProviderReviewReaderImpl(
    private val serviceProviderReviewQueryRepository: ServiceProviderReviewQueryRepository
) : ServiceProviderReviewReader {

    @Transactional(readOnly = true)
    override fun findByServiceProviderId(
        serviceProviderId: Long,
        perPage: Int,
        pageNumber: Int,
    ): Page<ServiceProviderReviewView> {
        return serviceProviderReviewQueryRepository.findByServiceProviderId(serviceProviderId, perPage, pageNumber).map {
            it.toView()
        }
    }

    @Transactional(readOnly = true)
    override fun findReviewByMe(
        accountId: Long,
        perPage: Int,
        pageNumber: Int,
    ): Page<ServiceProviderReviewView> {
        return serviceProviderReviewQueryRepository.findReviewByMe(accountId, perPage, pageNumber).map {
            it.toView()
        }
    }
}
