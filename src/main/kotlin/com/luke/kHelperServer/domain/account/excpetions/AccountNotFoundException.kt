package com.luke.kHelperServer.domain.account.excpetions

import com.luke.kHelperServer.common.CustomUncheckedException
import com.luke.kHelperServer.domain.account.Email

class AccountNotFoundException(email: Email): CustomUncheckedException(
    "해당 이메일을 소유한 유저를 찾을 수 없습니다. ${email.address}", null
)
