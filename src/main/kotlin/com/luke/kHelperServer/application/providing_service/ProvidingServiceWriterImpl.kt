package com.luke.kHelperServer.application.providing_service

import com.luke.kHelperServer.application.providing_service.provided_port.ProvidingServiceWriter
import com.luke.kHelperServer.application.providing_service.required_port.ProvidingServiceCommandRepository
import com.luke.kHelperServer.application.service_provider.required_port.ServiceProviderCommandRepository
import com.luke.kHelperServer.domain.exception.BizException
import com.luke.kHelperServer.domain.exception.ErrorMessages
import com.luke.kHelperServer.domain.providing_service.ProvidingServiceCreateRequest
import com.luke.kHelperServer.domain.providing_service.ProvidingServiceDeleteRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProvidingServiceWriterImpl(
    private val providingServiceCommandRepository: ProvidingServiceCommandRepository,
    private val serviceProviderCommandRepository: ServiceProviderCommandRepository
) : ProvidingServiceWriter{
    @Transactional
    override fun addService(request: ProvidingServiceCreateRequest, accountId: Long): ProvidingServiceDto {
        val serviceProvider = serviceProviderCommandRepository.findByAccountId(accountId) ?:
            throw BizException(ErrorMessages.SERVICE_PROVIDER_ACCOUNT_NOT_FOUND)

        return providingServiceCommandRepository.addNewService(
            request.price,
            request.description,
            serviceProvider
        ).let {
            ProvidingServiceDto(it)
        }
    }

    @Transactional
    override fun deleteService(request: ProvidingServiceDeleteRequest, accountId: Long) {
        return providingServiceCommandRepository.deleteService(request.providingServiceId, accountId)
    }
}