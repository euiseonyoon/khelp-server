package com.luke.kHelperServer.domain.account

enum class OauthVendor {
    GOOGLE,
    ;

    companion object {
        fun fromString(vendorName: String): OauthVendor {
            return entries.firstOrNull { it.name.equals(vendorName, ignoreCase = true) }
                ?: throw IllegalArgumentException("Unknown oauth vendor: $vendorName")
        }
    }
}

