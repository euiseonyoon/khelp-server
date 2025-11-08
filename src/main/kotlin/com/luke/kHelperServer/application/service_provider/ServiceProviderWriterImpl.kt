package com.luke.kHelperServer.application.service_provider

import com.luke.kHelperServer.application.account.provided_port.AccountWriter
import com.luke.kHelperServer.application.service_provider.dto.ServiceProviderDto
import com.luke.kHelperServer.application.service_provider.provided_port.ServiceProviderWriter
import com.luke.kHelperServer.application.service_provider.required_port.ServiceProviderCommandRepository
import com.luke.kHelperServer.domain.exception.BizException
import com.luke.kHelperServer.domain.exception.ErrorMessages
import com.luke.kHelperServer.domain.service_provider.request.ServiceProviderRegisterRequest
import jakarta.validation.Valid
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.validation.annotation.Validated

@Service
@Validated
class ServiceProviderWriterImpl(
    private val serviceProviderCommandRepository: ServiceProviderCommandRepository,
    private val accountWriter: AccountWriter,
) : ServiceProviderWriter{

    @Transactional
    override fun register(@Valid request: ServiceProviderRegisterRequest, accountId: Long): ServiceProviderDto {
        val accountFound = accountWriter.existsByAccountId(accountId)
        if (!accountFound) {
            throw BizException(ErrorMessages.SERVICE_PROVIDER_ACCOUNT_NOT_FOUND)
        }

        return serviceProviderCommandRepository.save(accountId, request.description).let {
            ServiceProviderDto(it)
        }
    }

    override fun findByAccountId(accountId: Long): ServiceProviderDto? {
        return serviceProviderCommandRepository.findByAccountId(accountId)?.let {
            ServiceProviderDto(it)
        }
    }
}
