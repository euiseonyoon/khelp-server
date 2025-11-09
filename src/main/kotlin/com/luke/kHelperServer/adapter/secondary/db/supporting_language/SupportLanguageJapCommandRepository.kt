package com.luke.kHelperServer.adapter.secondary.db.supporting_language

import com.luke.kHelperServer.application.supporting_language.required_port.SupportingLanguageCommandRepository
import com.luke.kHelperServer.domain.supporting_language.Language
import com.luke.kHelperServer.domain.supporting_language.write.SupportingLanguage
import org.springframework.stereotype.Service

@Service
class SupportLanguageJapCommandRepository(
    private val supportingLanguageJpaRepository: SupportingLanguageJpaRepository
) : SupportingLanguageCommandRepository {
    override fun findAllByLanguages(languages: List<Language>): List<SupportingLanguage> {
        return supportingLanguageJpaRepository.findAllByLanguages(languages)
    }
}
