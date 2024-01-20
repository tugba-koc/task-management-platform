package dev.tugba.taskapp.auth.config.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static dev.tugba.taskapp.auth.config.constants.Permission.ADMIN_CREATE;
import static dev.tugba.taskapp.auth.config.constants.Permission.ADMIN_DELETE;
import static dev.tugba.taskapp.auth.config.constants.Permission.ADMIN_READ;
import static dev.tugba.taskapp.auth.config.constants.Permission.ADMIN_UPDATE;
import static dev.tugba.taskapp.auth.config.constants.Permission.VISITOR_CREATE;
import static dev.tugba.taskapp.auth.config.constants.Permission.VISITOR_DELETE;
import static dev.tugba.taskapp.auth.config.constants.Permission.VISITOR_READ;
import static dev.tugba.taskapp.auth.config.constants.Permission.VISITOR_UPDATE;

@RequiredArgsConstructor
public enum Role {

  USER(Collections.emptySet()),
  ADMIN(
          Set.of(
                  ADMIN_READ,
                  ADMIN_UPDATE,
                  ADMIN_DELETE,
                  ADMIN_CREATE
          )
  ),
  VISITOR(
          Set.of(
                  VISITOR_READ,
                  VISITOR_UPDATE,
                  VISITOR_DELETE,
                  VISITOR_CREATE
          )
  );

  @Getter
  private final Set<Permission> permissions;

  public List<SimpleGrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = getPermissions()
            .stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
            .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
  }
}