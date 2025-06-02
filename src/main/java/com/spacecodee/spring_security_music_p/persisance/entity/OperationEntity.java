package com.spacecodee.spring_security_music_p.persisance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "operation", schema = "public", catalog = "music_project")
public class OperationEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "operation_id", nullable = false)
    private int operationId;

    @Column(name = "operation_tag", nullable = false, length = 100)
    private String operationTag;

    @Column(name = "operation_path", nullable = false, length = 100)
    private String operationPath;

    @Column(name = "operation_http_method", nullable = false, length = 100)
    private String operationHttpMethod;

    @Column(name = "operation_permit_all", nullable = false)
    private boolean operationPermitAll;

    @ManyToOne
    @JoinColumn(name = "module_id", referencedColumnName = "module_id", nullable = false)
    private ModuleEntity moduleEntity;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        OperationEntity that = (OperationEntity) object;
        return operationId == that.operationId
                && operationPermitAll == that.operationPermitAll
                && Objects.equals(operationTag, that.operationTag)
                && Objects.equals(operationPath, that.operationPath)
                && Objects.equals(operationHttpMethod, that.operationHttpMethod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operationId, operationTag, operationPath, operationHttpMethod, operationPermitAll);
    }
}
