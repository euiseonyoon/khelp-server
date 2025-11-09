package com.luke.kHelperServer.adapter.secondary.db.service_provider

import com.luke.kHelperServer.application.service_provider.required_port.ServiceProviderCommandRepository
import com.luke.kHelperServer.domain.service_provider.write.ServiceProvider
import org.springframework.stereotype.Component

@Component
class ServiceProviderJpaCommandRepository(
    private val serviceProviderJpaRepository: ServiceProviderJpaRepository,
) : ServiceProviderCommandRepository {
    override fun save(serviceProvider: ServiceProvider): ServiceProvider {
        return serviceProviderJpaRepository.save(serviceProvider)
    }

    override fun findByAccountId(accountId: Long): ServiceProvider? {
        return serviceProviderJpaRepository.findByAccountId(accountId)
    }

    override fun existsById(serviceProviderId: Long): Boolean {
        return serviceProviderJpaRepository.existsById(serviceProviderId)
    }

    override fun findById(serviceProviderId: Long): ServiceProvider? {
        return serviceProviderJpaRepository.findById(serviceProviderId).orElse(null)
    }

}
