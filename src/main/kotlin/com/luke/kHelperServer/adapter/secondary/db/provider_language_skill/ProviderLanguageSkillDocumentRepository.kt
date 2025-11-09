package com.luke.kHelperServer.adapter.secondary.db.provider_language_skill

import com.luke.kHelperServer.application.db_synchronizer.required_port.DocumentRepository
import com.luke.kHelperServer.domain.provider_language_skill.read.ProviderLanguageSkillDocument
import org.springframework.stereotype.Service

@Service
class ProviderLanguageSkillDocumentRepository(
    private val providerLanguageSkillMongoRepository: ProviderLanguageSkillMongoRepository
): DocumentRepository<ProviderLanguageSkillDocument> {
    override fun save(document: ProviderLanguageSkillDocument) {
        providerLanguageSkillMongoRepository.save(document)
    }

    override fun findByWriteEntityId(writeEntityId: Long): ProviderLanguageSkillDocument? {
        return providerLanguageSkillMongoRepository.findByProviderLanguageSkillId(writeEntityId)
    }

    override fun deleteByWriteEntityId(writeEntityId: Long) {
        return providerLanguageSkillMongoRepository.deleteByProviderLanguageSkillId(writeEntityId)
    }
}
