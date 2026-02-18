# ApexMatch

>A lightweight order matching engine (Spring Boot sample) — accepts orders and provides an order book snapshot.

## Overview

ApexMatch is a small Java application that demonstrates a simple limit order matching engine. It exposes a REST API to submit orders and retrieve the current order book snapshot.

## Key Features

- REST endpoint to submit new orders
- Order book snapshot endpoint
- Simple in-memory matching engine suitable for demos and learning

## Requirements

- JDK 11 or later
- Maven (or use the provided `mvnw` / `mvnw.cmd` wrappers)

## Build

Using the wrapper (Windows):

```powershell
./mvnw.cmd clean package
```

Using a system Maven:

```bash
mvn clean package
```

## Run

Run the packaged Spring Boot application:

```bash
java -jar target/*.jar
```

Or run via Maven (development):

```bash
mvn spring-boot:run
```

## API

- POST `/order` — submit a new order. Expects JSON body matching `OrderRequestDTO` (id, price, qty, type).
- GET `/order/book` — returns the current order book snapshot (`OrderBookResponseDTO`).

Example `curl` to submit an order:

```bash
curl -X POST http://localhost:8080/order \
  -H "Content-Type: application/json" \
  -d '{"id":"o1","price":100.5,"qty":10,"type":"BUY"}'
```

Example `curl` to get the book:

```bash
curl http://localhost:8080/order/book
```

## Project Structure (short)

- `src/main/java/.../controller` — REST controllers
- `src/main/java/.../service` — matching service logic
- `src/main/java/.../engine` — order book engine
- `src/main/java/.../dto` — request/response DTOs
- `src/main/java/.../model` — domain models (Order, Trade, etc.)

## Tests

Run tests with:

```bash
mvn test
```

## Contributing

PRs and issues are welcome. Keep changes focused and add tests for new behavior.

## License

This repository does not include a license file. Check with the project owner before using code in production.
