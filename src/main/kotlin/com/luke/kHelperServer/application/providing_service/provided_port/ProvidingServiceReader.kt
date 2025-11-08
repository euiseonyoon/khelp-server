package com.luke.kHelperServer.application.providing_service.provided_port

import com.luke.kHelperServer.domain.providing_service.read.ProvidingServiceView
import org.springframework.data.domain.Page

interface ProvidingServiceReader {
    fun getProvidingServices(perPage: Int, pageNumber: Int): Page<ProvidingServiceView>

    fun getByServiceProvider(perPage: Int, pageNumber: Int, serviceProviderId: Long): Page<ProvidingServiceView>
}
