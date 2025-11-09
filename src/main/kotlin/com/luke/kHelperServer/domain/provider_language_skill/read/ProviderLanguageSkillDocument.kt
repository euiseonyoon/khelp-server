package com.luke.kHelperServer.domain.provider_language_skill.read

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.CompoundIndex
import org.springframework.data.mongodb.core.index.CompoundIndexes
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.*

@Document(collection = "provider_language_skill")
@CompoundIndexes(
    CompoundIndex(
        name = "provider_language_skill_id_idx",
        def = "{'provider_language_skill_id': 1}",
        unique = true
    ),
    CompoundIndex(
        name = "language_id_approved_updated_at_idx",
        def = "{'language_id': 1, 'service_provider_approved': 1, 'updated_at': -1}"
    )
)
data class ProviderLanguageSkillDocument(
    @Id
    val id: String? = null,

    @Field("provider_language_skill_id")
    val providerLanguageSkillId: Long,

    @Field("service_provider_id")
    val serviceProviderId: Long,

    @Field("service_provider_approved")
    val serviceProviderApproved: Boolean,

    @Field("language_id")
    val languageId: Long,

    @Field("language_name")
    val languageName: String,

    @Field("level")
    val level: String,

    @Field("created_at")
    val createdAt: Date,

    @Field("updated_at")
    var updatedAt: Date
) {
    fun toView(): ProviderLanguageSkillView {
        return ProviderLanguageSkillView(
            serviceProviderId = this.serviceProviderId,
            languageName = this.languageName,
            level = this.level
        )
    }
}
