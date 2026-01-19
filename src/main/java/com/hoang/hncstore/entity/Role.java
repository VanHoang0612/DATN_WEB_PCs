package com.hoang.hncstore.entity;

import com.hoang.hncstore.enums.RoleType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    RoleType roleName;

    String description;

    @ManyToMany(mappedBy = "roles")
    Set<User> users = new HashSet<>();
}
