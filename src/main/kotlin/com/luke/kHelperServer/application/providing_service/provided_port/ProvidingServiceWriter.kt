package com.luke.kHelperServer.application.providing_service.provided_port

import com.luke.kHelperServer.application.providing_service.ProvidingServiceDto
import com.luke.kHelperServer.domain.providing_service.ProvidingServiceCreateRequest
import com.luke.kHelperServer.domain.providing_service.ProvidingServiceDeleteRequest
import jakarta.validation.Valid
import org.springframework.validation.annotation.Validated

@Validated
interface ProvidingServiceWriter {

    fun addService(@Valid request: ProvidingServiceCreateRequest, accountId: Long): ProvidingServiceDto

    fun deleteService(request: ProvidingServiceDeleteRequest, accountId: Long)

}
