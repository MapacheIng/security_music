package com.spacecodee.spring_security_music_p.service;


import com.spacecodee.spring_security_music_p.data.dto.security.UDOperationDTO;

import java.util.List;

public interface OperationService {

    List<UDOperationDTO> findByPublicAccess();




}
