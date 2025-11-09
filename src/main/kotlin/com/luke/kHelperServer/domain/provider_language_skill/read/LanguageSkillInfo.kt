package com.luke.kHelperServer.domain.provider_language_skill.read

import com.luke.kHelperServer.domain.supporting_language.LanguageLevel

data class LanguageSkillInfo(
    val languageName: String,
    val level: LanguageLevel
)
