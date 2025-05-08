# TaskMan Backend

This repository is the backend for TaskMan, a simple kanban-like task/project
manager.

## How to run

- Add the database URL propery on `src/main/resources/application.properties`.
  ```jproperties
  spring.datasource.url=jdbc:postgresql:${REST_OF_URL}
  ```

  - If using [Neon], make sure to follow the steps listed
    [here](https://neon.tech/docs/guides/java).
- Run with the command below (Unix-like).
  ```sh
  ./mvnw spring-boot:run

  ```

[Neon]: https://neon.tech/home
