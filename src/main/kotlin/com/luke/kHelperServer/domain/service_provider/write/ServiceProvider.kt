package com.luke.kHelperServer.domain.service_provider.write

import com.luke.kHelperServer.domain.BaseEntity
import jakarta.persistence.*

@Entity
@EntityListeners(ServiceProviderEntityListener::class)
class ServiceProvider(
    @Column(unique = true, nullable = false)
    val accountId: Long,

    @Column(nullable = false)
    val description: String,

    @Column(nullable = false)
    var approved: Boolean = false
): BaseEntity() {
    @Id
    @GeneratedValue
    val id: Long = 0

    fun approve(): ServiceProvider {
        this.approved = true
        return this
    }
}
