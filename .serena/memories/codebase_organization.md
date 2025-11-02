# KHelpServer Codebase Organization

## Architecture Pattern

### CQRS (Command Query Responsibility Segregation)
The project implements a **CQRS pattern with Event Sourcing** for database synchronization:

- **Write Side (Command)**: PostgreSQL with JPA entities
- **Read Side (Query)**: MongoDB with documents
- **Event-Driven Sync**: Automatic synchronization via domain events

### Architecture Flow
```
1. Write Operation → PostgreSQL (JPA Entity)
2. JPA Listener → Publishes Domain Event
3. Event Handler → Listens to Event
4. Sync Handler → Updates MongoDB (Read Model)
5. Query Operation → MongoDB (Document)
```

## Package Naming Conventions

### Base Package
`com.luke.kHelperServer`

### Package Structure Pattern
```
com.luke.kHelperServer
├── configuration/        # Spring configuration classes
├── common/              # Shared utilities, exceptions, interfaces
└── domain/              # Domain-Driven Design (DDD) structure
    ├── [entity]/        # Bounded context per entity
    │   ├── write/       # Command side (PostgreSQL JPA)
    │   ├── read/        # Query side (MongoDB)
    │   └── event/       # Domain events for sync
    └── db_sync/         # Cross-cutting sync infrastructure
```

### Naming Patterns

#### Write Side (Command)
- **Entity**: `{Entity}.kt` (e.g., `Account.kt`)
- **JPA Repository**: `{Entity}JpaRepository.kt`
- **Command Repository Interface**: `{Entity}CommandRepository.kt`
- **Command Repository Impl**: `{Entity}JpaCommandRepository.kt`
- **Entity Listener**: `{Entity}EntityListener.kt`

#### Read Side (Query)
- **Document**: `{Entity}Document.kt` (e.g., `AccountDocument.kt`)
- **View/DTO**: `{Entity}View.kt`
- **Mongo Repository**: `{Entity}MongoRepository.kt`
- **Query Repository Interface**: `{Entity}QueryRepository.kt`
- **Query Repository Impl**: `{Entity}MongodbQueryRepository.kt`
- **Sync Handler**: `{Entity}SyncHandler.kt`

#### Events
- **Base Event**: `{Entity}CommittedEvent.kt`
- **Creation Event**: `{Entity}CreatedEvent.kt`
- **Update Event**: `{Entity}UpdatedEvent.kt`
- **Deletion Event**: `{Entity}DeletedEvent.kt` (if needed)

## Code Organization Patterns

### 1. Base Infrastructure Classes

#### BaseEntity
```kotlin
@MappedSuperclass
abstract class BaseEntity {
    @CreatedDate val createdAt: OffsetDateTime
    @LastModifiedDate val updatedAt: OffsetDateTime
}
```
- Located: `domain/BaseEntity.kt`
- Purpose: Audit fields for all JPA entities
- Used by: All write-side entities

#### BaseEntityListener
```kotlin
abstract class BaseEntityListener<T : BaseEntity>(
    private val eventPublisher: ApplicationEventPublisher
) {
    @PostPersist fun onPostPersist(entity: T)
    @PostUpdate fun onPostUpdate(entity: T)
    @PostRemove fun onPostRemove(entity: T)
}
```
- Located: `domain/db_sync/BaseEntityListener.kt`
- Purpose: Publishes events on entity lifecycle
- Used by: Entity-specific listeners (e.g., `AccountEntityListener`)

### 2. Event-Driven Synchronization Pattern

#### Event Publication Flow
```
JPA Entity Change
  → @EntityListeners(EntityListener)
  → BaseEntityListener methods
  → ApplicationEventPublisher.publishEvent(event)
  → WriteDbEventHandler receives event
  → EntitySyncHandlerManager routes to handler
  → Specific SyncHandler updates MongoDB
```

#### Key Components

**WriteDbCommitedEvent**
- Base interface for all sync events
- Located: `domain/WriteDbCommitedEvent.kt`

**EntitySyncHandler**
```kotlin
interface EntitySyncHandler<T : WriteDbCommitedEvent> {
    fun handle(event: WriteDbCommitedEvent)
    fun getHandlingMessageType(): KClass<T>
}
```
- Generic handler interface for events
- Implementations handle specific entity sync

**EntitySyncHandlerManager**
- Manages registry of all sync handlers
- Routes events to appropriate handler
- Located: `domain/db_sync/`

