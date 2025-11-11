package com.luke.kHelperServer.domain.service_provider.write

import com.luke.kHelperServer.domain.BaseEntity
import com.luke.kHelperServer.domain.EventType
import com.luke.kHelperServer.domain.provider_language_skill.write.ProviderLanguageSkill
import com.luke.kHelperServer.domain.service_provider.event.ServiceProviderEvent
import com.luke.kHelperServer.domain.supporting_language.LanguageLevel
import com.luke.kHelperServer.domain.supporting_language.write.SupportingLanguage
import jakarta.persistence.*

@Entity
class ServiceProvider(
    accountId: Long,
    description: String,
    approved: Boolean = false,
): BaseEntity() {
    init {
        this.registerEvent(ServiceProviderEvent(EventType.CREATED, this))
    }

    @Id @GeneratedValue
    val id: Long = 0

    @Column(unique = true, nullable = false)
    val accountId: Long = accountId

    @Column(nullable = false)
    var description: String = description
        private set

    @Column(nullable = false)
    var approved: Boolean = approved
        private set

    @OneToMany(mappedBy = "serviceProvider", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var providerLanguageSkills: MutableList<ProviderLanguageSkill> = mutableListOf()
        private set

    fun addLanguageSkill(pair: Pair<SupportingLanguage, LanguageLevel>): ServiceProvider {
        this.registerEvent(ServiceProviderEvent(EventType.UPDATED, this))

        val languageSkill = ProviderLanguageSkill(
            this,
            pair.first,
            pair.second,
        )
        this.providerLanguageSkills.add(languageSkill)
        return this
    }

    fun approve(): ServiceProvider {
        this.registerEvent(ServiceProviderEvent(EventType.UPDATED, this))
        this.approved = true
        return this
    }

    fun updateDescription(description: String): ServiceProvider {
        this.registerEvent(ServiceProviderEvent(EventType.UPDATED, this))
        this.description = description
        return this
    }
}
