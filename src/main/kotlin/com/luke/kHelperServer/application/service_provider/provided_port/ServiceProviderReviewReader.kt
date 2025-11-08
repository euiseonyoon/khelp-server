package com.luke.kHelperServer.application.service_provider.provided_port

import com.luke.kHelperServer.domain.service_provider.read.ServiceProviderReviewView
import org.springframework.data.domain.Page

interface ServiceProviderReviewReader {
    fun findByServiceProviderId(serviceProviderId: Long, perPage: Int, pageNumber: Int): Page<ServiceProviderReviewView>

    fun findReviewByMe(accountId: Long, perPage: Int, pageNumber: Int): Page<ServiceProviderReviewView>
}