package com.luke.kHelperServer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@SpringBootApplication
@EnableJpaRepositories(basePackages = ["com.luke.kHelperServer.adapter.secondary.db"])
@EnableMongoRepositories(basePackages = ["com.luke.kHelperServer.adapter.secondary.db"])
@EnableJpaAuditing
class KHelpServerApplication

fun main(args: Array<String>) {
	runApplication<KHelpServerApplication>(*args)
}
