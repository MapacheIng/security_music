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
@Table(name = "module", schema = "public", catalog = "music_project")
public class ModuleEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "module_id", nullable = false)
    private int moduleId;

    @Column(name = "module_name", nullable = false, length = 50)
    private String moduleName;

    @Column(name = "module_base_path", nullable = false, length = 100)
    private String moduleBasePath;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ModuleEntity that = (ModuleEntity) object;
        return moduleId == that.moduleId
                && Objects.equals(moduleName, that.moduleName)
                && Objects.equals(moduleBasePath, that.moduleBasePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(moduleId, moduleName, moduleBasePath);
    }
}
