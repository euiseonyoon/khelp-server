package com.luke.kHelperServer.common

import com.luke.kHelperServer.domain.exception.BizException
import kotlinx.serialization.Serializable
import org.aspectj.weaver.ast.Not
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

@Serializable
data class GlobalResponse<T>(
    val isError: Boolean,
    val result: T?,
    val errorMsg: String?,
    val errorCode: String?,
) {
    companion object {
        fun <T> create(result: T): GlobalResponse<T> {
            return GlobalResponse(false, result, null, null)
        }

        fun createFromBizException(bizException: BizException): GlobalResponse<Nothing> {
            return GlobalResponse(true, null, bizException.message, bizException.code)
        }

        fun createFromException(e: Exception): GlobalResponse<Nothing> {
            return GlobalResponse(true, null, e.message, null)
        }

        fun <T> createResponse(result: T): ResponseEntity<GlobalResponse<T>> {
            return ResponseEntity.ok(this.create(result))
        }

        fun createBizExceptionResponse(
            bizException: BizException,
            httpStatus: HttpStatus,
        ): ResponseEntity<GlobalResponse<Nothing>>{
            require(httpStatus.isError)
            return ResponseEntity.status(httpStatus.value()).body(this.createFromBizException(bizException))
        }

        fun createExceptionResponse(
            e: Exception,
            httpStatus: HttpStatus,
        ): ResponseEntity<GlobalResponse<Nothing>>{
            require(httpStatus.isError)
            return ResponseEntity.status(httpStatus.value()).body(this.createFromException(e))
        }
    }
}
