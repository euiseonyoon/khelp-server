package com.luke.kHelperServer.application.providing_service.provided_port

import com.luke.kHelperServer.domain.providing_service.read.ProvidingServiceView
import jakarta.validation.constraints.Min
import org.springframework.data.domain.Page
import org.springframework.validation.annotation.Validated

@Validated
interface ProvidingServiceReader {
    fun getProvidingServices(
        @Min(1) perPage: Int,
        @Min(0) pageNumber: Int,
    ): Page<ProvidingServiceView>

    fun getByServiceProvider(
        @Min(1) perPage: Int,
        @Min(0) pageNumber: Int,
        serviceProviderId: Long,
    ): Page<ProvidingServiceView>
}
