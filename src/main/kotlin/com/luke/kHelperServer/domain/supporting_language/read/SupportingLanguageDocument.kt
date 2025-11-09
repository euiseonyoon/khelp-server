package com.luke.kHelperServer.domain.supporting_language.read

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.CompoundIndex
import org.springframework.data.mongodb.core.index.CompoundIndexes
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.*

@Document(collection = "supporting_language")
@CompoundIndexes(
    CompoundIndex(
        name = "supporting_language_id_idx",
        def = "{'supporting_language_id': 1}",
        unique = true
    ),
)
class SupportingLanguageDocument(
    @Id
    val id: String? = null,

    @Field("supporting_language_id")
    val supportingLanguageId: Long,

    @Field("language")
    val language: String,

    @Field("create_at")
    val createdAt: Date,

    @Field("updated_at")
    val updatedAt: Date,
)
