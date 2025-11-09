package com.luke.kHelperServer.adapter.secondary.db.provider_language_skill

import com.luke.kHelperServer.domain.provider_language_skill.write.ProviderLanguageSkill
import org.springframework.data.jpa.repository.JpaRepository

interface ProviderLanguageSkillJpaRepository : JpaRepository<ProviderLanguageSkill, Long> {
}
