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

## Routes

- `/sign-up`
  - `POST`
    - `{name: String, email: String, password: String}`
- `/sign-in`
  - `POST`
    - `{email: String, password: String}`
- `/project`
  - `GET`
  - `POST`
    - `{name: String, description?: String}`
  - `DELETE`
    - `{id: String}`

## Future Improvements

[ ] Properly handle errors on routes (current implementation uses
    `RuntimeException`, which is not ideal).

[Neon]: https://neon.tech/home
