package man.task.backend.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
  @Autowired
  private UserService service;

  @PostMapping
  public ResponseEntity<User> create(@RequestBody UserRequestDTO body) {
    User user = service.create(body);
    return ResponseEntity.ok(user);
  }
}
