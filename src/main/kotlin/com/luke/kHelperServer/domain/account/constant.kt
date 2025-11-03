package com.luke.kHelperServer.domain.account

val EMAIL_REGEX = Regex("^[A-Za-z0-9]([._-]?[A-Za-z0-9])*@[A-Za-z0-9]+([-.][A-Za-z0-9]+)*\\.[A-Za-z]{2,6}$")
const val PASSWORD_MIN_LENGTH = 8
const val PASSWORD_MAX_LENGTH = 16
