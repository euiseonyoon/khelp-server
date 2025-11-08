package com.luke.kHelperServer.application.service_provider.required_port

import com.luke.kHelperServer.domain.service_provider.write.ServiceProvider

interface ServiceProviderCommandRepository {
    fun save(accountId: Long, description: String): ServiceProvider

    fun save(serviceProvider: ServiceProvider): ServiceProvider

    fun findByAccountId(accountId: Long): ServiceProvider?

    fun existsById(serviceProviderId: Long): Boolean

    fun findById(serviceProviderId: Long): ServiceProvider?
}