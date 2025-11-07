package com.luke.kHelperServer.application.admin.account

import com.luke.kHelperServer.application.account.AccountCommandService
import com.luke.kHelperServer.application.account.dto.AccountDto
import com.luke.kHelperServer.application.account.provided_port.AccountService
import com.luke.kHelperServer.application.account.provided_port.AccountWriter
import com.luke.kHelperServer.application.admin.account.provided_port.AdminAccountService
import com.luke.kHelperServer.application.auth.auto_register.required_port.AuthorityCommandRepository
import com.luke.kHelperServer.domain.account.Email
import com.luke.kHelperServer.domain.account.PASSWORD_MAX_LENGTH
import com.luke.kHelperServer.domain.account.PASSWORD_MIN_LENGTH
import com.luke.kHelperServer.domain.account.request.SetToAdminRequest
import com.luke.kHelperServer.domain.account.write.Account
import com.luke.kHelperServer.domain.authority.ROLE_ADMIN
import com.luke.kHelperServer.domain.authority.ROLE_SUPER_ADMIN
import com.luke.kHelperServer.domain.authority.Role
import jakarta.validation.Valid
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.validation.annotation.Validated

@Service
@PreAuthorize("hasRole('$ROLE_SUPER_ADMIN')")
@Validated
class AdminAccountServiceImpl(
    private val accountService: AccountService,
    private val accountCommandService: AccountCommandService,
    private val authorityCommandRepository: AuthorityCommandRepository
) : AdminAccountService {

    @Transactional
    override fun setToAccountToAdmin(@Valid request: SetToAdminRequest): AccountDto? {
        val email = Email(request.targetEmail)
        if (request.newPassword == null) {
            return setAccountToAdmin(email)
        }

        setAccountToAdmin(email)
        return setAccountPassword(request.newPassword, email)
    }

    private fun setAccountToAdmin(email: Email): AccountDto? {
        val adminAuthority = authorityCommandRepository.findByRole(Role(ROLE_ADMIN))
            ?: throw IllegalArgumentException("어드민 role을 찾을 수 없음")

        val targetAccount = accountCommandService.findByEmail(email)?.account ?: return null
        targetAccount.authority = adminAuthority
        return accountCommandService.saveAccount(targetAccount)
    }

    private fun setAccountPassword(newPassword: String, email: Email): AccountDto? {
        return accountService.updateAccountPassword(newPassword, email)
    }
}
