package com.luke.kHelperServer.domain.service_provider.read

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
    var updatedAt: Date
)

