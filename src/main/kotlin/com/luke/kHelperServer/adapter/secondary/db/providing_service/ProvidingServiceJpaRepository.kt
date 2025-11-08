package com.luke.kHelperServer.adapter.secondary.db.providing_service

import com.luke.kHelperServer.domain.providing_service.write.ProvidingService
import org.springframework.data.jpa.repository.JpaRepository

interface ProvidingServiceJpaRepository : JpaRepository<ProvidingService, Long> {
}
