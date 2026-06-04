# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build & Run

```bash
# Start PostgreSQL (required before running the app)
docker-compose up -d

# Run the application (port 8090)
mvn spring-boot:run

# Build
mvn clean package

# Run all tests
mvn test

# Run a single test class
mvn test -Dtest=SetUpdateServiceTest

# Run a single test method
mvn test -Dtest=SetUpdateServiceTest#updateSets
```

Swagger UI: http://localhost:8090/swagger-ui.html

## Architecture

Spring Boot 3.4.0 / Java 21 backend for a Magic: The Gathering memory game. Root package: `de.andwari.memory.backend`.

| Package | Purpose |
|---------|---------|
| `web/rest/` | REST controllers (3 total) |
| `web/client/` | Feign HTTP client for Scryfall API |
| `service/` | Business logic services |
| `scheduler/` | Cron-based task system with DB persistence |
| `scheduler/task/` | Scheduled task implementations |
| `scheduler/db/` | TaskEntity, TaskRepository |
| `db/entity/` | JPA entities |
| `db/repository/` | Spring Data JPA repositories |
| `mapper/` | MapStruct mappers (6 total) |
| `model/rest/` | REST DTOs (Java records) |
| `model/scryfall/` | Scryfall API response models |
| `model/enums/` | CardType, CardLayout, SetType, ShapeType |
| `model/constants/` | DefaultMasks (shape coordinates for seed data) |
| `config/` | MagicMemoryConfig (registers EndpointInterceptor) |
| `interceptor/` | EndpointInterceptor (logs all HTTP requests) |

Schema is managed by Hibernate `hbm2ddl.auto: update` — no migration framework.

## REST Endpoints

### `AppRestController` — `/admin`
| Method | Path | Purpose |
|--------|------|---------|
| GET | `/admin/test` | Trigger set sync from Scryfall |
| POST | `/admin/update-cards/{set-code}` | Sync all cards for a set from Scryfall |
| GET | `/admin/sets` | All sets filtered to EXPANSION type, with ready card count |
| GET | `/admin/cards/{scryfallId}` | Fetch one card by Scryfall ID |
| GET | `/admin/cards?code=<code>` | All cards for a set |
| POST | `/admin/cards` | Update a card's shapes and ready status |

### `TimerRestController` — `/timer`
| Method | Path | Purpose |
|--------|------|---------|
| GET | `/timer` | List all tasks with their status and cron |
| PUT | `/timer/start/{task}` | Activate a scheduled task |
| PUT | `/timer/stop/{task}` | Deactivate a scheduled task |
| PUT | `/timer/execute/{task}` | Run a task immediately (one-shot) |

### `ShapeRestController` — `/shapes`
| Method | Path | Purpose |
|--------|------|---------|
| GET | `/shapes` | All shapes |
| POST | `/shapes` | Create a new shape |

CORS: all controllers allow `http://localhost:4200`.

## Key Domain Concepts

- **Set** — A Magic card set synced from Scryfall; only `EXPANSION` type sets are exposed to the frontend.
- **Card** — Belongs to a Set; has `cardType`, `cardLayout`, `shapes`, `ready`, `pictureUri`, `manaCost`. Shapes are stored directly on the card (ManyToMany via `card_shape` join table).
- **Mask** — Named collection of `Shape` entities used only as assignment templates; not stored on cards directly.
- **Shape** — A rectangular region (x, y, width, height) of type `ShapeType` (MANA, TYPE, TEXT, PT, LOYALTY). Shared across cards and masks via join tables.
- **MaskMatcher** — On initial card sync, copies shapes from a matching default mask onto the card. Runs when: layout is NORMAL and mana cost ≤ 3. Creatures/artifact-creatures get the creature mask shapes; spells/artifacts/land/enchantment/instant/sorcery get the spell mask shapes.
- **DefaultMaskInitializer** — `@PostConstruct` bean that seeds two default masks if the DB is empty: `DEFAULT_SPELL_3M` (mana, type, text shapes) and `DEFAULT_CREATURE_3M` (mana, type, text, pt shapes).

## Services

| Service | Responsibility |
|---------|---------------|
| `SetUpdateService` | Syncs Magic sets from Scryfall `/sets` endpoint |
| `CardsUpdateService` | Syncs cards for a set (paginated; 100ms sleep between pages to respect rate limits) |
| `SetService` | Queries sets filtered by EXPANSION, counts ready cards |
| `CardService` | Retrieves/updates individual cards; loads shapes by ID from `ShapeRepository` on update |
| `ShapeService` | CRUD for Shape entities |
| `MaskMatcher` | Copies shapes from a default mask onto a card based on type/layout/cost (initial sync only) |
| `DefaultMaskInitializer` | Seeds default masks on startup |
| `CardTypeService` | Converts Scryfall type strings → CardType enum (handles "Legendary" prefix) |
| `QueryService` | URL-encodes Scryfall search queries |
| `SchedulerService` | Manages ThreadPoolTaskScheduler; start/stop/execute tasks; persists state |
| `TaskProvider` | Registry mapping Task enum → task bean implementations |

## JPA Entities

| Entity | Table | Key Fields |
|--------|-------|-----------|
| `SetEntity` | `set` | scryfallId, code, name, url, type (SetType), releaseDate, cards (count), iconUrl, released |
| `CardEntity` | `card` | scryfallId, name, cardType (enum), cardLayout (enum), set (FK→Set), pictureUri, manaCost, shapes (ManyToMany via `card_shape`), ready |
| `MaskEntity` | `mask` | name (unique), standard, shapes (ManyToMany via `mask_shape`) — template only, not referenced by cards |
| `ShapeEntity` | `shape` | type (ShapeType), x, y, width, height |
| `TaskEntity` | `task` | task (Task enum: GET_SETS / DUMMY), status (ACTIVE / INACTIVE), cron |

All entities have `createdAt` / `updatedAt` timestamps.

## Repositories

| Repository | Custom queries |
|------------|---------------|
| `CardRepository` | `findByScryfallId`, `findBySetCode` |
| `SetRepository` | `findByScryfallId`, `findByCode` |
| `MaskRepository` | `findByName` |
| `ShapeRepository` | (standard CRUD only) |
| `TaskRepository` | `findByTask`, `findAllByStatus` |

## MapStruct Mappers

| Mapper | Converts |
|--------|---------|
| `SetMapper` | CardEntity ↔ Scryfall Set response ↔ SetModel |
| `CardMapper` | Scryfall CardData → CardEntity (uses CardTypeService, CardLayoutMapper) |
| `CardModelMapper` | CardEntity ↔ CardModel / ShapeModel |
| `CardLayoutMapper` | String → CardLayout enum (normal, transform) |
| `CardTypeMapper` | String → CardType enum (14 value mappings) |
| `TaskMapper` | TaskEntity → TimerModel |

## Database

PostgreSQL 16 via Docker. Connection defaults: `localhost:5432`, db `memory`, user/pass `andwari`.

Override via env vars: `SQL_DB_URL`, `SQL_DB_PORT`, `SQL_DB_USERNAME`, `SQL_DB_PASSWORD`.

## Testing

| Test | Type | Tools |
|------|------|-------|
| `SetUpdateServiceTest` | Unit | Mockito / MockitoExtension |
| `SchedulerServiceTest` | Integration | TestContainers (real PostgreSQL) |
| `ScryfallClientTest` | Integration | WireMock (Spring Cloud Contract Stub Runner) |

Helpers: `TestDataFactory.java` (entity/DTO factories), `DockerContainerExtension` (JUnit 5 extension for container lifecycle), `TestConfig` (Spring test config).
