package com.luke.kHelperServer.adapter.secondary.db.supporting_language

import com.luke.kHelperServer.domain.supporting_language.read.SupportingLanguageDocument
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface SupportingLanguageMongoRepository: MongoRepository<SupportingLanguageDocument, String> {
    fun findBySupportingLanguageId(supportingLanguageId: Long): SupportingLanguageDocument?

    fun deleteBySupportingLanguageId(supportingLanguageId: Long)
}
