package br.com.backend.challenge.commons;

import java.util.List;

public class RoleChecker {

    private final List<String> roles = List.of("Admin", "Member", "External");

    public boolean isValidRole(String role) {

        return roles.stream()
            .filter(
                r -> !role.isEmpty() && !role.isBlank()
            )
            .anyMatch(role::equalsIgnoreCase);
        //
    }
}