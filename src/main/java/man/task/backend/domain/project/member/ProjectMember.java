package man.task.backend.domain.project.member;

import java.util.*;

import jakarta.persistence.*;
import lombok.*;
import man.task.backend.domain.project.Project;
import man.task.backend.domain.user.User;

@Entity
@Table(name = "project_members")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectMember {
  @Id
  @GeneratedValue
  private UUID id;

  @ManyToOne(optional = false)
  @JoinColumn(name = "project_id")
  private Project project;

  @ManyToOne(optional = false)
  @JoinColumn(name = "user_id")
  private User user;

  @Enumerated(EnumType.STRING)
  private Role role;

  public enum Role { OWNER, COLLABORATOR; }
}
