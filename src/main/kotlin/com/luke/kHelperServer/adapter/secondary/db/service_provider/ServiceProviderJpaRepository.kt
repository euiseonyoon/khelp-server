package com.luke.kHelperServer.adapter.secondary.db.service_provider

import com.luke.kHelperServer.domain.service_provider.write.ServiceProvider
import org.springframework.data.jpa.repository.JpaRepository

interface ServiceProviderJpaRepository: JpaRepository<ServiceProvider, Long> {

    fun findByAccountId(accountId: Long): ServiceProvider
}
