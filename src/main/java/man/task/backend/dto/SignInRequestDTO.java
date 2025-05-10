package man.task.backend.dto;

import jakarta.validation.constraints.*;

public record SignInRequestDTO(
  @Email @NotNull String email,
  @NotNull String password
) {}
