package com.luke.kHelperServer.domain.account.excpetions

import com.luke.kHelperServer.common.CustomUncheckedException
import com.luke.kHelperServer.domain.account.OauthVendor

class OauthAuthenticatorNotFoundException(oauthVendor: OauthVendor): CustomUncheckedException(
    "지원하지 않는 외부 로그인 서비스 입니다. ${oauthVendor.name}", null
)
