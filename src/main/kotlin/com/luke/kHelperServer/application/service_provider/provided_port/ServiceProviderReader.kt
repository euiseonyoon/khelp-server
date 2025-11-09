package com.luke.kHelperServer.application.service_provider.provided_port

import com.luke.kHelperServer.domain.service_provider.read.ServiceProviderView
import jakarta.validation.constraints.Min
import org.springframework.data.domain.Page
import org.springframework.validation.annotation.Validated

@Validated
interface ServiceProviderReader {
    fun getApprovedServiceProviders(
        @Min(1) perPage: Int,
        @Min(0) pageNumber: Int,
        languageId: Long? = null,
        level: String? = null
    ): Page<ServiceProviderView>
}