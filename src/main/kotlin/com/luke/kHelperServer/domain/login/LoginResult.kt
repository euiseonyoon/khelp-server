package com.luke.kHelperServer.domain.login

import com.luke.kHelperServer.domain.account.write.Account

data class LoginResult(
    val account: Account,
    val generatedTokens: GeneratedTokens
)
