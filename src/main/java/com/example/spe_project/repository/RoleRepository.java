package com.example.spe_project.repository;

import com.example.spe_project.model.RoleName;
import com.example.spe_project.model.Role;
import com.example.spe_project.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}