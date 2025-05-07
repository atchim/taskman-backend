package man.task.backend.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "projects")
@Getter
@Setter
@AllArgsConstructor
public class Project {
  @Id
  @GeneratedValue
  private UUID id;

  private String name;

  @Column(nullable = true)
  private String description;

  @ManyToOne(optional = false)
  @JoinColumn(name = "owner_id")
  private User user;

  private LocalDateTime createdAt;
}
