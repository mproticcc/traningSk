package com.User.service;

import com.User.domain.Client;
import com.User.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientService {
    Page<ClientDto> findAll(Pageable pageable);
    ClientDto findById(Long id);

    void activate(Long id);

    ClientDto add(ClientCreateDto clientCreateDto);

    String returnAToken(ClientDto clientDto);

    TokenResponseDto login(TokenRequestDto tokenRequestDto);

    void updateProfile(Long id, ClientUpdateDto updatedProfile);

    ClientDto addReservation(Long id);

}
