package com.spacecodee.spring_security_music_p.persisance.repository;

import com.spacecodee.spring_security_music_p.persisance.entity.JwtTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JwtTokenRepository extends JpaRepository<JwtTokenEntity, Integer> {

    Optional<JwtTokenEntity> findByJwtTokenToken(String jwt);

}
