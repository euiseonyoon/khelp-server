package com.luke.kHelperServer.adapter.secondary.db.providing_service

import com.luke.kHelperServer.application.db_synchronizer.required_port.DocumentRepository
import com.luke.kHelperServer.domain.providing_service.read.ProvidingServiceDocument
import org.springframework.stereotype.Service

@Service
class ProvidingServiceDocumentRepository(
    private val providingServiceMongoRepository: ProvidingServiceMongoRepository
): DocumentRepository<ProvidingServiceDocument> {
    override fun save(document: ProvidingServiceDocument) {
        providingServiceMongoRepository.save(document)
    }

    override fun findByWriteEntityId(writeEntityId: Long): ProvidingServiceDocument? {
        return providingServiceMongoRepository.findByProvidingServiceId(writeEntityId)
    }

    override fun deleteByWriteEntityId(writeEntityId: Long) {
        return providingServiceMongoRepository.deleteByProvidingServiceId(writeEntityId)
    }
}
