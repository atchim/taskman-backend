package man.task.backend.dto;

public record SignUpRequestDTO (
  String email,
  String name,
  String password
) {}
