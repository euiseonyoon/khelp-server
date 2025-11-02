package com.luke.kHelperServer.domain.account.write

interface AccountCommandRepository {
    fun save(account: Account): Account
}
