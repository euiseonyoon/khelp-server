package com.luke.kHelperServer.adapter.primary.webapi.v1.service_provider

import com.luke.kHelperServer.adapter.primary.webapi.v1.V1_SERVICE_PROVIDER_REVIEW_URL
import com.luke.kHelperServer.application.service_provider.provided_port.ServiceProviderReviewReader
import com.luke.kHelperServer.application.service_provider.provided_port.ServiceProviderReviewWriter
import com.luke.kHelperServer.common.GlobalResponse
import com.luke.kHelperServer.common.PageResult
import com.luke.kHelperServer.domain.service_provider.read.ServiceProviderReviewView
import com.luke.kHelperServer.domain.service_provider.request.ServiceProviderReviewCreateRequest
import com.luke.kHelperServer.domain.service_provider.write.ServiceProviderReview
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(V1_SERVICE_PROVIDER_REVIEW_URL)
class ServiceReviewController(
    private val serviceProviderReviewWriter: ServiceProviderReviewWriter,
    private val serviceProviderReviewReader: ServiceProviderReviewReader,
) {
    @PostMapping
    fun addReview(
        @RequestBody(required = true) request: ServiceProviderReviewCreateRequest,
        @AuthenticationPrincipal accountId: Long
    ): ResponseEntity<GlobalResponse<ServiceProviderReview>> {
        return serviceProviderReviewWriter.addReview(request, accountId).let {
            GlobalResponse.createResponse(it.review)
        }
    }

    @GetMapping
    fun findReview(
        @RequestParam(required = true) serviceProviderId: Long,
        @RequestParam(required = true) perPage: Int,
        @RequestParam(required = true) pageNumber: Int,
    ): ResponseEntity<GlobalResponse<PageResult<ServiceProviderReviewView>>> {
        return serviceProviderReviewReader.findByServiceProviderId(serviceProviderId, perPage, pageNumber).let {
            GlobalResponse.createResponse(PageResult.fromPage(it))
        }
    }

    @GetMapping
    fun findMyReview(
        @RequestParam(required = true) perPage: Int,
        @RequestParam(required = true) pageNumber: Int,
        @AuthenticationPrincipal accountId: Long,
    ): ResponseEntity<GlobalResponse<PageResult<ServiceProviderReviewView>>> {
        return serviceProviderReviewReader.findReviewByMe(accountId, perPage, pageNumber).let {
            GlobalResponse.createResponse(PageResult.fromPage(it))
        }
    }
}
