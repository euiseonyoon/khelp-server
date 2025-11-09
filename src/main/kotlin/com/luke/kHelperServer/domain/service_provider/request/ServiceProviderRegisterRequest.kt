package com.luke.kHelperServer.domain.service_provider.request

import com.luke.kHelperServer.domain.provider_language_skill.read.LanguageSkillInfo
import jakarta.validation.constraints.NotEmpty

data class ServiceProviderRegisterRequest(
    @field:NotEmpty
    val description: String,
    @field:NotEmpty
    val languageSkillInfos: List<LanguageSkillInfo>
)