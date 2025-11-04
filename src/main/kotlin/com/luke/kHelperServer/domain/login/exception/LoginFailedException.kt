package com.luke.kHelperServer.domain.login.exception

import com.luke.kHelperServer.common.CustomUncheckedException

open class LoginFailedException(msg: String): CustomUncheckedException(msg)
