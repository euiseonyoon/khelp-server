package com.luke.kHelperServer.common

import org.springframework.http.HttpStatus

interface CustomExceptionInterface {
    val message: String
    val status: HttpStatus?
}
