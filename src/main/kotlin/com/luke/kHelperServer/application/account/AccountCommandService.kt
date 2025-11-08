package com.luke.kHelperServer.application.account

import com.luke.kHelperServer.application.account.dto.AccountDto
import com.luke.kHelperServer.application.account.provided_port.AccountWriter
import com.luke.kHelperServer.application.account.required_port.AccountCommandRepository
import com.luke.kHelperServer.domain.account.Email
import com.luke.kHelperServer.domain.account.PasswordEncoder
import com.luke.kHelperServer.domain.account.request.AccountCreateRequest
import com.luke.kHelperServer.domain.account.write.Account
import com.luke.kHelperServer.domain.exception.BizException
import com.luke.kHelperServer.domain.exception.ErrorMessages
import jakarta.validation.Valid
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.validation.annotation.Validated

@Service
@Validated
class AccountCommandService(
    private val accountCommandRepository: AccountCommandRepository,
    private val passwordEncoder: PasswordEncoder,
) : AccountWriter {
    @Transactional
    override fun registerAccount(@Valid request: AccountCreateRequest): AccountDto {
        checkDuplicateEmail(request.email)

        val newAccount = Account(
            email = request.email,
            passwordHash = passwordEncoder.encode(request.rawPassword),
            nickname = request.nickname,
            authority = request.authority,
            enabled = true,
            oauth = request.oauthVendor,
        )

        return AccountDto(accountCommandRepository.save(newAccount))
    }

    @Transactional(readOnly = true)
    override fun findByEmail(email: Email): AccountDto? {
        return accountCommandRepository.findByEmail(email)?.let { AccountDto(it) }
    }

    override fun findByAccountId(accountId: Long): AccountDto? {
        return accountCommandRepository.findByAccountId(accountId)?.let { AccountDto(it) }
    }

    override fun saveAccount(account: Account): AccountDto {
        return AccountDto(accountCommandRepository.save(account))
    }

    override fun existsByAccountId(accountId: Long): Boolean {
        return accountCommandRepository.existsByAccountId(accountId)
    }

    private fun checkDuplicateEmail(email: Email) {
        if (accountCommandRepository.existsByEmail(email)) {
            throw BizException(ErrorMessages.DUPLICATED_EMAIL)
        }
    }
}
