package com.luke.kHelperServer.adapter.secondary.db.service_provider

import com.luke.kHelperServer.domain.service_provider.read.ServiceProviderDocument
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ServiceProviderMongoRepository : MongoRepository<ServiceProviderDocument, String> {
    fun findByServiceProviderId(serviceProviderId: Long): ServiceProviderDocument?

    fun deleteByServiceProviderId(serviceProviderId: Long)
}
