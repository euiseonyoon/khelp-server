package com.luke.kHelperServer.application.providing_service

import com.luke.kHelperServer.application.providing_service.provided_port.ProvidingServiceReader
import com.luke.kHelperServer.application.providing_service.required_port.ProvidingServiceQueryRepository
import com.luke.kHelperServer.domain.providing_service.read.ProvidingServiceView
import jakarta.validation.constraints.Min
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.validation.annotation.Validated

@Service
@Validated
class ProvidingServiceReaderImpl(
    private val providingServiceQueryRepository: ProvidingServiceQueryRepository
) : ProvidingServiceReader{

    @Transactional(readOnly = true)
    override fun getProvidingServices(
        @Min(1) perPage: Int,
        @Min(0) pageNumber: Int,
    ): Page<ProvidingServiceView> {
        return providingServiceQueryRepository.getProvidingServices(perPage, pageNumber).map { it.toView() }
    }

    @Transactional(readOnly = true)
    override fun getByServiceProvider(
        @Min(1) perPage: Int,
        @Min(0) pageNumber: Int,
        serviceProviderId: Long
    ): Page<ProvidingServiceView> {
        return providingServiceQueryRepository.getByServiceProvider(perPage, pageNumber, serviceProviderId).map { it.toView() }
    }

}
