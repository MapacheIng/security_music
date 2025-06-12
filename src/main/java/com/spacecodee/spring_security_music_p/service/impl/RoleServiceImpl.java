package com.spacecodee.spring_security_music_p.service.impl;

import com.spacecodee.spring_security_music_p.data.dto.security.UDRoleDTO;
import com.spacecodee.spring_security_music_p.data.vo.RoleVO;
import com.spacecodee.spring_security_music_p.enums.RoleEnum;
import com.spacecodee.spring_security_music_p.exceptions.ExceptionShortComponent;
import com.spacecodee.spring_security_music_p.exceptions.ObjectNotFoundException;
import com.spacecodee.spring_security_music_p.mapper.dto.RoleDTOMapper;
import com.spacecodee.spring_security_music_p.mapper.vo.RoleVOMapper;
import com.spacecodee.spring_security_music_p.persisance.repository.RoleRepository;
import com.spacecodee.spring_security_music_p.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private static final String ROLE_FOUND_BY_NAME_NOT = "role.found.by.name.not";
    private final RoleRepository roleRepository;
    private final RoleDTOMapper roleDTOMapper;
    private final RoleVOMapper roleVOMapper;
    private final ExceptionShortComponent exceptionShortComponent;
    @Value("${security.default.role}")
    private String defaultRoleName;

    public RoleServiceImpl(RoleRepository roleRepository, RoleDTOMapper roleDTOMapper,
                            RoleVOMapper roleVOMapper, ExceptionShortComponent exceptionShortComponent) {
        this.roleRepository = roleRepository;
        this.roleDTOMapper = roleDTOMapper;
        this.roleVOMapper = roleVOMapper;
        this.exceptionShortComponent = exceptionShortComponent;
    }

    @Override
    public UDRoleDTO findDefaultRole(String lang) {
        RoleEnum toEnum = RoleEnum.valueOf(defaultRoleName);
        return this.roleRepository.findByRoleName(toEnum)
                .map(this.roleDTOMapper::entityToUdDto)
                .orElseThrow(() -> this.exceptionShortComponent.notFoundException(RoleServiceImpl.ROLE_FOUND_BY_NAME_NOT, lang));
    }

    @Override
    public RoleVO searchDefaultRole(String lang) {
        RoleEnum toEnum = RoleEnum.valueOf(defaultRoleName);
        return this.roleRepository.findByRoleName(toEnum)
                .map(this.roleVOMapper::roleEntityToVO)
                .orElseThrow(() -> this.exceptionShortComponent.notFoundException(RoleServiceImpl.ROLE_FOUND_BY_NAME_NOT, lang));
    }

    @Override
    public RoleVO searchDefaultRole() {
        RoleEnum toEnum = RoleEnum.valueOf(defaultRoleName);
        return this.roleRepository.findByRoleName(toEnum)
                .map(this.roleVOMapper::roleEntityToVO)
                .orElseThrow(() -> new ObjectNotFoundException("Role isn't found with Name: " + toEnum.name()));
    }

    @Override
    public RoleEnum getRoleEnum(String roleName, String lang) {
        RoleEnum toEnum = RoleEnum.valueOf(defaultRoleName);
        return this.roleRepository.findByRoleName(toEnum)
                .map(roleEntity ->  RoleEnum.valueOf(roleEntity.getRoleName().name()))
                .orElseThrow(() -> new ObjectNotFoundException("Role isn't found with Name: " + toEnum.name()));
    }

    @Override
    public boolean existsById(int roleId) {
        return this.roleRepository.existsById(roleId);
    }
}
