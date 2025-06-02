package com.spacecodee.spring_security_music_p.persisance.entity;

import com.spacecodee.spring_security_music_p.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "role", schema = "public", catalog = "music_project")
public class RoleEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "role_id", nullable = false)
    private int roleId;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", nullable = false, length = 50)
    private RoleEnum roleName;

    @OneToMany(mappedBy = "roleEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = PermissionEntity.class)
    @ToString.Exclude
    private Set<PermissionEntity> permissions;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        RoleEntity that = (RoleEntity) object;
        return roleId == that.roleId && Objects.equals(roleName, that.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, roleName);
    }
}
