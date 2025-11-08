package com.luke.kHelperServer.adapter.primary.webapi.v1.providing_service

import com.luke.kHelperServer.adapter.primary.webapi.v1.V1_PROVIDING_SERVICE_URL
import com.luke.kHelperServer.application.providing_service.provided_port.ProvidingServiceWriter
import com.luke.kHelperServer.common.GlobalResponse
import com.luke.kHelperServer.domain.providing_service.ProvidingServiceCreateRequest
import com.luke.kHelperServer.domain.providing_service.ProvidingServiceDeleteRequest
import com.luke.kHelperServer.domain.providing_service.write.ProvidingService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(V1_PROVIDING_SERVICE_URL)
class ProvidingServiceController(
    private val providingServiceWriter: ProvidingServiceWriter
) {

    @PostMapping
    fun createService(
        @RequestBody(required = true) request: ProvidingServiceCreateRequest,
        @AuthenticationPrincipal accountId: Long,
    ): ResponseEntity<GlobalResponse<ProvidingService>> {
        return providingServiceWriter.addService(request, accountId).let {
            GlobalResponse.createResponse(it.providingService)
        }
    }

    @DeleteMapping
    fun deleteService(
        @RequestBody(required = true) request: ProvidingServiceDeleteRequest,
        @AuthenticationPrincipal accountId: Long,
    ): ResponseEntity<GlobalResponse<Unit>>{
        return providingServiceWriter.deleteService(request, accountId).let {
            GlobalResponse.createResponse(it)
        }
    }

}
