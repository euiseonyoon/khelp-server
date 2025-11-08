package com.luke.kHelperServer.application.service_provider.provided_port

import com.luke.kHelperServer.application.service_provider.dto.ServiceProviderDto
import com.luke.kHelperServer.domain.service_provider.request.ServiceProviderRegisterRequest

interface ServiceProviderWriter {
    fun register(request: ServiceProviderRegisterRequest, accountId: Long): ServiceProviderDto

    fun findByAccountId(accountId: Long): ServiceProviderDto?
}