package com.spacecodee.spring_security_music_p.persisance.repository;

import com.spacecodee.spring_security_music_p.persisance.entity.OperationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OperationRepository extends JpaRepository<OperationEntity, Integer> {

    @Query("SELECT o FROM OperationEntity o WHERE o.operationPermitAll = true")
    List<OperationEntity> findByPublicAccess();

}
