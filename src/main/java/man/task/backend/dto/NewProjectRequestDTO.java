package man.task.backend.dto;

import jakarta.validation.constraints.*;

public record NewProjectRequestDTO(
  @NotEmpty String name,
  String description
) {}
