package com.luke.kHelperServer.adapter.secondary.db.service_provider

import com.luke.kHelperServer.application.service_provider.required_port.ServiceProviderQueryRepository
import org.springframework.stereotype.Component

@Component
class ServiceProviderMongodbQueryRepository(
    private val serviceProviderMongoRepository: ServiceProviderMongoRepository
) : ServiceProviderQueryRepository {
}
