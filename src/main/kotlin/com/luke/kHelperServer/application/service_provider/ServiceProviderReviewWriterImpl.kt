package com.luke.kHelperServer.application.service_provider

import com.luke.kHelperServer.application.service_provider.dto.ServiceProviderReviewDto
import com.luke.kHelperServer.application.service_provider.provided_port.ServiceProviderReviewWriter
import com.luke.kHelperServer.application.service_provider.required_port.ServiceProviderCommandRepository
import com.luke.kHelperServer.application.service_provider.required_port.ServiceProviderReviewCommandRepository
import com.luke.kHelperServer.domain.exception.BizException
import com.luke.kHelperServer.domain.exception.ErrorMessages
import com.luke.kHelperServer.domain.service_provider.request.ServiceProviderReviewCreateRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ServiceProviderReviewWriterImpl(
    private val reviewCommandRepository: ServiceProviderReviewCommandRepository,
    private val serviceProviderCommandRepository: ServiceProviderCommandRepository
) : ServiceProviderReviewWriter{

    @Transactional
    override fun addReview(request: ServiceProviderReviewCreateRequest, accountId: Long): ServiceProviderReviewDto {
        val provider = serviceProviderCommandRepository.findById(request.serviceProviderId)
        if (provider != null) {
            return reviewCommandRepository.addReview(provider, request.review, request.rating, accountId).let {
                ServiceProviderReviewDto(it)
            }
        } else {
            throw BizException(ErrorMessages.SERVICE_PROVIDER_NOT_FOUND)
        }
    }
}