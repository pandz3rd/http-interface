# Spring Boot HTTP Interface Sample

This project is a sample Spring Boot application demonstrating how to use **Spring HTTP Interface (`@HttpExchange`)** with `RestClient` to call external REST APIs in a clean and type-safe way.

---

## 🚀 Tech Stack

- Java 17
- Spring Boot 4.0.3
- HTTP Interface (`@HttpExchange`)

---

## 📌 Overview

This service demonstrates:

- Creating an HTTP Interface client
- Sending `@RequestBody`
- Handling `ResponseEntity`

---

## 🧩 HTTP Interface Example

```java
@HttpExchange("/users")
public interface UserClient {

    @GetExchange("/{id}")
    ApiBaseResponse<User> getUser(@PathVariable Long id);

    @PostExchange
    ApiBaseResponse<User> createUser(@RequestBody CreateUserRequest request);
}
```

--

## 🔧 Client Configuration

```java
@Configuration
public class UserClientConfig {

    @Bean
    public UserClient userClient(
            @Value("${external.user-service.base-url}") String baseUrl,
            RestClient.Builder builder) {

        RestClient restClient = builder
                .baseUrl(baseUrl)
                .build();

        return HttpServiceProxyFactory
                .builderFor(RestClientAdapter.create(restClient))
                .build()
                .createClient(UserClient.class);
    }
}
```

--

## 🏃 Running the Application

```bash
mvn spring-boot:run
```
