package com.luke.kHelperServer.application.admin.service_provider.provided_port

import com.luke.kHelperServer.application.service_provider.dto.ServiceProviderDto

interface AdminServiceProviderWriter {
    fun approve(accountId: Long): ServiceProviderDto
}
