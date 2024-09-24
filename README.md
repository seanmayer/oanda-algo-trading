# OANDA Trading API Integration

This project is a Spring Boot application that integrates with the OANDA v20 REST API to provide trading functionalities. The application exposes various endpoints to interact with OANDA services, such as fetching market data and managing orders.

## Table of Contents

- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [Building the Project](#building-the-project)
- [Running the Application](#running-the-application)
- [Accessing the API Documentation](#accessing-the-api-documentation)
- [Java Version Compatibility](#java-version-compatibility)
- [Known Issues](#known-issues)
- [Contact](#contact)

## Features

- **Market Data Retrieval**
   - Fetch candlestick data for instruments.
   - Retrieve order books and position books.
- **Account Management**
   - Get account details and summaries.
   - List tradable instruments for an account.
- **Order Management**
   - Create new orders.
   - Retrieve existing orders and pending orders.
   - Get details of specific orders.
- **API Documentation**
   - Integrated OpenAPI (Swagger) specification.
   - Interactive Swagger UI for API exploration.

## Prerequisites

- **Java Development Kit (JDK) 11**
   - The application is built and runs on Java 11.
- **OANDA Account**
   - A valid OANDA trading account.
   - An OANDA API token with appropriate permissions.
- **Maven**
   - For building and managing the project dependencies.

## Installation

1. **Clone the Repository**

   ```bash
   gh repo clone seanmayer/oanda-algo-trading
   ```

2. **Navigate to the Project Directory**

   ```bash
   cd oanda-trading-api
   ```

## Configuration

Update the `application.properties` file located in `src/main/resources` with your OANDA API token and environment settings.

```properties
# OANDA API Configuration
oanda.api.token=YOUR_OANDA_API_TOKEN
oanda.api.environment=practice  # Use 'live' for live trading
```

## Building the Project

Use Maven to clean, compile, and package the application:

```bash
mvn clean install
```

This command will:

- Compile the source code.
- Run unit tests.
- Package the application into an executable JAR file.
- Generate the OpenAPI specification file (`openapi.yaml`).

## Running the Application

Start the Spring Boot application using Maven:

```bash
mvn spring-boot:run
```

Alternatively, you can run the packaged JAR file:

```bash
java -jar target/oanda-trading-api-1.0.0.jar
```

The application will start and listen on `http://localhost:8080`.

## Accessing the API Documentation

### Swagger UI

The application uses OpenAPI (Swagger) for API documentation. Access the Swagger UI at:

```
http://localhost:8080/swagger-ui.html
```

This interface provides:

- Detailed information about all available endpoints.
- Request and response models.
- Ability to interact with the API directly from the browser.

### OpenAPI Specification File

The OpenAPI specification file (`openapi.yaml`) is generated during the build process and is located in the `src/main/resources/static/docs` directory.

You can also access the OpenAPI specification directly via:

```
http://localhost:8080/docs/openapi.yaml
```

This file can be used for:

- Generating client SDKs.
- Importing into API testing tools like Postman.
- Sharing API structure with other developers.

## Java Version Compatibility

- **Application Java Version**: **Java 11**
   - The application is developed and runs on Java 11.
- **OANDA SDK Java Version**: **Java 1.8 (Java 8)**
   - The OANDA v20 SDK is compiled with Java 1.8.

**Note:** While the application uses Java 11, it remains compatible with the OANDA SDK compiled in Java 1.8. Ensure that your build configurations account for this compatibility.

## Known Issues

- **Reflection Access Errors**
   - When running the application on Java 11, you might encounter reflection access errors due to Java's module system.
   - **Solution:** Downgrade to Java 11 resolved the issue. Ensure you're using Java 11, as higher versions may require additional JVM arguments to open specific modules.

- **Gson Compatibility with Java Modules**
   - The OANDA SDK uses Gson for JSON serialization/deserialization, which may cause issues with Java's module system.
   - **Solution:** Ensure you're using compatible versions of Gson and consider adding JVM arguments if necessary.

## Contact

For questions, issues, or contributions, please contact:

- **Name:** Sean Mayer
- **Website:** [https://scalablehuman.com](https://scalablehuman.com)

---

**Disclaimer:** Trading Forex and CFDs involves significant risk of loss and is not suitable for all investors. Ensure you fully understand the risks involved before trading.

---