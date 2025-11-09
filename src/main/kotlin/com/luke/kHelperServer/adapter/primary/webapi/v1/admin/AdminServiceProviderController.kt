package com.luke.kHelperServer.adapter.primary.webapi.v1.admin

import com.luke.kHelperServer.adapter.primary.webapi.v1.V1_ADMIN_SERVICE_PROVIDER_URL
import com.luke.kHelperServer.application.admin.service_provider.provided_port.AdminServiceProviderWriter
import com.luke.kHelperServer.application.service_provider.dto.ServiceProviderDto
import com.luke.kHelperServer.common.GlobalResponse
import com.luke.kHelperServer.domain.service_provider.request.ServiceProviderApproveRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(V1_ADMIN_SERVICE_PROVIDER_URL)
class AdminServiceProviderController(
    private val adminServiceProviderWriter: AdminServiceProviderWriter
) {
    @PutMapping
    fun approve(
        @RequestBody reqBody: ServiceProviderApproveRequest
    ): ResponseEntity<GlobalResponse<ServiceProviderDto>> {
        return adminServiceProviderWriter.approve(reqBody.accountId).let {
            GlobalResponse.createResponse(it)
        }
    }
}
