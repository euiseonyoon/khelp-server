package com.luke.kHelperServer.domain.service_provider.write

import com.luke.kHelperServer.domain.BaseEntity
import jakarta.persistence.*
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min


@Entity
@EntityListeners(ServiceProviderReviewEntityListener::class)
class ServiceProviderReview(
    @Column(nullable = false)
    val review: String,

    @Column(nullable = false)
    val reviewerAccountId: Long,

    @Column(nullable = false) @Min(1) @Max(5)
    val rating: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    val serviceProvider: ServiceProvider
): BaseEntity() {
    @Id
    @GeneratedValue
    val id: Long = 0
}
