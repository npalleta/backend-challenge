package br.com.backend.challenge.commons;

import java.util.List;

public class RoleChecker {

    private final List<String> roles = List.of("Admin", "Member", "External");

    public boolean isValidRole(String role) {

        /* if (checkRole) return true;
        else throw new IllegalArgumentException("Role must be one of: Admin, Member, External"); */

        return roles.stream()
            .filter(
                roles -> !role.isEmpty() && !role.isBlank()
            )
            .anyMatch(role::equalsIgnoreCase);
        //
    }
}