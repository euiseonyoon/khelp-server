package com.luke.kHelperServer.adapter.primary.webapi.common

import com.luke.kHelperServer.common.GlobalResponse
import com.luke.kHelperServer.domain.exception.BizException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(BizException::class)
    fun handleBizException(e: BizException): ResponseEntity<GlobalResponse<Nothing>> {
        return GlobalResponse.createBizExceptionResponse(e, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(e: IllegalArgumentException): ResponseEntity<GlobalResponse<Nothing>> {
        return GlobalResponse.createExceptionResponse(e, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(IllegalStateException::class)
    fun handleIllegalStateException(e: IllegalStateException): ResponseEntity<GlobalResponse<Nothing>> {
        return GlobalResponse.createExceptionResponse(e, HttpStatus.CONFLICT)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<GlobalResponse<Nothing>> {
        val response = GlobalResponse(
            isError = true,
            result = null,
            errorMsg = "An unexpected error occurred.",
            errorCode = "INTERNAL_SERVER_ERROR",
        )
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response)
    }
}
