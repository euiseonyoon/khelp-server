package com.luke.kHelperServer.adapter.primary.webapi.v1.service_provider

import com.luke.kHelperServer.adapter.primary.webapi.v1.V1_SERVICE_PROVIDER_URL
import com.luke.kHelperServer.application.service_provider.dto.ServiceProviderDto
import com.luke.kHelperServer.application.service_provider.provided_port.ServiceProviderReader
import com.luke.kHelperServer.application.service_provider.provided_port.ServiceProviderWriter
import com.luke.kHelperServer.common.GlobalResponse
import com.luke.kHelperServer.common.PageResult
import com.luke.kHelperServer.domain.service_provider.read.ServiceProviderView
import com.luke.kHelperServer.domain.service_provider.request.ServiceProviderRegisterRequest
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(V1_SERVICE_PROVIDER_URL)
class ServiceProviderController(
    private val serviceProviderWriter: ServiceProviderWriter,
    private val serviceProviderReader: ServiceProviderReader,
) {
    @PostMapping
    fun register(
        @RequestBody(required = true) request: ServiceProviderRegisterRequest,
        @AuthenticationPrincipal accountId: Long,
    ): ResponseEntity<GlobalResponse<ServiceProviderDto>> {
        return serviceProviderWriter.register(request, accountId).let {
            GlobalResponse.createResponse(it)
        }
    }

    @GetMapping
    fun getApprovedServiceProviders(
        @RequestParam(required = true) perPage: Int,
        @RequestParam(required = true) pageNumber: Int,
        @RequestParam(required = false) languageId: Long?,
        @RequestParam(required = false) level: String?
    ):  ResponseEntity<GlobalResponse<PageResult<ServiceProviderView>>> {
        return serviceProviderReader.getApprovedServiceProviders(perPage, pageNumber, languageId, level).let {
            GlobalResponse.createResponse(PageResult.fromPage(it))
        }
    }
}
