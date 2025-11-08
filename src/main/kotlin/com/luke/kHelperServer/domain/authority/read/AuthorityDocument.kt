package com.luke.kHelperServer.domain.authority.read

import com.luke.kHelperServer.domain.authority.write.Authority
import org.springframework.data.mongodb.core.index.CompoundIndex
import org.springframework.data.mongodb.core.index.CompoundIndexes
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.Date

@Document(collection = "authority")
@CompoundIndexes(
    CompoundIndex(name = "authority_id_idx", def = "{'authority_id': 1}", unique = true),
)
data class AuthorityDocument(
    @Field("id")
    val id: String? = null,

    @Field("authority_id")
    val authorityId: Long,

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
                authorityId = authority.id,
                role = authority.role.name,
                hierarchy = authority.hierarchy,
                createdAt = Date.from(authority.createdAt.toInstant()),
                updatedAt = Date.from(authority.updatedAt.toInstant())
            )
        }
    }
}
