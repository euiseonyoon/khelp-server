package com.luke.kHelperServer.domain.service_provider.read

import org.springframework.data.mongodb.core.index.CompoundIndex
import org.springframework.data.mongodb.core.index.CompoundIndexes
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.*

@Document(collection = "service_provider_review")
@CompoundIndexes(
    CompoundIndex(
        name = "service_provider_id_updated_at_idx",
        def = "{'service_provider_id': 1, 'updated_at': -1}"
    ),
    CompoundIndex(
        name = "reviewer_account_id_updated_at_idx",
        def = "{'reviewer_account_id': 1, 'updated_at': -1}"
    ),
    CompoundIndex(
        name = "service_provider_review_id_idx",
        def = "{'service_provider_review_id': 1}",
        unique = true
    )
)
data class ServiceProviderReviewDocument(
    @org.springframework.data.annotation.Id
    val id: String? = null,

    @Field("service_provider_review_id")
    val serviceProviderReviewId: Long,

    @Field("reviewer_account_id")
    val reviewerAccountId: Long,

    @Field("service_provider_id")
    val serviceProviderId: Long,

    @Field("review")
    val review: String,

    @Field
    val rating: Int,

    @Field("created_at")
    val createdAt: Date,

    @Field("updated_at")
    var updatedAt: Date
) {
    fun toServiceProviderReviewView(): ServiceProviderReviewView {
        return ServiceProviderReviewView(this.serviceProviderReviewId, this.review, this.rating)
    }
}

