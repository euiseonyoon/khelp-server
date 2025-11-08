package com.luke.kHelperServer.adapter.secondary.db.providing_service

import com.luke.kHelperServer.application.providing_service.required_port.ProvidingServiceQueryRepository
import com.luke.kHelperServer.domain.providing_service.read.ProvidingServiceDocument
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component

@Component
class ProvidingServiceMongodbQueryRepository(
    private val providingServiceMongoRepository: ProvidingServiceMongoRepository
) : ProvidingServiceQueryRepository{
    override fun getProvidingServices(perPage: Int, pageNumber: Int): Page<ProvidingServiceDocument> {
        val pageable = PageRequest.of(
            pageNumber,
            perPage,
            Sort.by(Sort.Direction.DESC, "updated_at")
        )

        return providingServiceMongoRepository.findAll(pageable)
    }

    override fun getByServiceProvider(
        perPage: Int,
        pageNumber: Int,
        serviceProviderId: Long
    ): Page<ProvidingServiceDocument> {
        val pageable = PageRequest.of(
            pageNumber,
            perPage,
            Sort.by(Sort.Direction.DESC, "updated_at")
        )

        return providingServiceMongoRepository.findByServiceProviderId(serviceProviderId, pageable)
    }
}
