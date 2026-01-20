package com.hoang.hncstore.configuration;

import com.hoang.hncstore.entity.Role;
import com.hoang.hncstore.enums.RoleType;
import com.hoang.hncstore.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public DataInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        for (RoleType roleType : RoleType.values()) {
            if (!roleRepository.existsByRoleName(roleType)) {
                Role role = new Role();
                role.setRoleName(roleType);
                roleRepository.save(role);
            }
        }
    }
}
