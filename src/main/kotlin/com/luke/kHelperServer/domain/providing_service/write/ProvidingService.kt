package com.luke.kHelperServer.domain.providing_service.write

import com.luke.kHelperServer.domain.BaseEntity
import com.luke.kHelperServer.domain.service_provider.write.ServiceProvider
import jakarta.persistence.*

@Entity
@EntityListeners(ProvidingServiceEntityListener::class)
class ProvidingService(
    @Column(nullable = false)
    val price: Int,

    @Column(nullable = false)
    val description: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    val serviceProvider: ServiceProvider,
): BaseEntity() {
    @Id @GeneratedValue
    val id: Long = 0
}