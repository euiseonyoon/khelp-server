package com.luke.kHelperServer.adapter.secondary.db.provider_language_skill

import com.luke.kHelperServer.domain.provider_language_skill.read.ProviderLanguageSkillDocument
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ProviderLanguageSkillMongoRepository: MongoRepository<ProviderLanguageSkillDocument, String> {

    fun findByProviderLanguageSkillId(providerLanguageSkillId: Long): ProviderLanguageSkillDocument?

    fun deleteByProviderLanguageSkillId(providerLanguageSkillId: Long)

    fun findByServiceProviderId(serviceProviderId: Long, pageable: Pageable): Page<ProviderLanguageSkillDocument>

    fun findByLanguageIdAndServiceProviderApproved(
        languageId: Long,
        serviceProviderApproved: Boolean,
        pageable: Pageable
    ): Page<ProviderLanguageSkillDocument>
}
