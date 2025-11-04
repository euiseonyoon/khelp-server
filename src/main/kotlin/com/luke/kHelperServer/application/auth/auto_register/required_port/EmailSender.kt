package com.luke.kHelperServer.application.auth.auto_register.required_port

import com.luke.kHelperServer.domain.account.Email

interface EmailSender {
    fun sendEmail(email: Email, rawPassword: String)
}
