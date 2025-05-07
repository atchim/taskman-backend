package man.task.backend.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
public class User {
  @Id
  @GeneratedValue
  private UUID id;

  private String name;
  private String email;
  private String password;
  private LocalDateTime createdAt;
}
