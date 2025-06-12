package com.spacecodee.spring_security_music_p.service.impl;

import com.spacecodee.spring_security_music_p.data.dto.security.UDOperationDTO;
import com.spacecodee.spring_security_music_p.exceptions.ExceptionShortComponent;
import com.spacecodee.spring_security_music_p.mapper.dto.OperationDTOMapper;
import com.spacecodee.spring_security_music_p.persisance.entity.OperationEntity;
import com.spacecodee.spring_security_music_p.persisance.repository.OperationRepository;
import com.spacecodee.spring_security_music_p.service.OperationService;

import java.util.List;

public class OperationServiceImpl implements OperationService {

    private final OperationRepository operationRepository;
    private final OperationDTOMapper operationDTOMapper;
    private final ExceptionShortComponent exceptionShortComponent;

    public OperationServiceImpl(OperationRepository operationRepository,
                                OperationDTOMapper operationDTOMapper,
                                ExceptionShortComponent exceptionShortComponent) {
        this.operationRepository = operationRepository;
        this.operationDTOMapper = operationDTOMapper;
        this.exceptionShortComponent = exceptionShortComponent;
    }

    @Override
    public List<UDOperationDTO> findByPublicAccess() {
        List<OperationEntity> list = this.operationRepository.findByPublicAccess();
        return list.stream()
                .map(this.operationDTOMapper::toDto)
                .toList();
    }
}
