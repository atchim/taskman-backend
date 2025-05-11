package man.task.backend.dto;

import jakarta.validation.constraints.*;

public record DeleteProjectRequestDTO(@NotEmpty String id) {}
