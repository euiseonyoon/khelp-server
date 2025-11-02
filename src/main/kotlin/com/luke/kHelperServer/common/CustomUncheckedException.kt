package com.luke.kHelperServer.common

import org.springframework.http.HttpStatus

abstract class CustomUncheckedException(
    override val message: String,
    override val status: HttpStatus? = null,
) : RuntimeException(message), CustomExceptionInterface
