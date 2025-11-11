package com.luke.kHelperServer.domain

import jakarta.persistence.PrePersist
import jakarta.persistence.PreUpdate
import org.slf4j.LoggerFactory
import java.lang.reflect.Field
import java.time.OffsetDateTime

class UpdatedAtListener {
    private val logger = LoggerFactory.getLogger(UpdatedAtListener::class.java)

    @PrePersist
    @PreUpdate
    fun preUpdate(entity: Any) {
        try {
            val updatedAtField = findField(entity.javaClass)
            if (updatedAtField != null) {
                updatedAtField.isAccessible = true
                updatedAtField[entity] = OffsetDateTime.now()
            }
        } catch (e: IllegalAccessException) {
            logger.warn(
                "Failed to set updatedAt for entity: {}",
                entity.javaClass.name, e
            )
        }
    }

    private fun findField(clazz: Class<*>): Field? {
        var current: Class<*>? = clazz
        while (current != null && current != Any::class.java) {
            try {
                return current.getDeclaredField("updatedAt")
            } catch (e: NoSuchFieldException) {
                current = current.superclass
            }
        }
        return null
    }
}
