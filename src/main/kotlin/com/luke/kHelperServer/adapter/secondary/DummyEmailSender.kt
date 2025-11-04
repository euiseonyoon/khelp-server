package com.luke.kHelperServer.adapter.secondary

import com.luke.kHelperServer.application.auth.auto_register.required_port.EmailSender
import com.luke.kHelperServer.domain.account.Email
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class DummyEmailSender : EmailSender {
    @Async
    override fun sendEmail(email: Email, rawPassword: String) {
        TODO("Not yet implemented")
    }
}
