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

  @Convert(converter = RoleConverter.class)
  private Role role;

  public enum Role {
    OWNER("owner"),
    COLLABORATOR("collaborator");

    private String code;

    Role(String code) {
      this.code = code;
    }

    public String getCode() {
      return code;
    }

    public static Role fromCode(String code) {
      return Arrays
        .stream(values())
        .filter(s -> s.code.equals(code))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
    }
  }

  @Converter
  public static class RoleConverter implements AttributeConverter<Role, String> {
    @Override
    public String convertToDatabaseColumn(Role role) {
      return role.getCode();
    }

    @Override
    public Role convertToEntityAttribute(String code) {
      return Role.fromCode(code);
    }
  }
}
