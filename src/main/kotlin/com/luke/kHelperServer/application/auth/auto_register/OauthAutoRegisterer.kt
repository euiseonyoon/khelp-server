package com.luke.kHelperServer.application.auth.auto_register

import com.luke.kHelperServer.application.account.dto.AccountDto
import com.luke.kHelperServer.application.account.provided_port.AccountWriter
import com.luke.kHelperServer.application.auth.auto_register.provided_port.AutoRegisterer
import com.luke.kHelperServer.application.auth.auto_register.required_port.AuthorityCommandRepository
import com.luke.kHelperServer.application.auth.auto_register.required_port.EmailSender
import com.luke.kHelperServer.domain.account.Email
import com.luke.kHelperServer.domain.account.OauthVendor
import com.luke.kHelperServer.domain.account.request.AccountCreateRequest
import com.luke.kHelperServer.domain.authority.ROLE_USER
import com.luke.kHelperServer.domain.authority.Role
import com.luke.kHelperServer.domain.authority.exception.AuthorityNotFoundException
import com.luke.kHelperServer.application.auth.auto_register.required_port.PasswordGenerator
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OauthAutoRegisterer(
    private val authorityCommandRepository: AuthorityCommandRepository,
    private val accountWriter: AccountWriter,
    private val passwordGenerator: PasswordGenerator,
    private val emailSender: EmailSender,
) : AutoRegisterer {
    @Transactional
    override fun autoRegisterAccount(email: Email, oauthVendor: OauthVendor): AccountDto {
        val targetRole = Role(ROLE_USER)
        val userRole = authorityCommandRepository.findByRole(targetRole) ?: throw AuthorityNotFoundException(targetRole)
        val rawPassword = passwordGenerator.generatePassword()

        val request = AccountCreateRequest(
            email = email,
            oauthVendor = oauthVendor,
            authority = userRole,
            rawPassword = rawPassword,
            nickname = null,
        )
        return accountWriter.registerAccount(request).also {
            emailSender.sendEmail(email, rawPassword)
        }
    }
}
