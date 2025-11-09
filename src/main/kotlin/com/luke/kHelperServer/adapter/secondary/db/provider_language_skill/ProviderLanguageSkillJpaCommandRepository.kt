package com.luke.kHelperServer.adapter.secondary.db.provider_language_skill

import com.luke.kHelperServer.application.provider_language_skill.required_port.ProviderLanguageSkillCommandRepository
import com.luke.kHelperServer.domain.provider_language_skill.write.ProviderLanguageSkill
import com.luke.kHelperServer.domain.service_provider.write.ServiceProvider
import com.luke.kHelperServer.domain.supporting_language.LanguageLevel
import com.luke.kHelperServer.domain.supporting_language.write.SupportingLanguage
import org.springframework.stereotype.Component

@Component
class ProviderLanguageSkillJpaCommandRepository(
    private val providerLanguageSkillJpaRepository: ProviderLanguageSkillJpaRepository
) : ProviderLanguageSkillCommandRepository {

    override fun addLanguageSkill(
        serviceProvider: ServiceProvider,
        language: SupportingLanguage,
        level: LanguageLevel
    ): ProviderLanguageSkill {
        return providerLanguageSkillJpaRepository.save(
            ProviderLanguageSkill(serviceProvider, language, level)
        )
    }

    override fun deleteLanguageSkill(providerLanguageSkillId: Long) {
        // FIX: deleteById는 @EntityListener의 @PostDelete 으로 잡을수 없다.
        // Hibernate는 DeleteById를 할 경우, entity를 메모리에 로딩시킨후 삭제시키는게 아니다. 바로 DELETE 쿼리를 날린다.
        // 그렇기 때문에 Entity의 라이플사이클 이벤트를 사용하는 @EntityListener나 AbstractAggregateRoot로는 deleteById 할 경우
        // 이벤트를 알수 없다.
        // providerLanguageSkillJpaRepository.deleteById(providerLanguageSkillId)

        providerLanguageSkillJpaRepository.findById(providerLanguageSkillId).orElse(null)?.let {
            providerLanguageSkillJpaRepository.delete(it)
        }
    }
}
