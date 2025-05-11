package man.task.backend.domain.project;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import man.task.backend.domain.user.User;

public interface ProjectRepository extends JpaRepository<Project, UUID> {
  List<Project> findByUser(User user);
}
