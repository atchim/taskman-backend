package man.task.backend.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import man.task.backend.entities.ProjectMember;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, UUID> {}
