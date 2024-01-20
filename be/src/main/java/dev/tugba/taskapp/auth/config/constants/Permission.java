package dev.tugba.taskapp.auth.config.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    VISITOR_READ("visitor:read"),
    VISITOR_UPDATE("visitor:update"),
    VISITOR_CREATE("visitor:create"),
    VISITOR_DELETE("visitor:delete");

    @Getter
    private final String permission;
}
