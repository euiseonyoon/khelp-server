package com.luke.kHelperServer.adapter.primary.webapi.v1

const val V1_URL = "/v1"
const val AUTH_URL = "/auth"
const val LOGIN_URL = "/login"
const val REFRESH_TOKEN_URL = "/refresh"
const val ADMIN_URL = "/admin"
const val SERVICE_PROVIDER_URL = "/service-provider"
const val PROVIDING_SERVICE_URL = "/providing-service"

const val V1_LOGIN_URL = "$V1_URL$AUTH_URL$LOGIN_URL"
const val V1_REFRESH_TOKEN_URL = "$V1_URL$AUTH_URL$REFRESH_TOKEN_URL"
const val V1_ADMIN_ACCOUNT_URL = "$V1_URL$ADMIN_URL/account"
const val V1_ADMIN_SERVICE_PROVIDER_URL = "$V1_URL$ADMIN_URL$SERVICE_PROVIDER_URL"
const val V1_ACCOUNT_URL = "$V1_URL/account"
const val V1_SERVICE_PROVIDER_URL = "$V1_URL$SERVICE_PROVIDER_URL"
const val V1_PROVIDING_SERVICE_URL = "$V1_URL$PROVIDING_SERVICE_URL"
const val V1_SERVICE_PROVIDER_REVIEW_URL = "$V1_URL$SERVICE_PROVIDER_URL/review"

