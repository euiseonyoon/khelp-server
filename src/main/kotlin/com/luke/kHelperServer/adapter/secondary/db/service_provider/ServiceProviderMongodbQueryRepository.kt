package com.luke.kHelperServer.adapter.secondary.db.service_provider

import com.luke.kHelperServer.application.service_provider.required_port.ServiceProviderQueryRepository
import com.luke.kHelperServer.domain.service_provider.read.ServiceProviderDocument
import com.luke.kHelperServer.domain.service_provider.read.ServiceProviderView
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Component

@Component
class ServiceProviderMongodbQueryRepository(
    private val mongoTemplate: MongoTemplate
) : ServiceProviderQueryRepository {

    override fun getApprovedServiceProviders(
        perPage: Int,
        pageNumber: Int,
        languageId: Long?,
        level: String?
    ): Page<ServiceProviderView> {

        val criteria = Criteria.where("approved").`is`(true)

        // 언어 조건 추가
        if (languageId != null && level != null) {
            // 언어 ID + 레벨 둘 다 있음
            criteria.and("language_skills").elemMatch(
                Criteria.where("language_id").`is`(languageId)
                    .and("level").`is`(level)
            )
        } else if (languageId != null) {
            // 언어 ID만 있음
            criteria.and("language_skills").elemMatch(
                Criteria.where("language_id").`is`(languageId)
            )
        }

        val query = Query(criteria)
            .with(Sort.by(Sort.Direction.DESC, "updated_at"))
            .with(PageRequest.of(pageNumber, perPage))

        val documents = mongoTemplate.find(query, ServiceProviderDocument::class.java)
        val total = mongoTemplate.count(Query(criteria), ServiceProviderDocument::class.java)
        val content = documents.map { it.toView() }

        return PageImpl(content, PageRequest.of(pageNumber, perPage), total)
    }
}
