package org.example.techparanoiaserver.repository.user;

import org.example.techparanoiaserver.entity.user.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByName(String name);
}
