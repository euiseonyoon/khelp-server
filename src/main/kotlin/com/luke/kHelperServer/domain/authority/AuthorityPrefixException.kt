package com.luke.kHelperServer.domain.authority

import com.luke.kHelperServer.common.CustomUncheckedException

class AuthorityPrefixException(
    givenRoleName: String
) : CustomUncheckedException("Inadequate authority name. given : $givenRoleName", null)
