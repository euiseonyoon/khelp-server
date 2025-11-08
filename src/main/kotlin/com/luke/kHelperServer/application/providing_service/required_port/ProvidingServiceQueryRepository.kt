package com.luke.kHelperServer.application.providing_service.required_port

import com.luke.kHelperServer.domain.providing_service.read.ProvidingServiceDocument
import org.springframework.data.domain.Page

interface ProvidingServiceQueryRepository {
    fun getProvidingServices(perPage: Int, pageNumber: Int): Page<ProvidingServiceDocument>

    fun getByServiceProvider(perPage: Int, pageNumber: Int, serviceProviderId: Long): Page<ProvidingServiceDocument>
}
