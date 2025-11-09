package com.luke.kHelperServer.adapter.secondary.db.supporting_language

import com.luke.kHelperServer.domain.supporting_language.Language
import com.luke.kHelperServer.domain.supporting_language.write.SupportingLanguage

interface SupportingLanguageRepositoryExtension {
    fun findAllByLanguages(languages: List<Language>): List<SupportingLanguage>
}