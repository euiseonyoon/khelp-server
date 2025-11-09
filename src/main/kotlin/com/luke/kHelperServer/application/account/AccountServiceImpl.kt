package com.luke.kHelperServer.application.account

import com.luke.kHelperServer.application.account.dto.AccountDto
import com.luke.kHelperServer.application.account.provided_port.AccountService
import com.luke.kHelperServer.domain.account.Email
import com.luke.kHelperServer.domain.account.PASSWORD_MAX_LENGTH
import com.luke.kHelperServer.domain.account.PASSWORD_MIN_LENGTH
import com.luke.kHelperServer.domain.account.PasswordEncoder
import com.luke.kHelperServer.domain.account.request.AccountUpdateNicknameRequest
import com.luke.kHelperServer.domain.account.request.AccountUpdatePasswordRequest
import jakarta.validation.Valid
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AccountServiceImpl(
    private val accountCommandService: AccountCommandService,
    private val passwordEncoder: PasswordEncoder,
) : AccountService {
    @Transactional
    override fun updateAccountPassword(newRawPassword: String, email: Email): AccountDto? {
        val targetAccount = accountCommandService.findByEmail(email)?.account ?: return null
        targetAccount.updatePassword(passwordEncoder, newRawPassword)

        return accountCommandService.saveAccount(targetAccount)
    }

    @Transactional
    override fun updateAccountPassword(
        request: AccountUpdatePasswordRequest,
        accountId: Long,
    ): AccountDto? {
        val targetAccount = accountCommandService.findByAccountId(accountId)?.account ?: return null
        targetAccount.updatePassword(passwordEncoder, request.newPassword)

        return accountCommandService.saveAccount(targetAccount)
    }

    @Transactional
    override fun updateAccountNickname(
        request: AccountUpdateNicknameRequest,
        accountId: Long,
    ): AccountDto? {
        val targetAccount = accountCommandService.findByAccountId(accountId)?.account ?: return null
        targetAccount.updateNickname(request.newNickname)

        return accountCommandService.saveAccount(targetAccount)
    }
}