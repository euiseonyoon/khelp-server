package com.luke.kHelperServer.domain.authority.read

import com.luke.kHelperServer.domain.authority.write.Authority
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.Date

@Document(collection = "authority")
data class AuthorityDocument(
    @Field("id")
    val id: Long,

    @Field("role")
    val role: String,

    @Field("hierarchy")
    val hierarchy: Int,

    @Field("created_at")
    val createdAt: Date,

    @Field("updated_at")
    val updatedAt: Date
) {
    companion object {
        fun fromAuthority(authority: Authority): AuthorityDocument {
            return AuthorityDocument(
                authority.id,
                authority.role.name,
                authority.hierarchy,
                Date.from(authority.createdAt.toInstant()),
                Date.from(authority.updatedAt.toInstant())
            )
        }
    }
}