**WriteDbEventHandler**
- Listens to Spring application events
- Delegates to EntitySyncHandlerManager
- Located: `domain/db_sync/`

### 3. Domain-Driven Design Structure

#### Bounded Contexts
Each domain entity has its own bounded context:

**Account Bounded Context**
```
domain/account/
├── Email.kt              # Value object
├── OauthVendor.kt        # Enum
├── constant.kt           # Domain constants
├── write/                # Command model
├── read/                 # Query model
└── event/                # Domain events
```

**Authority Bounded Context**
```
domain/authority/
├── Role.kt               # Enum
├── constant.kt           # Domain constants
├── AuthorityPrefixException.kt  # Domain exception
├── write/                # Command model
└── read/                 # Query model
```

### 4. Repository Pattern

#### Write Side (JPA)
- **Interface**: Spring Data JPA repository
- **Wrapper**: Custom command repository for domain logic
- **Example**: `AccountJpaRepository` + `AccountCommandRepository`

#### Read Side (MongoDB)
- **Interface**: Spring Data MongoDB repository
- **Wrapper**: Custom query repository for read logic
- **Example**: `AccountMongoRepository` + `AccountQueryRepository`

### 5. Configuration Classes

#### MongoConfig
- Located: `configuration/MongoConfig.kt`
- Purpose: MongoDB connection configuration
- Customizes MongoDB client settings

## Main Entry Points

### Application Entry Point
```kotlin
// src/main/kotlin/com/luke/kHelperServer/KHelpServerApplication.kt
@SpringBootApplication
class KHelpServerApplication

fun main(args: Array<String>) {
    runApplication<KHelpServerApplication>(*args)
}
```

### Domain Entry Points (Per Bounded Context)

**Account Domain**
- Write: `Account` entity with `AccountCommandRepository`
- Read: `AccountDocument` with `AccountQueryRepository`
- Sync: `AccountSyncHandler` handles events

**Authority Domain**
- Write: `Authority` entity with `AuthorityRepository`
- Read: `AuthorityDocument`

## Important Classes and Symbols

### Core Infrastructure
1. **BaseEntity** (class) - Audit fields base class
2. **BaseEntityListener** (abstract class) - Event publishing base
3. **EntitySyncHandler** (interface) - Sync handler contract
4. **EntitySyncHandlerManager** (interface) - Handler registry
5. **WriteDbEventHandler** (interface) - Event listener contract

### Account Domain
6. **Account** (entity) - Write model for user accounts
7. **AccountDocument** (data class) - Read model for MongoDB
8. **AccountSyncHandler** (component) - Syncs account changes
9. **Email** (value object) - Email validation and encapsulation
10. **AccountEntityListener** (component) - Publishes account events

### Authority Domain
11. **Authority** (entity) - User authority/role entity
12. **Role** (enum) - Predefined roles
13. **AuthorityDocument** (data class) - Read model for MongoDB

### Events
14. **WriteDbCommitedEvent** (interface) - Base event
15. **AccountCommittedEvent** (sealed class) - Base account event
16. **AccountCreatedEvent** (data class) - Account creation
17. **AccountUpdatedEvent** (data class) - Account update

## Testing Structure

### Test Organization
```
src/test/kotlin/com/luke/kHelperServer/
├── KHelpServerApplicationTests.kt       # Integration tests
└── domain/db_sync/
    └── EntitySyncHandlerManagerTest.kt  # Unit tests
```

### Test Configuration
- Uses H2 in-memory database for tests
- JUnit 5 platform
- Spring Boot Test support
- Spring Security Test utilities

## Development Scripts

### run_dev.sh
- Shell script for local development
- Likely starts Spring Boot with dev profile
- Located at project root

## Key Design Decisions

1. **CQRS Pattern**: Separate read/write models for scalability
2. **Event-Driven Sync**: Automatic MongoDB sync via JPA listeners
3. **DDD Structure**: Clear bounded contexts per domain entity
4. **Type Safety**: Kotlin for null safety and conciseness
5. **Spring Native**: Full Spring Boot ecosystem integration
6. **Multi-Database**: PostgreSQL for writes, MongoDB for reads
7. **Docker Compose**: Easy local development environment

## Code Style Conventions

- **Kotlin**: Idiomatic Kotlin with data classes
- **Naming**: PascalCase for classes, camelCase for properties
- **Annotations**: Standard Spring/JPA annotations
- **Package Structure**: Feature-based (domain-driven)
- **Logging**: SLF4J logger pattern
- **Error Handling**: Custom exception interfaces
