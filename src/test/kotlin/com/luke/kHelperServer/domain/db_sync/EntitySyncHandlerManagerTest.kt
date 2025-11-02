package com.luke.kHelperServer.domain.db_sync

import com.luke.kHelperServer.domain.account.Email
import com.luke.kHelperServer.domain.account.PasswordHash
import com.luke.kHelperServer.domain.account.event.AccountCreatedEvent
import com.luke.kHelperServer.domain.account.read.AccountSyncHandler
import com.luke.kHelperServer.domain.account.write.Account
import com.luke.kHelperServer.domain.authority.Role
import com.luke.kHelperServer.domain.authority.write.Authority
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNotNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import kotlin.test.assertTrue


@SpringBootTest
@TestPropertySource(properties = ["spring.profiles.active=test"])
class EntitySyncHandlerManagerTest {
    @Autowired
    lateinit var entitySyncHandlerManager: EntitySyncHandlerManager

    @Test
    fun test() {
        val event = AccountCreatedEvent(
            Account(
                email = Email("test@test.com"),
                passwordHash = PasswordHash("pswd"),
                authority = Authority(Role("ROLE_USER"), 100),
            )
        )
        val handler = entitySyncHandlerManager.findHandler(event)
        assertNotNull(handler)
        assertTrue { handler is AccountSyncHandler }
    }
}