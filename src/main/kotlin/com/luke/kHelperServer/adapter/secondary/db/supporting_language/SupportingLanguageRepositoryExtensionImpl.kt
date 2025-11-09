package com.luke.kHelperServer.adapter.secondary.db.supporting_language

import com.luke.kHelperServer.domain.supporting_language.Language
import com.luke.kHelperServer.domain.supporting_language.write.QSupportingLanguage
import com.luke.kHelperServer.domain.supporting_language.write.SupportingLanguage
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class SupportingLanguageRepositoryExtensionImpl(
    private val queryFactory: JPAQueryFactory,
): SupportingLanguageRepositoryExtension {
    private val supportingLanguage = QSupportingLanguage.supportingLanguage

    override fun findAllByLanguages(languages: List<Language>): List<SupportingLanguage> {
        val names = languages.map { it.name.uppercase() }

        return queryFactory.selectFrom(supportingLanguage)
            .where(supportingLanguage.language.name.`in`(names))
            .fetch()
    }

}
