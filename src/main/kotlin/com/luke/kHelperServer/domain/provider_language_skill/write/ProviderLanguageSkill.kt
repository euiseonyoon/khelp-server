package com.luke.kHelperServer.domain.provider_language_skill.write

import com.luke.kHelperServer.domain.BaseEntity
import com.luke.kHelperServer.domain.service_provider.write.ServiceProvider
import com.luke.kHelperServer.domain.supporting_language.LanguageLevel
import com.luke.kHelperServer.domain.supporting_language.write.SupportingLanguage
import jakarta.persistence.*

@Entity
@Table(
    name = "provider_language_skill",
    uniqueConstraints = [
        UniqueConstraint(columnNames = ["service_provider_id", "language_id"])
    ]
)
@EntityListeners(ProviderLanguageSkillEntityListener::class)
class ProviderLanguageSkill(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    val serviceProvider: ServiceProvider,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    val supportingLanguage: SupportingLanguage,

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    val level: LanguageLevel
): BaseEntity() {
    @Id @GeneratedValue
    val id: Long = 0
}
