package com.luke.kHelperServer.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@Configuration
@EnableMongoRepositories(basePackages = ["com.luke.kHelperServer.domain"])
class MongoConfig
