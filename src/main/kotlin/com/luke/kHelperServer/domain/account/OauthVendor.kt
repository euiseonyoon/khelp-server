package com.luke.kHelperServer.domain.account

import com.fasterxml.jackson.annotation.JsonCreator

enum class OauthVendor {
    GOOGLE,
    ;

    companion object {
        fun fromString(vendorName: String): OauthVendor {
            return entries.firstOrNull { it.name.equals(vendorName, ignoreCase = true) }
                ?: throw IllegalArgumentException("Unknown oauth vendor: $vendorName")
        }

        @JsonCreator // RequestBody의 conversion을 위해서
        fun fromValue(value: String): OauthVendor {
            return fromString(value)
        }
    }
}

