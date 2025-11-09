package com.luke.kHelperServer.adapter.secondary.db.providing_service

import com.luke.kHelperServer.domain.providing_service.write.ProvidingService
import com.luke.kHelperServer.domain.providing_service.write.QProvidingService
import com.luke.kHelperServer.domain.service_provider.write.QServiceProvider
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class ProvidingServiceRepositoryExtensionImpl(
    private val queryFactory: JPAQueryFactory
) : ProvidingServiceRepositoryExtension{
    private val providingService = QProvidingService.providingService
    private val serviceProvider = QServiceProvider.serviceProvider

    override fun findByIdWithServiceProvider(id: Long): ProvidingService? {
        return queryFactory.selectFrom(providingService)
            .innerJoin(serviceProvider).on(providingService.serviceProvider.eq(serviceProvider))
            .where(providingService.id.eq(id))
            .fetchOne()
    }
}