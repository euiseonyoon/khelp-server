package com.luke.kHelperServer.application.service_provider.required_port

import com.luke.kHelperServer.domain.service_provider.read.ServiceProviderView
import org.springframework.data.domain.Page

interface ServiceProviderQueryRepository {
    fun getApprovedServiceProviders(
        perPage: Int,
        pageNumber: Int,
        languageId: Long? = null,
        level: String? = null
    ): Page<ServiceProviderView>
}
