package com.spacecodee.spring_security_music_p.persisance.repository;

import com.spacecodee.spring_security_music_p.persisance.entity.ModuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModuleRepository extends JpaRepository<ModuleEntity, Integer> {
}
