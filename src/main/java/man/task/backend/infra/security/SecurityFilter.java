package man.task.backend.infra.security;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import man.task.backend.domain.user.User;
import man.task.backend.domain.user.UserRepository;

@Component
public class SecurityFilter extends OncePerRequestFilter {
  @Autowired
  TokenService tokenService;

  @Autowired
  UserRepository userRepository;

  @Override
  protected void doFilterInternal(
    HttpServletRequest request,
    HttpServletResponse response,
    FilterChain filterChain
  ) throws ServletException, IOException {
    Optional<String> mayEmail = recoverToken(request)
      .flatMap(token -> tokenService.validateToken(token));

    mayEmail.ifPresent(email -> {
      User user = userRepository
        .findByEmail(email)
        .orElseThrow(() -> new RuntimeException("User not found"));

      List<SimpleGrantedAuthority> authorities = Collections
        .singletonList(new SimpleGrantedAuthority("USER"));

      UsernamePasswordAuthenticationToken authentication =
        new UsernamePasswordAuthenticationToken(user, null, authorities);

      SecurityContextHolder.getContext().setAuthentication(authentication);
    });

    filterChain.doFilter(request, response);
  }

  private Optional<String> recoverToken(HttpServletRequest request) {
    String authHeader = request.getHeader("Authorization");
    if (authHeader == null) return Optional.empty();
    return Optional.of(authHeader.replace("Bearer ", ""));
  }
}
