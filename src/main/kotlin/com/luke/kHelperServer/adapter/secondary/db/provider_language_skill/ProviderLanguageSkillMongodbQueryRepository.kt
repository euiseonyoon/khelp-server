package com.luke.kHelperServer.adapter.secondary.db.provider_language_skill

import com.luke.kHelperServer.application.provider_language_skill.required_port.ProviderLanguageSkillQueryRepository
import com.luke.kHelperServer.domain.provider_language_skill.read.ProviderLanguageSkillDocument
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component

@Component
class ProviderLanguageSkillMongodbQueryRepository(
    private val providerLanguageSkillMongoRepository: ProviderLanguageSkillMongoRepository
) : ProviderLanguageSkillQueryRepository {

    override fun findByServiceProviderId(
        serviceProviderId: Long,
        perPage: Int,
        pageNumber: Int
    ): Page<ProviderLanguageSkillDocument> {
        val pageable = PageRequest.of(
            pageNumber,
            perPage,
            Sort.by(Sort.Direction.DESC, "updated_at")
        )
        return providerLanguageSkillMongoRepository.findByServiceProviderId(serviceProviderId, pageable)
    }

    override fun findByLanguageId(
        languageId: Long,
        perPage: Int,
        pageNumber: Int
    ): Page<ProviderLanguageSkillDocument> {
        val pageable = PageRequest.of(
            pageNumber,
            perPage,
            Sort.by(Sort.Direction.DESC, "updated_at")
        )
        return providerLanguageSkillMongoRepository.findByLanguageIdAndServiceProviderApproved(
            languageId = languageId,
            serviceProviderApproved = true,
            pageable = pageable
        )
    }
}
