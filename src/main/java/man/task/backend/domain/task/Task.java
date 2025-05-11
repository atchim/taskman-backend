package man.task.backend.domain.task;

import java.time.*;
import java.util.*;

import jakarta.persistence.*;
import lombok.*;
import man.task.backend.domain.project.Project;
import man.task.backend.domain.user.User;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {
  @Id
  @GeneratedValue
  private UUID id;

  private String title;

  @Column(nullable = true)
  private String description;

  @Enumerated(EnumType.STRING)
  private Status status;

  @Column(nullable = true)
  private LocalDate dueDate;

  @ManyToOne(optional = false)
  @JoinColumn(name = "asignee_id")
  private User asignee;

  @ManyToOne(optional = false)
  @JoinColumn(name = "project_id")
  private Project project;

  @Column(insertable = false)
  private LocalDateTime createdAt;

  public enum Status { PENDING, IN_PROGRESS, DONE; }
}
