package com.luke.kHelperServer.application.providing_service.required_port

import com.luke.kHelperServer.domain.providing_service.write.ProvidingService
import com.luke.kHelperServer.domain.service_provider.write.ServiceProvider

interface ProvidingServiceCommandRepository {

    fun addNewService(price: Int, description: String, serviceProvider: ServiceProvider): ProvidingService

    fun deleteService(providingServiceId: Long, accountId: Long)

}
