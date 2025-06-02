package com.spacecodee.spring_security_music_p.persisance.repository;

import com.spacecodee.spring_security_music_p.persisance.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<PermissionEntity, Integer> {

}
