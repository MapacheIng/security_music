package com.spacecodee.spring_security_music_p.service.impl;

import com.spacecodee.spring_security_music_p.data.dto.security.UDUserSDTO;
import com.spacecodee.spring_security_music_p.data.vo.auth.SaveCustomerSVO;
import com.spacecodee.spring_security_music_p.data.vo.auth.SaveUserSVO;
import com.spacecodee.spring_security_music_p.exceptions.ExceptionShortComponent;
import com.spacecodee.spring_security_music_p.exceptions.InvalidPasswordException;
import com.spacecodee.spring_security_music_p.exceptions.ObjectNotFoundException;
import com.spacecodee.spring_security_music_p.mapper.dto.UserSDTOMapper;
import com.spacecodee.spring_security_music_p.mapper.vo.UserSVOMapper;
import com.spacecodee.spring_security_music_p.persisance.entity.UserSEntity;
import com.spacecodee.spring_security_music_p.persisance.repository.UserSRepository;
import com.spacecodee.spring_security_music_p.service.RoleService;
import com.spacecodee.spring_security_music_p.service.UserSService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class UserSServiceImpl implements UserSService {

    private final UserSRepository userSRepository;
    private final UserSDTOMapper userSDTOMapper;
    private final UserSVOMapper userSVOMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final ExceptionShortComponent exceptionShortComponent;

    public UserSServiceImpl(UserSRepository userSRepository, UserSDTOMapper userSDTOMapper,
                            UserSVOMapper userSVOMapper, PasswordEncoder passwordEncoder,
                            RoleService roleService, ExceptionShortComponent exceptionShortComponent) {
        this.userSRepository = userSRepository;
        this.userSDTOMapper = userSDTOMapper;
        this.userSVOMapper = userSVOMapper;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.exceptionShortComponent = exceptionShortComponent;
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public UDUserSDTO findOneByUsername(String username) {
        Optional<UserSEntity> user = this.userSRepository.findByUserSUsername(username);
        return user.map(this.userSDTOMapper::entityToDto)
                .orElseThrow(() -> new ObjectNotFoundException("User isn't found with Username " + username));
    }

    @Override
    public void registerOneCustomer(SaveCustomerSVO newCustomer, String lang) {
        this.validatePassword(newCustomer.getPassword(), newCustomer.getRepeatedPassword());
        this.alreadyExistsUserOrCustomer(newCustomer.getUsername(), "customer.exists", lang);
        newCustomer.setPassword(this.passwordEncoder.encode(newCustomer.getPassword()));
        newCustomer.setRoleVO(this.roleService.searchDefaultRole());
        this.userSRepository.save(this.userSVOMapper.toEntity(newCustomer));
    }

    @Override
    public void registerOneUser(SaveUserSVO newUser, String lang) {
        this.validatePassword(newUser.getPassword(), newUser.getRepeatedPassword());
        this.alreadyExistsUserOrCustomer(newUser.getUsername(), "user.exists", lang);
        boolean roleExistsById = this.roleService.existsById(newUser.getRoleId());
        if (!roleExistsById) {
            throw this.exceptionShortComponent.notFoundException("role.found.by.id.not", lang);
        }
        newUser.setPassword(this.passwordEncoder.encode(newUser.getPassword()));
        this.userSRepository.save(this.userSVOMapper.voToEntity(newUser));
    }

    @Override
    public void updateOneUser(SaveUserSVO userVO, String lang) {
        this.alreadyExistsUserOrCustomer(userVO.getUsername(), "user.exists", lang);
        boolean roleExistsById = this.roleService.existsById(userVO.getRoleId());
        if (!roleExistsById) {
            throw this.exceptionShortComponent.notFoundException("role.found.by.id.not", lang);
        }
        this.userSRepository.save(this.userSVOMapper.voToEntity(userVO));
    }

    private void alreadyExistsUserOrCustomer(String username, String message, String lang){
        boolean alreadyExists = this.userSRepository.existsByUserSUsernameIgnoreCase(username);
        if (alreadyExists) {
            throw this.exceptionShortComponent.cannotSaveUpdateException(message, lang);
        }
    }

    private void validatePassword(String password, String repeatPassword) {
        if (!StringUtils.hasText(password) || !StringUtils.hasText(repeatPassword)) {
            throw new InvalidPasswordException("Password and Repeat Password are required.");
        }
        if (!password.equals(repeatPassword)) {
            throw new InvalidPasswordException("Password and Repeat Password do not match.");
        }
    }
}
