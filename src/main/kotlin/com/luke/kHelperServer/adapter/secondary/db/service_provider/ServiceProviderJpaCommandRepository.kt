package com.luke.kHelperServer.adapter.secondary.db.service_provider

import com.luke.kHelperServer.application.service_provider.required_port.ServiceProviderCommandRepository
import org.springframework.stereotype.Component

@Component
class ServiceProviderJpaCommandRepository(
    private val serviceProviderJpaRepository: ServiceProviderJpaRepository,
) : ServiceProviderCommandRepository {

}
