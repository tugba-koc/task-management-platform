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
import static dev.tugba.taskapp.auth.config.constants.Permission.PORTAL_CREATE;
import static dev.tugba.taskapp.auth.config.constants.Permission.PORTAL_DELETE;
import static dev.tugba.taskapp.auth.config.constants.Permission.PORTAL_READ;
import static dev.tugba.taskapp.auth.config.constants.Permission.PORTAL_UPDATE;

@RequiredArgsConstructor
public enum Role {

  USER(Collections.emptySet()),
  ADMIN(
          Set.of(
                  ADMIN_READ,
                  ADMIN_UPDATE,
                  ADMIN_DELETE,
                  ADMIN_CREATE,
                  PORTAL_READ,
                  PORTAL_UPDATE,
                  PORTAL_DELETE,
                  PORTAL_CREATE
          )
  ),
  PORTAL(
          Set.of(
                  PORTAL_READ,
                  PORTAL_UPDATE,
                  PORTAL_DELETE,
                  PORTAL_CREATE
          )
  );

  @Getter
  private final Set<Permission> permissions;

  public List<SimpleGrantedAuthority> getAuthorities() {
    var authorities = getPermissions()
            .stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
            .collect(Collectors.toList());
    authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    return authorities;
  }
}