package com.luke.kHelperServer.adapter.secondary.db.providing_service

import com.luke.kHelperServer.domain.providing_service.write.ProvidingService

interface ProvidingServiceRepositoryExtension {
    fun findByIdWithServiceProvider(id: Long): ProvidingService?
}
