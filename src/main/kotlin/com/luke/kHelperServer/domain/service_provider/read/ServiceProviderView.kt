package com.luke.kHelperServer.domain.service_provider.read

import com.luke.kHelperServer.domain.provider_language_skill.read.LanguageSkillInfo

data class ServiceProviderView(
    val serviceProviderId: Long,
    val description: String,
    val languageSkills: List<LanguageSkillInfo>
)
