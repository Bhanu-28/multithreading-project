
```markdown
# Java Microservices with Spring Boot, Docker & Kubernetes

This project is a hands-on implementation of microservices using **Spring Boot**, **Spring Cloud**, **Docker**, **Kubernetes**, and supporting tools like **Spring Data JPA**, **H2**, **RabbitMQ**, **Kafka**, **Prometheus**, **Grafana**, and **OpenAPI / Swagger**.

The goal is to build production-style microservices following industry best practices for **design**, **resilience**, **security**, **documentation**, and **deployment**.

---

## 🎯 What You Will Learn / Implement

- Why microservices vs monolith and SOA
- How to design and build Java microservices with Spring Boot following production standards
- How to:
  - Right‑size microservices and identify domain boundaries
  - Containerize microservices with **Docker**
  - Externalize and manage configurations with **Spring Cloud Config**
  - Implement **service discovery** and **registration** with **Eureka Server**
  - Build an **API gateway / edge server** with **Spring Cloud Gateway**
  - Make microservices **resilient** and **fault tolerant** using **Resilience4j** patterns
  - Add **monitoring and observability** using **Prometheus** and **Grafana**
  - **Secure** microservices with **OAuth2**, **OpenID**, and **Spring Security**
  - Build **event-driven microservices** using:
    - **RabbitMQ** + Spring Cloud Function / Spring Cloud Stream
    - **Kafka**
  - Deploy microservices to a **Kubernetes** cluster
  - Use **Helm** as the Kubernetes package manager and deploy to a cloud environment

---

## 🧱 Architecture Evolution

### 1. Monolithic Architecture

- All business functionality deployed as a **single unit** (single server, single database).
- UI (HTML, JS, CSS), business logic, and data access layer tightly coupled.
- **Pros**
  - Simple development and deployment for small teams and apps.
  - Cross‑cutting concerns (logging, security, auditing) are easier in a single codebase.
  - Better performance due to in‑process method calls (no network latency).
- **Cons**
  - Hard to adopt new technologies across teams.
  - Limited agility and difficult maintenance of a **single large codebase**.
  - Not fault tolerant: a small failure can bring down the entire app.
  - **Tiny update needs a full deployment**, causing downtime.
  - Scalability and availability issues.

### 2. SOA (Service Oriented Architecture)

- UI and backend logic separated.
- Communication via **Enterprise Service Bus (ESB)**.
- Uses **SOAP** and **XML** for communication.
- **Pros**
  - Parallel development.
  - Reusability of services.
  - Better maintainability than monoliths.
- **Cons**
  - SOAP + XML are heavy and complex vs REST + JSON.
  - ESB products are usually **commercial**, expensive, and add operational overhead.
  - Architecture becomes complex over time.

### 3. Microservices Architecture

- Application is built as a **suite of small services** modeled around **business domains**.
- Example: `accounts-service`, `cards-service`, `loans-service`.
- Each microservice:
  - Has its **own database**.
  - Runs in its **own process / container**.
  - Can be deployed **independently**.
- **Pros**
  - Easy to develop, test, and deploy small services.
  - Increased agility and parallel development.
  - Horizontal scalability.
  - Technology diversity:
    - Different services can use different languages and databases
      (e.g., Java + SQL, Python + NoSQL, Go, etc.).
- **Cons**
  - Higher complexity: service interactions, data consistency, observability.
  - Infrastructure overhead (service discovery, gateway, config, monitoring).
  - Additional security concerns across multiple services.
- The architecture prioritizes **independent deployability**.
  - No deployment dependency between microservices.
  - Agility and flexibility naturally emerge from this.

---

## 📖 Definition of Microservices

> **Microservices is an approach to develop a single application as a suite of small services.**  
> Each service:
> - Runs in its **own process**.
> - Communicates using **lightweight mechanisms** (mostly **REST over HTTP** with **JSON**).
> - Is built around **business capabilities** or **domains**.
> - Is **independently deployable** using fully automated deployment pipelines (CI/CD).

A typical pipeline:
- Developer commits code.
- Build triggers automatically.
- Artifacts are deployed to **DEV / UAT** automatically.
- Optional automated or semi‑automated deployment to production using CI/CD.

---

## 🚀 Spring Boot as the Microservice Framework

### Why Spring Boot?

- Built on top of the **Spring Framework**.
- Helps develop and deploy Java web applications and microservices **easily**.
- Lets you build **self‑contained**, **executable JARs** (fat/uber jars) instead of WAR/EAR.
  - JAR includes your code, dependencies, and an **embedded server** (Tomcat/Jetty/Undertow).
- Developer focuses mainly on **business logic**.  
  Spring Boot handles:
  - Bootstrapping.
  - Dependency wiring.
  - Server startup.
  - Packaging.

### Key Advantages

- **Auto Configuration**: Detects dependencies and configures components automatically.
- **Dependency Injection**: Standard Spring DI.
- **Embedded Servers**: Tomcat, Jetty, Undertow.
- **Production Ready Features** via **Spring Boot Actuator**:
  - Metrics.
  - Health checks.
  - Configuration exposure (carefully controlled).
- **Cloud‑ready**:
  - Easy Docker containerization.
  - Easy deployment to Kubernetes and cloud platforms.
- **Starter Dependencies**:
  - `spring-boot-starter-web`
  - `spring-boot-starter-data-jpa`
  - `spring-boot-starter-actuator`
  - `spring-boot-starter-validation`
  - etc.

---

## 🧩 Project Setup and Dependencies

Typical dependencies being used right now:

- **Spring Web**
  - For building REST APIs and MVC web applications.
  - Uses **embedded Tomcat** by default.
- **H2 Database**
  - In‑memory database for local and demo use.
- **Spring Data JPA**
  - Database interaction (CRUD, queries) with minimal boilerplate.
- **Spring Boot Actuator**
  - Production metrics, health, and configuration endpoints.
- **Spring Boot DevTools**
  - Fast restarts and live reload during development.
- **Lombok**
  - Eliminates boilerplate getters/setters/constructors.
- **Spring Boot Validation**
  - Bean validation on DTOs and request parameters.
- **Springdoc OpenAPI (Swagger UI)**  
```

