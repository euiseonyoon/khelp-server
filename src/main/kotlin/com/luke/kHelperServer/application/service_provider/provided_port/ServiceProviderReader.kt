package com.luke.kHelperServer.application.service_provider.provided_port

import com.luke.kHelperServer.domain.service_provider.read.ServiceProviderView
import org.springframework.data.domain.Page

interface ServiceProviderReader {
    fun getApprovedServiceProviders(perPage: Int, pageNumber: Int): Page<ServiceProviderView>
}