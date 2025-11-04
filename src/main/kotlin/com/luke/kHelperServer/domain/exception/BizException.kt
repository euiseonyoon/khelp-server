package com.luke.kHelperServer.domain.exception

open class BizException(
    val code: String,
    override val message: String
) : RuntimeException(message) {
    constructor(error: ErrorMessages) : this(error.code, error.msg)
}
