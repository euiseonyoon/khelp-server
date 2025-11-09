package com.luke.kHelperServer.application.service_provider

import com.luke.kHelperServer.application.account.provided_port.AccountWriter
import com.luke.kHelperServer.application.service_provider.dto.ServiceProviderDto
import com.luke.kHelperServer.application.service_provider.provided_port.ServiceProviderWriter
import com.luke.kHelperServer.application.service_provider.required_port.ServiceProviderCommandRepository
import com.luke.kHelperServer.application.supporting_language.required_port.SupportingLanguageCommandRepository
import com.luke.kHelperServer.domain.exception.BizException
import com.luke.kHelperServer.domain.exception.ErrorMessages
import com.luke.kHelperServer.domain.provider_language_skill.read.LanguageSkillInfo
import com.luke.kHelperServer.domain.service_provider.request.ServiceProviderRegisterRequest
import com.luke.kHelperServer.domain.service_provider.write.ServiceProvider
import com.luke.kHelperServer.domain.supporting_language.Language
import com.luke.kHelperServer.domain.supporting_language.LanguageLevel
import com.luke.kHelperServer.domain.supporting_language.write.SupportingLanguage
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ServiceProviderWriterImpl(
    private val serviceProviderCommandRepository: ServiceProviderCommandRepository,
    private val supportingLanguageCommandRepository: SupportingLanguageCommandRepository,
    private val accountWriter: AccountWriter,
) : ServiceProviderWriter{

    @Transactional
    override fun register(request: ServiceProviderRegisterRequest, accountId: Long): ServiceProviderDto {
        val accountFound = accountWriter.existsByAccountId(accountId)
        if (!accountFound) {
            throw BizException(ErrorMessages.SERVICE_PROVIDER_ACCOUNT_NOT_FOUND)
        }
        val filteredLanguageSkills = filterSupportedLanguage(request.languageSkillInfos)

        val serviceProvider = ServiceProvider(
            accountId = accountId,
            description = request.description,
            approved = false
        )
        filteredLanguageSkills.forEach { serviceProvider.addLanguageSkill(it) }

        return serviceProviderCommandRepository.save(serviceProvider).let {
            ServiceProviderDto.fromEntity(it)
        }
    }

    private fun filterSupportedLanguage(languageSkillInfos: List<LanguageSkillInfo>):  List<Pair<SupportingLanguage, LanguageLevel>> {
        val map = languageSkillInfos.associateBy { it.languageName.uppercase() }

        val supportingLanguages = supportingLanguageCommandRepository.findAllByLanguages(
            languageSkillInfos.map { Language(it.languageName.uppercase()) }
        )

        return supportingLanguages.mapNotNull{ supportingLanguage ->
            map[supportingLanguage.language.name]?.let {
                supportingLanguage to it.level
            }
        }
    }
}
