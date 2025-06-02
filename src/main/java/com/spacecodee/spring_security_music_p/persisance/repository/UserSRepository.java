package com.spacecodee.spring_security_music_p.persisance.repository;

import com.spacecodee.spring_security_music_p.persisance.entity.UserSEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserSRepository extends JpaRepository<UserSEntity, Integer> {

    Optional<UserSEntity> findByUserSUsername(String username);

    boolean existsByUserSUsernameIgnoreCase(String username);

}
