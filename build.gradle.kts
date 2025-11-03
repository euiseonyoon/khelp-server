plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.5.7"
	id("io.spring.dependency-management") version "1.1.7"
	kotlin("plugin.jpa") version "1.9.25"
}

group = "com.luke"
version = "0.0.1-SNAPSHOT"
description = "Demo project for Spring Boot"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	// JPA
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")

	// MONGODB
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")

	// 코틀린 기본 라이브러리
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	// 💡 리플렉션 기능
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	// OAuth2 Resource Server (NimbusJwtDecoder 포함)
	implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")

    // validation
    implementation("org.springframework.boot:spring-boot-starter-validation")

	// jwt
	val jjwtVersion = "0.11.5"
	implementation("io.jsonwebtoken:jjwt-api:$jjwtVersion")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:${jjwtVersion}")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:${jjwtVersion}")

	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	// test db
	testImplementation("com.h2database:h2")

	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testImplementation("org.springframework.security:spring-security-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
