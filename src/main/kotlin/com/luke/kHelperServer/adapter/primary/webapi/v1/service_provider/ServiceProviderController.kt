package com.luke.kHelperServer.adapter.primary.webapi.v1.service_provider

import com.luke.kHelperServer.adapter.primary.webapi.v1.V1_SERVICE_PROVIDER_URL
import com.luke.kHelperServer.application.service_provider.provided_port.ServiceProviderWriter
import com.luke.kHelperServer.common.GlobalResponse
import com.luke.kHelperServer.domain.service_provider.request.ServiceProviderRegisterRequest
import com.luke.kHelperServer.domain.service_provider.write.ServiceProvider
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(V1_SERVICE_PROVIDER_URL)
class ServiceProviderController(
    private val serviceProviderWriter: ServiceProviderWriter
) {
    @PostMapping
    fun register(
        request: ServiceProviderRegisterRequest,
        @AuthenticationPrincipal accountId: Long,
    ): ResponseEntity<GlobalResponse<ServiceProvider>> {
        return serviceProviderWriter.register(request, accountId).let {
            GlobalResponse.createResponse(it.serviceProvider)
        }
    }
}
