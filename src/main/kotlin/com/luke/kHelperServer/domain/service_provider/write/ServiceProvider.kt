package com.luke.kHelperServer.domain.service_provider.write

import com.luke.kHelperServer.domain.BaseEntity
import com.luke.kHelperServer.domain.provider_language_skill.write.ProviderLanguageSkill
import com.luke.kHelperServer.domain.supporting_language.LanguageLevel
import com.luke.kHelperServer.domain.supporting_language.write.SupportingLanguage
import jakarta.persistence.*

@Entity
@EntityListeners(ServiceProviderEntityListener::class)
class ServiceProvider(
    @Column(unique = true, nullable = false)
    val accountId: Long,

    @Column(nullable = false)
    val description: String,

    @Column(nullable = false)
    var approved: Boolean = false,
): BaseEntity() {
    @Id
    @GeneratedValue
    val id: Long = 0

    @OneToMany(mappedBy = "serviceProvider", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var providerLanguageSkills: MutableList<ProviderLanguageSkill> = mutableListOf()
        private set

    fun addLanguageSkill(pair: Pair<SupportingLanguage, LanguageLevel>): ServiceProvider {
        val languageSkill = ProviderLanguageSkill(
            this,
            pair.first,
            pair.second,
        )
        this.providerLanguageSkills.add(languageSkill)
        return this
    }

    fun approve(): ServiceProvider {
        this.approved = true
        return this
    }
}
