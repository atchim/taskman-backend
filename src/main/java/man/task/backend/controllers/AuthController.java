package man.task.backend.controllers;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import man.task.backend.domain.user.User;
import man.task.backend.domain.user.UserRepository;
import man.task.backend.dto.SignInRequestDTO;
import man.task.backend.dto.SignInResponseDTO;
import man.task.backend.dto.SignUpRequestDTO;
import man.task.backend.infra.security.TokenService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final TokenService tokenService;

  @PostMapping("sign-in")
  public ResponseEntity<?> signIn(@Valid @RequestBody SignInRequestDTO body) {
    User user = repository
      .findByEmail(body.email())
      .orElseThrow(() -> new RuntimeException("User not found"));

    if (passwordEncoder.matches(body.password(), user.getPassword())) {
      String token = tokenService.generateToken(user);
      return ResponseEntity.ok(new SignInResponseDTO(user.getName(), token));
    }

    return ResponseEntity.badRequest().build();
  }

  @PostMapping("sign-up")
  public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequestDTO body) {
    Optional<User> mayUser = repository.findByEmail(body.email());

    if (mayUser.isEmpty()) {
      User user = new User();
      user.setPassword(passwordEncoder.encode(body.password()));
      user.setEmail(body.email());
      user.setName(body.name());
      repository.save(user);
      String token = tokenService.generateToken(user);
      return ResponseEntity.ok(new SignInResponseDTO(user.getName(), token));
    }

    return ResponseEntity.badRequest().build();
  }
}
