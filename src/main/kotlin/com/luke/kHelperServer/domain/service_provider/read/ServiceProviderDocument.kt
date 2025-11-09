package com.luke.kHelperServer.domain.service_provider.read

import com.luke.kHelperServer.domain.provider_language_skill.read.LanguageSkillInfo
import com.luke.kHelperServer.domain.provider_language_skill.read.ProviderLanguageSkillDocument
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.CompoundIndex
import org.springframework.data.mongodb.core.index.CompoundIndexes
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.*


@Document(collection = "service_provider")
@CompoundIndexes(
    CompoundIndex(name = "account_id_idx", def = "{'account_id': 1}", unique = true),
    CompoundIndex(name = "service_provider_id_idx", def = "{'service_provider_id': 1}", unique = true),
    CompoundIndex(
        name = "approved_updated_at_idx",
        def = "{'approved': 1, 'updated_at': -1}"
    ),
    CompoundIndex(
        name = "approved_language_id_updated_at_idx",
        def = "{'approved': 1, 'language_skills.language_id': 1, 'updated_at': -1}"
    ),
    CompoundIndex(
        name = "approved_language_id_level_updated_at_idx",
        def = "{'approved': 1, 'language_skills.language_id': 1, 'language_skills.level': 1, 'updated_at': -1}"
    )
)
data class ServiceProviderDocument(
    @Id
    val id: String? = null,

    @Field("service_provider_id")
    val serviceProviderId: Long,

    @Field("account_id")
    val accountId: Long,

    @Field("description")
    val description: String,

    @Field("approved")
    var approved: Boolean,

    @Field("created_at")
    val createdAt: Date,

    @Field("updated_at")
    var updatedAt: Date,

    @Field("language_skills")
    val languageSkills: List<ProviderLanguageSkillDocument> = emptyList()
) {
    fun toView(): ServiceProviderView {
        return ServiceProviderView(
            serviceProviderId = this.serviceProviderId,
            description = this.description,
            languageSkills = languageSkills.map { skill ->
                LanguageSkillInfo(
                    languageName = skill.languageName,
                    level = skill.level
                )
            }
        )
    }
}

