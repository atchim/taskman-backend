package man.task.backend.domain.project.member;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, UUID> {}
