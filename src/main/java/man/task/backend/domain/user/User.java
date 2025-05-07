package man.task.backend.domain.user;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
  @Id
  @GeneratedValue
  private UUID id;

  private String name;
  private String email;
  private String password;

  @Column(insertable = false)
  private LocalDateTime createdAt;
}
