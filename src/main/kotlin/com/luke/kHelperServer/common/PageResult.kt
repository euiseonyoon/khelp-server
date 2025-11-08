package com.luke.kHelperServer.common

import kotlinx.serialization.Serializable
import org.springframework.data.domain.Page

@Serializable
data class PageResult<T>(
    val perPage: Int,
    val pageNumber: Int,
    val totalCount: Long,
    val totalPages: Int,
    val result: List<T>
){
    companion object {
        fun <T> fromPage(page: Page<T>): PageResult<T> {
            return PageResult(
                perPage = page.size,
                pageNumber = page.number,
                totalCount = page.totalElements,
                totalPages = page.totalPages,
                result = page.content,
            )
        }
    }
}
