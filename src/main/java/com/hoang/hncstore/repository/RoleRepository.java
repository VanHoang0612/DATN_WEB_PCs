package com.hoang.hncstore.repository;

import com.hoang.hncstore.entity.Role;
import com.hoang.hncstore.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Set<Role> findByRoleNameIn(Set<RoleType> roleNames);

    Optional<Role> findByRoleName(RoleType roleName);

    boolean existsByRoleName(RoleType roleNames);
}
