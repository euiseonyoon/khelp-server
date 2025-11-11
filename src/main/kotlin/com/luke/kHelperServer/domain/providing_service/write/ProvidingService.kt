package com.luke.kHelperServer.domain.providing_service.write

import com.luke.kHelperServer.domain.BaseEntity
import com.luke.kHelperServer.domain.EventType
import com.luke.kHelperServer.domain.providing_service.event.ProvidingServiceEvent
import com.luke.kHelperServer.domain.service_provider.write.ServiceProvider
import jakarta.persistence.*
import jakarta.validation.constraints.Min

@Entity
class ProvidingService(
    @Column(nullable = false) @Min(0)
    val price: Int,

    description: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    val serviceProvider: ServiceProvider,
): BaseEntity() {
    init {
        this.registerEvent(ProvidingServiceEvent(EventType.CREATED, this))
    }

    @Id @GeneratedValue
    val id: Long = 0

    @Column(nullable = false)
    var description: String = description
        private set

    fun updateDescription(description: String): ProvidingService {
        this.registerEvent(ProvidingServiceEvent(EventType.UPDATED, this))
        this.description = description
        return this
    }
}
