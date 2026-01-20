package com.hoang.hncstore.service;

import com.hoang.hncstore.entity.Role;
import com.hoang.hncstore.enums.ErrorCode;
import com.hoang.hncstore.enums.RoleType;
import com.hoang.hncstore.exception.AppException;
import com.hoang.hncstore.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Set<Role> getRolesByNameIn(Set<RoleType> roleNames) {
        Set<Role> roles = roleRepository.findByRoleNameIn(roleNames);
        if (roles.isEmpty()) {
            throw new AppException(ErrorCode.ROLE_NOT_FOUND);
        }
        return roles;
    }
}
