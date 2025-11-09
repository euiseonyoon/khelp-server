package com.luke.kHelperServer.domain.supporting_language

import jakarta.persistence.Embeddable

@Embeddable
class Language(
    val name: String
) {
    init {
        if(!name.all { it.isUpperCase() }) {
            throw IllegalArgumentException("language name must be all capitalized.")
        }
    }
}
