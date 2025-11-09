package com.luke.kHelperServer.application.provider_language_skill.required_port

import com.luke.kHelperServer.domain.provider_language_skill.read.ProviderLanguageSkillDocument
import org.springframework.data.domain.Page

interface ProviderLanguageSkillQueryRepository {
    fun findByServiceProviderId(serviceProviderId: Long, perPage: Int, pageNumber: Int): Page<ProviderLanguageSkillDocument>

    fun findByLanguageId(languageId: Long, perPage: Int, pageNumber: Int): Page<ProviderLanguageSkillDocument>
}
