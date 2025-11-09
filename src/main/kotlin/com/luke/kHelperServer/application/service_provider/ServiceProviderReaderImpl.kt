package com.luke.kHelperServer.application.service_provider

import com.luke.kHelperServer.application.service_provider.provided_port.ServiceProviderReader
import com.luke.kHelperServer.application.service_provider.required_port.ServiceProviderQueryRepository
import com.luke.kHelperServer.domain.service_provider.read.ServiceProviderView
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ServiceProviderReaderImpl(
    private val serviceProviderQueryRepository: ServiceProviderQueryRepository,
) : ServiceProviderReader{

    @Transactional(readOnly = true)
    override fun getApprovedServiceProviders(
        perPage: Int,
        pageNumber: Int,
        languageId: Long?,
        level: String?
    ): Page<ServiceProviderView> {
        return serviceProviderQueryRepository.getApprovedServiceProviders(perPage, pageNumber, languageId, level)
    }
}
