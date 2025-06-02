package com.spacecodee.spring_security_music_p.persisance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "permission", schema = "public", catalog = "music_project")
public class PermissionEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "permission_id", nullable = false)
    private int permissionId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "role_id", nullable = false)
    private RoleEntity roleEntity;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = OperationEntity.class)
    @JoinColumn(name = "operation_id", referencedColumnName = "operation_id", nullable = false)
    private OperationEntity operationEntity;


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        PermissionEntity that = (PermissionEntity) object;
        return permissionId == that.permissionId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(permissionId);
    }
}
