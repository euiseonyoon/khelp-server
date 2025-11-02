# KHelpServer Project Structure

## Project Overview
- **Project Name**: KHelpServer
- **Language**: Kotlin 1.9.25
- **Framework**: Spring Boot 3.5.7
- **Java Version**: 17
- **Build Tool**: Gradle (Kotlin DSL)
- **Architecture Pattern**: CQRS (Command Query Responsibility Segregation) with Event Sourcing

## Technology Stack

### Core Frameworks
- **Spring Boot**: 3.5.7
- **Kotlin**: 1.9.25
- **Spring Data JPA**: For write operations (PostgreSQL)
- **Spring Data MongoDB**: For read operations (MongoDB)
- **Spring Security**: Authentication and authorization
- **Spring Web**: REST API endpoints

### Databases
- **PostgreSQL**: Write database (command side)
- **MongoDB**: Read database (query side)
- **H2**: Test database

### Supporting Libraries
- **Jackson Kotlin Module**: JSON serialization
- **JUnit 5**: Testing framework
- **Kotlin Reflect**: Reflection capabilities

## Directory Structure

```
KHelpServer/
├── .serena/                          # Serena MCP configuration
│   ├── project.yml
│   ├── memories/                     # Project memory storage
│   └── .gitignore
├── .claude/                          # Claude configuration
│   └── settings.local.json
├── gradle/                           # Gradle wrapper
│   └── wrapper/
├── .run/                             # IDE run configurations
│   └── KHelpServerApplicationKt.run.xml
├── src/
│   ├── main/
│   │   ├── kotlin/com/luke/kHelperServer/
│   │   │   ├── KHelpServerApplication.kt    # Main entry point
│   │   │   ├── configuration/               # Configuration classes
│   │   │   │   └── MongoConfig.kt
│   │   │   ├── common/                      # Common utilities/exceptions
│   │   │   │   ├── CustomExceptionInterface.kt
│   │   │   │   └── CustomUncheckedException.kt
│   │   │   └── domain/                      # Domain layer (DDD)
│   │   │       ├── BaseEntity.kt            # Base entity with audit fields
│   │   │       ├── WriteDbCommitedEvent.kt  # Base event for DB sync
│   │   │       ├── db_sync/                 # Database synchronization infrastructure
│   │   │       │   ├── BaseEntityListener.kt           # JPA entity listener base
│   │   │       │   ├── EntitySyncHandler.kt            # Event handler interface
│   │   │       │   ├── EntitySyncHandlerManager.kt     # Handler registry interface
│   │   │       │   ├── EntitySyncHandlerManagerImpl.kt # Handler registry impl
│   │   │       │   ├── WriteDbEventHandler.kt          # Event handler interface
│   │   │       │   └── WriteDbEventHandlerImpl.kt      # Event handler impl
│   │   │       ├── account/                 # Account bounded context
│   │   │       │   ├── Email.kt             # Email value object
│   │   │       │   ├── OauthVendor.kt       # OAuth provider enum
│   │   │       │   ├── constant.kt          # Domain constants
│   │   │       │   ├── write/               # Write side (command)
│   │   │       │   │   ├── Account.kt                   # Account entity (PostgreSQL)
│   │   │       │   │   ├── AccountEntityListener.kt     # JPA listener for events
│   │   │       │   │   ├── AccountJpaRepository.kt      # Spring Data JPA repo
│   │   │       │   │   ├── AccountCommandRepository.kt  # Command repo interface
│   │   │       │   │   └── AccountJpaCommandRepository.kt # Command repo impl
│   │   │       │   ├── read/                # Read side (query)
│   │   │       │   │   ├── AccountDocument.kt              # MongoDB document
│   │   │       │   │   ├── AccountView.kt                  # Read model/DTO
│   │   │       │   │   ├── AccountSyncHandler.kt           # Sync handler for events
│   │   │       │   │   ├── AccountMongoRepository.kt       # Spring Data Mongo repo
│   │   │       │   │   ├── AccountQueryRepository.kt       # Query repo interface
│   │   │       │   │   └── AccountMongodbQueryRepository.kt # Query repo impl
│   │   │       │   └── event/               # Domain events
│   │   │       │       ├── AccountCommittedEvent.kt  # Base account event
│   │   │       │       ├── AccountCreatedEvent.kt    # Account creation event
│   │   │       │       └── AccountUpdatedEvent.kt    # Account update event
│   │   │       └── authority/               # Authority bounded context
│   │   │           ├── Role.kt              # Role enum
│   │   │           ├── constant.kt          # Domain constants
│   │   │           ├── AuthorityPrefixException.kt  # Domain exception
│   │   │           ├── write/               # Write side
│   │   │           │   ├── Authority.kt              # Authority entity
│   │   │           │   └── AuthorityRepository.kt    # JPA repository
│   │   │           └── read/                # Read side
│   │   │               └── AuthorityDocument.kt      # MongoDB document
│   │   └── resources/
│   │       ├── application.yaml             # Main application config
│   │       ├── database-dev.yaml            # PostgreSQL dev config
│   │       ├── database-test.yaml           # PostgreSQL test config
│   │       ├── database-prod.yaml           # PostgreSQL prod config
│   │       ├── mongodb-dev.yaml             # MongoDB dev config
│   │       ├── mongodb-test.yaml            # MongoDB test config
│   │       ├── mongodb-prod.yaml            # MongoDB prod config
│   │       ├── static/                      # Static resources
│   │       └── templates/                   # Templates
│   └── test/
│       └── kotlin/com/luke/kHelperServer/
│           ├── KHelpServerApplicationTests.kt
│           └── domain/db_sync/
│               └── EntitySyncHandlerManagerTest.kt
├── build.gradle.kts                 # Gradle build configuration
├── settings.gradle.kts              # Gradle settings
├── docker-compose.yml               # Docker services (PostgreSQL, MongoDB)
├── .env_sample                      # Environment variables sample
├── run_dev.sh                       # Development run script
├── gradlew                          # Gradle wrapper (Unix)
└── gradlew.bat                      # Gradle wrapper (Windows)
```

## Configuration Files

### build.gradle.kts
- Defines dependencies and plugins
- Java 17 toolchain
- Kotlin JVM and Spring plugins
- JPA plugin for entity generation

### application.yaml
- Active profile: dev
- Imports profile-specific configs for database and MongoDB

### docker-compose.yml
- **PostgreSQL 15**: Write database on configurable port
- **MongoDB 7**: Read database on configurable port
- Both services with health checks and persistent volumes

## Key Dependencies
- spring-boot-starter-data-jpa
- spring-boot-starter-data-mongodb
- spring-boot-starter-security
- spring-boot-starter-web
- kotlin-stdlib-jdk8
- kotlin-reflect
- jackson-module-kotlin
- postgresql (runtime)
- h2 (test only)
