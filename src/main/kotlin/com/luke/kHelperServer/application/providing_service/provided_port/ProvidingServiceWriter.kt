package com.luke.kHelperServer.application.providing_service.provided_port

import com.luke.kHelperServer.application.providing_service.ProvidingServiceDto
import com.luke.kHelperServer.domain.providing_service.ProvidingServiceCreateRequest
import com.luke.kHelperServer.domain.providing_service.ProvidingServiceDeleteRequest

interface ProvidingServiceWriter {

    fun addService(request: ProvidingServiceCreateRequest, accountId: Long): ProvidingServiceDto

    fun deleteService(request: ProvidingServiceDeleteRequest, accountId: Long)

}
