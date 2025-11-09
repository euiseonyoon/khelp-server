package com.luke.kHelperServer.application.service_provider.dto

import com.luke.kHelperServer.domain.provider_language_skill.read.LanguageSkillInfo
import com.luke.kHelperServer.domain.service_provider.write.ServiceProvider

data class ServiceProviderDto(
    val serviceProviderId: Long,
    val description: String,
    val approved: Boolean,
    val languageSkills: List<LanguageSkillInfo>
) {
    companion object {
        fun fromEntity(entity: ServiceProvider): ServiceProviderDto {
            return ServiceProviderDto(
                serviceProviderId = entity.id,
                description = entity.description,
                approved = entity.approved,
                languageSkills = entity.providerLanguageSkills.map { skill ->
                    LanguageSkillInfo(skill.supportingLanguage.language.name, skill.level)
                }
            )
        }
    }
}
