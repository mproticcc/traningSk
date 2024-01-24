package com.User.service;

import com.User.dto.AdminDto;
import com.User.dto.TokenRequestDto;
import com.User.dto.TokenResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminService {

     void banClient(Long id);

     void unBanClient(Long id);

     void banManager(Long id);

     void unBanManager(Long id);

     Page<AdminDto> findAll(Pageable pageable);

     TokenResponseDto login(TokenRequestDto tokenRequestDto);
}