<dependency>

<groupId>org.springdoc</groupId>

<artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>

<version>2.8.15</version>

</dependency>

```

---

## 🗄️ Database & Configuration

### H2 Configuration with YAML

`application.yml` example:

```

```
server:

port: 8080

spring:

datasource:

url: jdbc:h2:mem:testdb

driver-class-name: org.h2.Driver

username: sa

password: ''

h2:

console:

enabled: true

jpa:

database-platform: org.hibernate.dialect.H2Dialect

hibernate:

ddl-auto: update

show-sql: true

```

Notes:

- Using **YAML** instead of `.properties`:
  - More readable and intuitive.
  - Widely used across **Docker**, **Kubernetes**, **cloud providers**.
- H2 console:
  - Enabled for interactive DB inspection.
  - Accessible at: `http://localhost:8080/h2-console` (or your configured path).

### Schema and Data Initialization

- `schema.sql` – table creation:
```

CREATE TABLE IF NOT EXISTS customer (

customer_id   INT AUTO_INCREMENT PRIMARY KEY,

name          VARCHAR(100) NOT NULL,

email         VARCHAR(100) NOT NULL,

mobile_number VARCHAR(20)  NOT NULL,

created_at    DATE         NOT NULL,

created_by    VARCHAR(20)  NOT NULL,

updated_at    DATE DEFAULT NULL,

updated_by    VARCHAR(20) DEFAULT NULL

);

CREATE TABLE IF NOT EXISTS accounts (

customer_id    INT NOT NULL,

account_number INT AUTO_INCREMENT PRIMARY KEY,

account_type   VARCHAR(100) NOT NULL,

branch_address VARCHAR(200) NOT NULL,

created_at     DATE         NOT NULL,

created_by     VARCHAR(20)  NOT NULL,

updated_at     DATE DEFAULT NULL,

updated_by     VARCHAR(20) DEFAULT NULL

);

```

- `data.sql` – for initial seed data (optional).

---

## 🧬 Entities, Repositories & JPA Auditing

### Base Entity

A shared superclass for metadata columns:

- `createdAt`, `createdBy`, `updatedAt`, `updatedBy`.

Key annotations:

```

@EntityListeners(AuditingEntityListener.class)

@Getter

@Setter

@ToString

public class BaseEntity {

@CreatedDate

@Column(updatable = false)

private LocalDateTime createdAt;

@CreatedBy

@Column(updatable = false)

private String createdBy;

@LastModifiedDate

@Column(insertable = false)

private LocalDateTime updatedAt;

@LastModifiedBy

@Column(insertable = false)

private String updatedBy;

}

```

- `@MappedSuperclass` on BaseEntity so JPA does not create its own table but shares fields with child entities.
- `@Column(updatable = false)` / `@Column(insertable = false)` to control when fields are set.

### Auditing Setup

