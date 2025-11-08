package com.luke.kHelperServer.adapter.secondary.db.providing_service

import com.luke.kHelperServer.domain.providing_service.read.ProvidingServiceDocument
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ProvidingServiceMongoRepository : MongoRepository<ProvidingServiceDocument, String> {
    fun findByProvidingServiceId(providingServiceId: Long): ProvidingServiceDocument?

    fun deleteByProvidingServiceId(providingServiceId: Long)
}
