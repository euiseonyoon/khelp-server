package com.luke.kHelperServer.application.service_provider.provided_port

import com.luke.kHelperServer.application.service_provider.dto.ServiceProviderDto
import com.luke.kHelperServer.domain.service_provider.request.ServiceProviderRegisterRequest
import jakarta.validation.Valid
import org.springframework.validation.annotation.Validated

@Validated
interface ServiceProviderWriter {
    fun register(@Valid request: ServiceProviderRegisterRequest, accountId: Long): ServiceProviderDto

    fun findByAccountId(accountId: Long): ServiceProviderDto?
}