package com.luke.kHelperServer.adapter.secondary.db.providing_service

import com.luke.kHelperServer.application.providing_service.required_port.ProvidingServiceCommandRepository
import com.luke.kHelperServer.domain.exception.BizException
import com.luke.kHelperServer.domain.exception.ErrorMessages
import com.luke.kHelperServer.domain.providing_service.write.ProvidingService
import com.luke.kHelperServer.domain.service_provider.write.ServiceProvider
import org.springframework.stereotype.Component

@Component
class ProvidingServiceJpaCommandRepository(
    private val serviceJpaRepository: ProvidingServiceJpaRepository
) : ProvidingServiceCommandRepository{
    override fun addNewService(price: Int, description: String, serviceProvider: ServiceProvider): ProvidingService {
        return serviceJpaRepository.save(ProvidingService(price, description, serviceProvider))
    }

    override fun deleteService(providingServiceId: Long, accountId: Long) {
        val providingService = serviceJpaRepository.findByIdWithServiceProvider(providingServiceId)
            ?: throw BizException(ErrorMessages.PROVIDING_SERVICE_NOT_FOUND)

        if (providingService.serviceProvider.accountId == accountId) {
            // FIX: deleteById는 @EntityListener의 @PostDelete 으로 잡을수 없다.
            // serviceJpaRepository.deleteById(providingServiceId)
            serviceJpaRepository.delete(providingService)
        }
    }
}

