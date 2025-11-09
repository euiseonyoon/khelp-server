package com.luke.kHelperServer.application.supporting_language.required_port

import com.luke.kHelperServer.domain.supporting_language.Language
import com.luke.kHelperServer.domain.supporting_language.write.SupportingLanguage

interface SupportingLanguageCommandRepository {
    fun findAllByLanguages(languages: List<Language>): List<SupportingLanguage>
}
