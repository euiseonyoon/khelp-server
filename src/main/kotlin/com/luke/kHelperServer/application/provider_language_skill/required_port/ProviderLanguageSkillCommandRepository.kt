package com.luke.kHelperServer.application.provider_language_skill.required_port

import com.luke.kHelperServer.domain.provider_language_skill.write.ProviderLanguageSkill
import com.luke.kHelperServer.domain.service_provider.write.ServiceProvider
import com.luke.kHelperServer.domain.supporting_language.LanguageLevel
import com.luke.kHelperServer.domain.supporting_language.write.SupportingLanguage

interface ProviderLanguageSkillCommandRepository {

    fun addLanguageSkill(
        serviceProvider: ServiceProvider,
        language: SupportingLanguage,
        level: LanguageLevel
    ): ProviderLanguageSkill

    fun deleteLanguageSkill(providerLanguageSkillId: Long)
}
