package man.task.backend.entities;

import java.time.*;
import java.util.*;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@AllArgsConstructor
public class Task {
  @Id
  @GeneratedValue
  private UUID id;

  private String title;

  @Column(nullable = true)
  private String description;

  @Convert(converter = StatusConverter.class)
  private Status status;

  @Column(nullable = true)
  private LocalDate dueDate;

  @ManyToOne(optional = false)
  @JoinColumn(name = "asignee_id")
  private User asignee;

  @ManyToOne(optional = false)
  @JoinColumn(name = "project_id")
  private Project project;

  private LocalDateTime createdAt;

  public enum Status {
    PENDING("pending"),
    IN_PROGRESS("in-progress"),
    DONE("done");

    private String code;

    Status(String code) {
      this.code = code;
    }

    public String getCode() {
      return code;
    }

    public static Status fromCode(String code) {
      return Arrays
        .stream(values())
        .filter(s -> s.code.equals(code))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
    }
  }

  @Converter
  public static class StatusConverter implements AttributeConverter<Status, String> {
    @Override
    public String convertToDatabaseColumn(Status status) {
      return status.getCode();
    }

    @Override
    public Status convertToEntityAttribute(String code) {
      return Status.fromCode(code);
    }
  }
}
