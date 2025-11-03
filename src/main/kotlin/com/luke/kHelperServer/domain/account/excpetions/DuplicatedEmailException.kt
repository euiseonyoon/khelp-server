package com.luke.kHelperServer.domain.account.excpetions

import com.luke.kHelperServer.common.CustomUncheckedException
import com.luke.kHelperServer.domain.account.Email

class DuplicatedEmailException(email: Email): CustomUncheckedException(
    "이미 존재하는 이메일입니다. email=${email.address}", null
)
