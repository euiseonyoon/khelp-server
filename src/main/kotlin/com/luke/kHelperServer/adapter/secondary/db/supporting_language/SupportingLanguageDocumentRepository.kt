package com.luke.kHelperServer.adapter.secondary.db.supporting_language

import com.luke.kHelperServer.application.db_synchronizer.required_port.DocumentRepository
import com.luke.kHelperServer.domain.supporting_language.read.SupportingLanguageDocument
import org.springframework.stereotype.Service

@Service
class SupportingLanguageDocumentRepository(
    private val supportingLanguageMongoRepository: SupportingLanguageMongoRepository
) : DocumentRepository<SupportingLanguageDocument> {
    override fun save(document: SupportingLanguageDocument) {
        supportingLanguageMongoRepository.save(document)
    }

    override fun findByWriteEntityId(writeEntityId: Long): SupportingLanguageDocument? {
        return supportingLanguageMongoRepository.findBySupportingLanguageId(writeEntityId)
    }

    override fun deleteByWriteEntityId(writeEntityId: Long) {
        supportingLanguageMongoRepository.deleteBySupportingLanguageId(writeEntityId)
    }
}
