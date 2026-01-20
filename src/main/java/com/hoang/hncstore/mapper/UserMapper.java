package com.hoang.hncstore.mapper;

import com.hoang.hncstore.dto.user.UpdateUserRequest;
import com.hoang.hncstore.dto.user.UserResponse;
import com.hoang.hncstore.entity.Role;
import com.hoang.hncstore.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "roles", source = "roles")
    UserResponse toUserResponse(User user);

    default String mapRoleToString(Role role) {
        return role.getRoleName()
                .name();
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromRequest(UpdateUserRequest request, @MappingTarget User user);

}
