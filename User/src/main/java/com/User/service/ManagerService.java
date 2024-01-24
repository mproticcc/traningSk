package com.User.service;

import com.User.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ManagerService {
    Page<ManagerDto> findAll(Pageable pageable);

    ManagerDto add(ManagerCreateDto managerCreateDto);

    TokenResponseDto login(TokenRequestDto tokenRequestDto);

    ManagerDto findById(Long id);

    void updateProfile(Long id, ManagerUdateDto updatedProfile);

}
