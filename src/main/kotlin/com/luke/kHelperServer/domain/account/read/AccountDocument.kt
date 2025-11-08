package com.luke.kHelperServer.domain.account.read

import com.luke.kHelperServer.domain.account.Email
import com.luke.kHelperServer.domain.account.OauthVendor
import com.luke.kHelperServer.domain.authority.read.AuthorityDocument
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.CompoundIndex
import org.springframework.data.mongodb.core.index.CompoundIndexes
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.Date

@Document(collection = "account")
@CompoundIndexes(
    CompoundIndex(name = "account_id_idx", def = "{'account_id': 1}", unique = true),
    CompoundIndex(name = "email_idx", def = "{'email': 1}", unique = true)
)
data class AccountDocument(
    @Id
    val id: String? = null,

    @Field("account_id")
    val accountId: Long,

    @Field("email")
    val email: String,

    @Field("passwordHash")
    var passwordHash: String,

    @Field("authority")
    var authority: AuthorityDocument,

    @Field("nickname")
    var nickname: String?,

    @Field("enabled")
    var enabled: Boolean,

    @Field("oauth_vendor")
    var oauthVendor: String?,

    @Field("created_at")
    val createdAt: Date,

    @Field("updated_at")
    var updatedAt: Date
) {
    fun toAccountView(): AccountView {
        return AccountView(
            accountId = accountId,
            email = Email(email),
            passwordHash = passwordHash,
            authorityDoc = authority,
            nickName = nickname,
            enabled = enabled,
            oauthVendor = oauthVendor?.let { OauthVendor.valueOf(it) }
        )
    }
}
