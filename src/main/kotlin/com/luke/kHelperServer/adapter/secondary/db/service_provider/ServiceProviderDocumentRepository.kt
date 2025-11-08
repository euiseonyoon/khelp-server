package com.luke.kHelperServer.adapter.secondary.db.service_provider

import com.luke.kHelperServer.application.db_synchronizer.required_port.DocumentRepository
import com.luke.kHelperServer.domain.service_provider.read.ServiceProviderDocument
import org.springframework.stereotype.Service

@Service
class ServiceProviderDocumentRepository(
    private val serviceProviderMongoRepository: ServiceProviderMongoRepository
): DocumentRepository<ServiceProviderDocument> {
    override fun save(document: ServiceProviderDocument) {
        serviceProviderMongoRepository.save(document)
    }

    override fun findByWriteEntityId(writeEntityId: Long): ServiceProviderDocument? {
        return serviceProviderMongoRepository.findByServiceProviderId(writeEntityId)
    }

    override fun deleteByWriteEntityId(writeEntityId: Long) {
        return serviceProviderMongoRepository.deleteByServiceProviderId(writeEntityId)
    }
}
