package com.luke.kHelperServer.adapter.secondary.db.service_provider

import com.luke.kHelperServer.application.service_provider.required_port.ServiceProviderQueryRepository
import com.luke.kHelperServer.domain.service_provider.read.ServiceProviderDocument
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component

@Component
class ServiceProviderMongodbQueryRepository(
    private val serviceProviderMongoRepository: ServiceProviderMongoRepository
) : ServiceProviderQueryRepository {

    override fun getApprovedServiceProviders(perPage: Int, pageNumber: Int): Page<ServiceProviderDocument> {
        val pageable = PageRequest.of(
            pageNumber,
            perPage,
            Sort.by(Sort.Direction.DESC, "updated_at")
        )

        return serviceProviderMongoRepository.findByApproved(true, pageable)
    }
}
