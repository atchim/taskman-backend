package man.task.backend.controllers;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import man.task.backend.domain.project.Project;
import man.task.backend.domain.project.ProjectRepository;
import man.task.backend.domain.project.member.ProjectMember;
import man.task.backend.domain.project.member.ProjectMemberRepository;
import man.task.backend.domain.project.member.ProjectMember.Role;
import man.task.backend.domain.user.User;
import man.task.backend.domain.user.UserRepository;
import man.task.backend.dto.DeleteProjectRequestDTO;
import man.task.backend.dto.NewProjectRequestDTO;

// TODO: Find a better way to fetch authenticated user to avoid code
// duplication.
@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {
  private final ProjectRepository projectRepository;
  private final ProjectMemberRepository projectMemberRepository;

  @GetMapping
  public ResponseEntity<?> getProjects() {
    Authentication auth = SecurityContextHolder
      .getContext()
      .getAuthentication();

    if (auth == null) return ResponseEntity.badRequest().build();

    Object mayUser = auth.getPrincipal();
    if (!(mayUser instanceof User)) return ResponseEntity.badRequest().build();
    User user = (User) mayUser;

    return ResponseEntity.ok().body(projectRepository.findByUser(user));
  }

  @PostMapping
  public ResponseEntity<?> newProject(
    @Valid @RequestBody NewProjectRequestDTO body
  ) {
    Authentication auth = SecurityContextHolder
      .getContext()
      .getAuthentication();

    if (auth == null) return ResponseEntity.badRequest().build();

    Object mayUser = auth.getPrincipal();
    if (!(mayUser instanceof User)) return ResponseEntity.badRequest().build();
    User user = (User) mayUser;

    Project project = new Project();
    project.setName(body.name());
    project.setDescription(body.description());
    project.setUser(user);
    project = projectRepository.save(project);

    ProjectMember projectMember = new ProjectMember();
    projectMember.setProject(project);
    projectMember.setUser(user);
    projectMember.setRole(Role.OWNER);
    projectMemberRepository.save(projectMember);

    return ResponseEntity
      .created(URI.create("/project/" + project.getId()))
      .body(project);
  }

  @DeleteMapping
  public ResponseEntity<?> delete(
    @Valid @RequestBody DeleteProjectRequestDTO body
  ) {
    Authentication auth = SecurityContextHolder
      .getContext()
      .getAuthentication();

    if (auth == null) return ResponseEntity.badRequest().build();

    Object mayUser = auth.getPrincipal();
    if (!(mayUser instanceof User)) return ResponseEntity.badRequest().build();
    User user = (User) mayUser;

    UUID projectId = UUID.fromString(body.id());

    Project project = projectRepository
      .findById(projectId)
      .orElseThrow(() -> new RuntimeException("Project not found"));

    if (project.getUser().getId().compareTo(user.getId()) != 0) {
      return ResponseEntity.badRequest().build();
    }

    projectRepository.deleteById(projectId);
    return ResponseEntity.noContent().build();
  }
}
