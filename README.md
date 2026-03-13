# ApexMatch

Lightweight Spring Boot order-matching service with a REST API for submitting limit orders and reading an in-memory order book snapshot.

## Tech Stack

- Java 17
- Spring Boot 3.5.10
- Spring Web, Spring Data JPA, Bean Validation
- H2 in-memory database
- Maven

## Current Project Status

The codebase contains the full matching domain flow (controller -> service -> queue -> order book), but the application context currently fails to start because `OrderQueue` is not registered as a Spring bean while `MatchingService` requires it via constructor injection.

Observed from test run:

- `mvn test` fails at `ApexmatchApplicationTests.contextLoads`
- Root cause: `No qualifying bean of type 'cto.iamskrai.apexmatch.engine.OrderQueue' available`

## API

Base URL: `http://localhost:8080`

### `POST /order`

Submits a new order.

Request body (`OrderRequestDTO`):

```json
{
  "id": 101,
  "price": 100,
  "qty": 10,
  "type": "BUY"
}
```

Validation rules:

- `id >= 1`
- `price >= 1`
- `qty >= 1`
- `type` is required (`BUY` or `SELL`)

Success response (`OrderResponseDTO`):

```json
{
  "id": 101,
  "status": "Accepted"
}
```

### `GET /order/book`

Returns the current in-memory order book grouped by price.

Response shape (`OrderBookResponseDTO`):

```json
{
  "buyOrderBook": {
    "100": [
      "OrderId:101 Qty:10"
    ]
  },
  "sellOrderBook": {
    "101": [
      "OrderId:102 Qty:5"
    ]
  }
}
```

## Matching Behavior

Implemented in `OrderBook`:

- BUY orders match against the lowest available sell price first.
- SELL orders match against the highest available buy price first.
- Matching uses price-time priority within each price level (FIFO queue).
- Partial fills are supported.
- Trade price is the resting order's price level being matched.
- Remaining quantity is inserted back into the correct side of the book.

## Persistence and Runtime Data

- Incoming orders are persisted using `OrderRepository` (`orders` table).
- Trades are created in memory inside `OrderBook.tradeList`.
- `TradeRepository` exists but is not currently used by service logic.

## Build and Run

### Using system Maven

```bash
mvn clean package
mvn spring-boot:run
```

### Using Maven wrapper

```powershell
./mvnw.cmd clean package
./mvnw.cmd spring-boot:run
```

If wrapper execution fails on your machine, use installed Maven (`mvn`) instead.

## Database / H2 Console

Configured in `src/main/resources/application.properties`:

- JDBC URL: `jdbc:h2:mem:tradingdb`
- Username: `sa`
- Password: *(empty)*
- H2 console: `http://localhost:8080/h2-console`

## Tests

Run:

```bash
mvn test
```

Current result: fails due to missing Spring bean wiring for `OrderQueue`.

## Project Layout

- `src/main/java/.../controller` - REST controllers
- `src/main/java/.../service` - service orchestration
- `src/main/java/.../engine` - matching engine, order book, queue
- `src/main/java/.../dto` - API DTOs
- `src/main/java/.../model` - JPA entities and enums
- `src/main/java/.../repository` - Spring Data repositories
- `src/main/java/.../exception` - validation exception handler class
