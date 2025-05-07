package man.task.backend.domain.project;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;
import man.task.backend.domain.user.User;

@Entity
@Table(name = "projects")
@Getter
@Setter
@NoArgsConstructor
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

  @Column(insertable = false)
  private LocalDateTime createdAt;
}
