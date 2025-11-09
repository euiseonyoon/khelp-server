package com.luke.kHelperServer.application.admin.service_provider

import com.luke.kHelperServer.application.admin.service_provider.provided_port.AdminServiceProviderWriter
import com.luke.kHelperServer.application.service_provider.dto.ServiceProviderDto
import com.luke.kHelperServer.application.service_provider.required_port.ServiceProviderCommandRepository
import com.luke.kHelperServer.domain.authority.ROLE_ADMIN
import com.luke.kHelperServer.domain.exception.BizException
import com.luke.kHelperServer.domain.exception.ErrorMessages
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@PreAuthorize("hasRole('$ROLE_ADMIN')")
class AdminServiceProviderService(
    private val serviceProviderCommandRepository: ServiceProviderCommandRepository,
): AdminServiceProviderWriter{

    @Transactional
    override fun approve(accountId: Long): ServiceProviderDto {
        val serviceProvider = serviceProviderCommandRepository.findByAccountId(accountId)
            ?: throw BizException(ErrorMessages.SERVICE_PROVIDER_ACCOUNT_NOT_FOUND)

        return serviceProviderCommandRepository.save(serviceProvider.approve()).let {
            ServiceProviderDto.fromEntity(it)
        }
    }
}