```

@Component("auditAwareImpl")

public class AuditAwareImpl implements AuditorAware<String> {

@Override

public Optional<String> getCurrentAuditor() {

// TODO: integrate with Spring Security later.

return Optional.of("Accounts_MS");

}

}

@SpringBootApplication

@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")

public class AccountsApplication {

public static void main(String[] args) {

[SpringApplication.run](http://SpringApplication.run)(AccountsApplication.class, args);

}

}

```

- Spring Data JPA will automatically populate audit columns using this configuration.

### Entity & Repository Patterns

- Entities use:
  - `@Entity`
  - `@Id`, `@GeneratedValue(strategy = [GenerationType.AUTO](http://GenerationType.AUTO), generator = "native")`
  - Lombok: `@Getter`, `@Setter`, `@AllArgsConstructor`, `@NoArgsConstructor`.
- Repositories:
  - Extend Spring Data JPA interfaces (`JpaRepository`).
  - Custom finder methods using **derived query methods**, e.g.:
```

Optional<Customer> findByMobileNumber(String mobileNumber);

```
  - For modifying custom queries:
    - Use `@Modifying` + `@Transactional`.

---

## 🎁 DTO (Data Transfer Object) Pattern

### Why DTOs?

- Entities are **database-focused** and should not be exposed directly to clients.
- Different clients may need **different combinations** or **views** of data:
  - e.g., combined `Customer` + `Account` info.
- DTOs:
  - Reduce network traffic (send only required fields).
  - Encapsulate **serialization logic** (JSON, XML, YAML, etc.).
  - Decouple **presentation layer** from **data access layer**.
  - Changes in DB schema do not directly break API contracts.

### Example DTO

```

@Data

public class CustomersDto {

private String name;

private String email;

private String mobileNumber;

}

```

- DTOs use Lombok `@Data`:
  - Generates getters, setters, `toString`, `equals`, `hashCode`, etc.
- Entities typically avoid `@Data` for safety with JPA.

### Mapping

- Mapping between Entity and DTO is currently done **manually** using mapper classes, e.g.:

  - `CustomersMapper.mapToCustomer(Customer, CustomersDto)`

- Libraries like **ModelMapper** and **MapStruct** exist but may not always be accepted in strict production environments due to governance or security concerns.
- Manual mapping gives full control for custom logic, e.g.:
  - Masking mobile numbers.
  - Transforming formats.

---

## 🧪 REST APIs and Best Practices

### REST Basics

- Microservices expose their business logic using **REST APIs**.
- REST is:
  - Lightweight.
  - Based on **HTTP**.
  - Typically uses **JSON** for payloads.
- Common communication patterns:
  - Mobile App → Backend via REST.
  - Backend → Backend via REST.
  - Web App (Angular, React, etc.) → Backend via REST.

### HTTP Method Conventions

- **POST** – Create new resources.
- **GET** – Read resources.
- **PUT / PATCH** – Update existing resources.
- **DELETE** – Delete resources.

### Input Validation

- Add dependency: `spring-boot-starter-validation`.
- Validate on **DTOs**, e.g.:

```

@Data

public class CustomerDto {

@NotEmpty(message = "Name can not be a null or empty")

@Size(min = 5, max = 30,

message = "The length of the customer name should be between 5 and 30")

private String name;

@NotEmpty(message = "Email address can not be a null or empty")

@Email(message = "Email address should be a valid value")

private String email;

@Pattern(regexp = "(^$|[0-9]{10})",

message = "Mobile number must be 10 digits")

private String mobileNumber;

private AccountDto accountDto;

}

```

- At controller level:
  - Use `@Validated` on controller class.
  - Use `@Valid` on parameters annotated with `@RequestBody`.
  - Use validation annotations directly on `@RequestParam` or `@PathVariable`.

### Controllers & REST Endpoints

- Use `@RestController` for REST APIs.
  - Or `@Controller + @ResponseBody` combination if needed.
- Example service method pattern:

```

@Service

@AllArgsConstructor

public class AccountsServiceImpl implements IAccountsService {

private AccountRepository accountRepository;

private CustomerRepository customerRepository;

@Override

public void createAccount(CustomersDto customerDto) {

Customer customer = CustomersMapper.mapToCustomer(new Customer(), customerDto);

Customer savedCustomer = [customerRepository.save](http://customerRepository.save)(customer);

// Create Account entity using savedCustomer.getCustomerId()

// and save via [accountRepository.save](http://accountRepository.save)(...)

}

}

```

- For responses, use **`ResponseEntity<T>`**:
  - To send **status**, **headers**, and **body**.
  - Allows more control than returning DTO alone.

---

## ⚠️ Global Exception Handling

### Custom Exceptions

- Example: `CustomerAlreadyExistsException` when mobile number is already registered.

### Global Handler

