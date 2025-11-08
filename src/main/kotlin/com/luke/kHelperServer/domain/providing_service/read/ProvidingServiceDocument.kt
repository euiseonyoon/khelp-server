package com.luke.kHelperServer.domain.providing_service.read

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.CompoundIndex
import org.springframework.data.mongodb.core.index.CompoundIndexes
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.*

@Document(collection = "providing_service")
@CompoundIndexes(
    CompoundIndex(name = "providing_service_id_idx", def = "{'providing_service_id': 1}", unique = true),
    CompoundIndex(
        name = "service_provider_id_updated_at_idx",
        def = "{'service_provider_id': 1, 'updated_at': -1}"
    ),
    CompoundIndex(
        name = "updated_at_idx",
        def = "{'updated_at': -1}"
    )
)
data class ProvidingServiceDocument(
    @Id
    val id: String? = null,

    @Field("providing_service_id")
    val providingServiceId: Long,

    @Field("service_provider_id")
    val serviceProviderId: Long,

    @Field("description")
    val description: String,

    @Field("price")
    val price: Int,

    @Field("created_at")
    val createdAt: Date,

    @Field("updated_at")
    var updatedAt: Date
) {
    fun toView(): ProvidingServiceView {
        return ProvidingServiceView(
            this.providingServiceId,
            this.description,
            this.price,
            this.serviceProviderId
        )
    }
}

