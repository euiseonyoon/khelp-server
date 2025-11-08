package com.luke.kHelperServer.domain.exception

enum class ErrorMessages(val code: String, val msg: String) {
    DUPLICATED_EMAIL("AUTH-1", "중복된 이메일이 존재합니다."),
    OAUTH_VENDOR_NOT_FOUND("AUTH-2", "지원하지 않는 인증수단 입니다."),
    AUTHORITY_NOT_FOUND("AUTH-3", "권한을 찾을 수 없습니다."),
    ACCOUNT_NOT_FOUND("AUTH-4", "계정을 찾을 수 없습니다."),
    INVALID_CREDENTIAL("AUTH-5", "올바른 인증정보가 아닙니다."),

    REFRESH_TOKEN_ABSENT_FROM_COOKIE("TOKEN-1", "요청으로부터 인증 정보를 추출하는데 실패했습니다."),
    REFRESH_TOKEN_NOT_FOUND_ON_REPOSITORY("TOKEN-2", "인증 정보를 불러오는데 실패했습니다."),
    UNIDENTICAL_REFRESH_TOKEN("TOKEN-3", "잘못된 인증 정보입니다."),

    SERVICE_PROVIDER_ACCOUNT_NOT_FOUND("SERVICE-PROVIDER-1", "유저를 찾을 수 없습니다."),
    SERVICE_PROVIDER_NOT_FOUND("SERVICE-PROVIDER-1", "서비스 제공자를 찾을 수 없습니다."),

    PROVIDING_SERVICE_NOT_FOUND("PROVIDING-SERVICE-1", "서비스를 찾을 수 없습니다.")
}
