package com.luke.kHelperServer.domain.authority.exception

import com.luke.kHelperServer.common.CustomUncheckedException
import com.luke.kHelperServer.domain.authority.Role

class AuthorityNotFoundException(
    givenRole: Role
) : CustomUncheckedException("권한을 찾을 수 없습니다. ${givenRole.name}", null)
