package com.luke.kHelperServer.application.service_provider

import com.luke.kHelperServer.application.service_provider.provided_port.ServiceProviderReader
import com.luke.kHelperServer.application.service_provider.required_port.ServiceProviderQueryRepository
import com.luke.kHelperServer.domain.service_provider.read.ServiceProviderView
import jakarta.validation.constraints.Min
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.validation.annotation.Validated

@Service
@Validated
class ServiceProviderReaderImpl(
    private val serviceProviderQueryRepository: ServiceProviderQueryRepository,
) : ServiceProviderReader{

    @Transactional(readOnly = true)
    override fun getApprovedServiceProviders(
        @Min(1) perPage: Int,
        @Min(0) pageNumber: Int,
    ): Page<ServiceProviderView> {
        return serviceProviderQueryRepository.getApprovedServiceProviders(perPage, pageNumber).map {
            it.toView()
        }
    }
}
