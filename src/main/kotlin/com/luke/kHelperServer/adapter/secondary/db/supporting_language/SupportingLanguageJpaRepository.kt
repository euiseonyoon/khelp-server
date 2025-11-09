package com.luke.kHelperServer.adapter.secondary.db.supporting_language

import com.luke.kHelperServer.domain.supporting_language.write.SupportingLanguage
import org.springframework.data.jpa.repository.JpaRepository

interface SupportingLanguageJpaRepository: JpaRepository<SupportingLanguage, Long> {
}
