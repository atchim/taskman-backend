package man.task.backend.domain.user;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  @Autowired
  private UserRepository repository;

  public User create(UserRequestDTO data) {
    User user = new User();
    user.setName(data.name());
    user.setEmail(data.email());
    user.setPassword(data.password());
    repository.save(user);
    return user;
  }
}
