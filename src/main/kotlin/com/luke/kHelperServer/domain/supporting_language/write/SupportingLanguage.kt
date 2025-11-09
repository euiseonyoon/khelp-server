package com.luke.kHelperServer.domain.supporting_language.write

import com.luke.kHelperServer.domain.BaseEntity
import com.luke.kHelperServer.domain.supporting_language.Language
import jakarta.persistence.*

@Entity
@EntityListeners(SupportingLanguageEntityListener::class)
class SupportingLanguage(
    @Embedded
    @AttributeOverride(
        name = "name",
        column = Column(name = "language", nullable = false, unique = true)
    )
    val language: Language
): BaseEntity() {
    @Id @GeneratedValue
    val id: Long = 0
}

