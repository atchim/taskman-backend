package man.task.backend.dto;

import jakarta.validation.constraints.*;

public record SignUpRequestDTO(
  @Email @NotNull String email,
  @NotEmpty String name,
  @Size(min = 6) @NotNull String password
) {}
