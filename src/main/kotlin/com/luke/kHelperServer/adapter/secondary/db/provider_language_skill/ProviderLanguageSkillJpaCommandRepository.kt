package com.luke.kHelperServer.adapter.secondary.db.provider_language_skill

import com.luke.kHelperServer.application.provider_language_skill.required_port.ProviderLanguageSkillCommandRepository
import com.luke.kHelperServer.domain.provider_language_skill.write.ProviderLanguageSkill
import com.luke.kHelperServer.domain.service_provider.write.ServiceProvider
import com.luke.kHelperServer.domain.supporting_language.LanguageLevel
import com.luke.kHelperServer.domain.supporting_language.write.SupportingLanguage
import org.springframework.stereotype.Component

@Component
class ProviderLanguageSkillJpaCommandRepository(
    private val providerLanguageSkillJpaRepository: ProviderLanguageSkillJpaRepository
) : ProviderLanguageSkillCommandRepository {

    override fun addLanguageSkill(
        serviceProvider: ServiceProvider,
        language: SupportingLanguage,
        level: LanguageLevel
    ): ProviderLanguageSkill {
        return providerLanguageSkillJpaRepository.save(
            ProviderLanguageSkill(serviceProvider, language, level)
        )
    }

    override fun deleteLanguageSkill(providerLanguageSkillId: Long) {
        providerLanguageSkillJpaRepository.deleteById(providerLanguageSkillId)
    }
}
