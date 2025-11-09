package com.luke.kHelperServer.domain.supporting_language

import com.fasterxml.jackson.annotation.JsonCreator

enum class LanguageLevel {
    NATIVE,
    FLUENT,
    ADVANCED,
    INTERMEDIATE,
    ;

    companion object {
        fun fromString(levelName: String): LanguageLevel {
            return LanguageLevel.entries.firstOrNull { it.name.equals(levelName, ignoreCase = true) }
                ?: throw IllegalArgumentException("Unknown language level: $levelName")
        }

        @JsonCreator
        fun fromValue(value: String): LanguageLevel {
            return fromString(value)
        }
    }
}
