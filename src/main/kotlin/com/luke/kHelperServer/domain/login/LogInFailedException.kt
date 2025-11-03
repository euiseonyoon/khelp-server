package com.luke.kHelperServer.domain.login

import com.luke.kHelperServer.common.CustomUncheckedException

class LogInFailedException(msg: String): CustomUncheckedException(msg, null)
