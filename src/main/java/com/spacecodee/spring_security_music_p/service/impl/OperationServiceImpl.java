package com.spacecodee.spring_security_music_p.service.impl;

import com.spacecodee.spring_security_music_p.data.dto.security.UDOperationDTO;
import com.spacecodee.spring_security_music_p.exceptions.ExceptionShortComponent;
import com.spacecodee.spring_security_music_p.mapper.dto.OperationDTOMapper;
import com.spacecodee.spring_security_music_p.persisance.entity.OperationEntity;
import com.spacecodee.spring_security_music_p.persisance.repository.OperationRepository;
import com.spacecodee.spring_security_music_p.service.OperationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationServiceImpl implements OperationService {

    private final OperationRepository operationRepository;
    private final OperationDTOMapper operationDTOMapper;

    public OperationServiceImpl(OperationRepository operationRepository,
                                OperationDTOMapper operationDTOMapper) {
        this.operationRepository = operationRepository;
        this.operationDTOMapper = operationDTOMapper;
    }

    @Override
    public List<UDOperationDTO> findByPublicAccess() {
        List<OperationEntity> list = this.operationRepository.findByPublicAccess();
        return list.stream()
                .map(this.operationDTOMapper::toDto)
                .toList();
    }
}
