package com.luke.kHelperServer.domain.provider_language_skill.read

data class ProviderLanguageSkillView(
    val serviceProviderId: Long,
    val languageName: String,
    val level: String
)