- Use `@ControllerAdvice` (or `@RestControllerAdvice`) and `@ExceptionHandler`:

```

@ControllerAdvice

public class GlobalExceptionHandler {

@ExceptionHandler(CustomerAlreadyExistsException.class)

public ResponseEntity<ErrorResponseDto> handleCustomerAlreadyExistsException(

CustomerAlreadyExistsException exception,

WebRequest webRequest) {

ErrorResponseDto errorResponseDto = new ErrorResponseDto(

webRequest.getDescription(false),

HttpStatus.BAD_REQUEST,

exception.getMessage(),

[LocalDateTime.now](http://LocalDateTime.now)()

);

return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);

}

}

```

- To also handle **validation errors**, extend `ResponseEntityExceptionHandler`:
  - Override `handleMethodArgumentNotValid(...)` to return a map of field → error message.

---

## 📚 API Documentation with Springdoc OpenAPI

- Use **Springdoc OpenAPI** to automatically generate Swagger UI.
- Access UI at:  
  `http://localhost:8080/swagger-ui/index.html`

### Basic Configuration

```

@OpenAPIDefinition(

info = @Info(

title = "Accounts Microservice REST API Documentation",

description = "Here we Create, Fetch, Update and Delete Account Details",

version = "v1",

contact = @Contact(

name = "Bhanu Pradeep",

email = "[gorrebhanupradeepkumar@gmail.com](mailto:gorrebhanupradeepkumar@gmail.com)",

url = "https://www.linkedin.com/..."

)

)

)

public class OpenApiConfig {

}

```

### Per-Endpoint Annotations

Use:

- `@Tag` – to group endpoints.
- `@Operation` – for summary and description.
- `@ApiResponses` / `@ApiResponse` – for documenting response status, types, and error payloads.
- `@Schema` / `@Content` – for describing DTOs and error objects.

Example:

```

@Operation(

summary = "Update Account Details REST API",

description = "REST API to update Customer & Account details based on an account number"

)

@ApiResponses({

@ApiResponse(responseCode = "200", description = "HTTP Status OK"),

@ApiResponse(responseCode = "417", description = "Expectation Failed"),

@ApiResponse(

responseCode = "500",

description = "HTTP Status Internal Server Error",

content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))

)

})

```

- DTO validation annotations are also reflected in the Swagger UI, helping clients understand:
  - Required fields.
  - Patterns.
  - Length constraints.
  - Example values (if configured).

---

## 🧠 Java Generics Refresher (Used in ResponseEntity, Repositories, etc.)

Generics are used heavily in:

- `ResponseEntity<T>`
- `JpaRepository<Entity, ID>`
- Utility classes and DTO helpers.

Examples:

```

public class Box<T> {

private T value;

public void set(T value) { this.value = value; }

public T get() { return value; }

}

public class Pair<K, V> {

private K key;

private V value;

// ...

}

```

Bounded types:

```

public class Calculator<T extends Number> {

public double doubleValue(T num) {

return num.doubleValue();

}

}

```

---

## ✅ Current Status Summary


- Understood and documented:
  - Evolution from **Monolith → SOA → Microservices**.
  - Pros and cons of each architecture.
- Set up:
  - Spring Boot with starter dependencies.
  - H2 database + `schema.sql`.
  - YAML‑based configuration.
- Implemented:
  - Entities + base auditing entity.
  - Spring Data JPA repositories.
  - DTO pattern (CustomerDto, AccountDto, ResponseDto, etc.).
  - Manual mapping via mapper classes.
  - Service layer (`AccountsServiceImpl`).
  - REST controllers using `@RestController`, `ResponseEntity`, and validation.
  - Global exception handling with `@ControllerAdvice` + `@ExceptionHandler`.
  - Input validation with Bean Validation annotations.
  - Spring Data JPA auditing for metadata columns.
  - OpenAPI / Swagger documentation with Springdoc.


- Standardize all runtime exception handling and auditing.
- Finish full CRUD operations for:
  - Accounts microservice.
  - Repeat patterns for **Cards** and **Loans** microservices.
- Enhance OpenAPI docs with better examples and tags.
- Move further into:
  - Resilience patterns (Resilience4j).
  - Security (OAuth2, OpenID, Spring Security).
  - Event-driven communication (RabbitMQ, Kafka).
  - Dockerization and Kubernetes deployment (with Helm).

---

## 📂 Repository / Commits

- For a reference of the CRUD changes, check the merge commit:  
  `Merge commit for CRUD operations: https://github.com/Bhanu-28/multithreading-project/commit/5edf6a302934d13f0d0ff7a54bdeeb82b9232bea`

---


```

```

```