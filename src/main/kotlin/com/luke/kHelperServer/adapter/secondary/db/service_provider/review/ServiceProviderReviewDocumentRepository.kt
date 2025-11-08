package com.luke.kHelperServer.adapter.secondary.db.service_provider.review

import com.luke.kHelperServer.application.db_synchronizer.required_port.DocumentRepository
import com.luke.kHelperServer.domain.service_provider.read.ServiceProviderReviewDocument
import org.springframework.stereotype.Service

@Service
class ServiceProviderReviewDocumentRepository(
    private val providingServiceMongoRepository: ServiceProviderReviewMongoRepository
): DocumentRepository<ServiceProviderReviewDocument> {
    override fun save(document: ServiceProviderReviewDocument) {
        providingServiceMongoRepository.save(document)
    }

    override fun findByWriteEntityId(writeEntityId: Long): ServiceProviderReviewDocument? {
        return providingServiceMongoRepository.findByServiceProviderReviewId(writeEntityId)
    }

    override fun deleteByWriteEntityId(writeEntityId: Long) {
        return providingServiceMongoRepository.deleteByServiceProviderReviewId(writeEntityId)
    }
}
