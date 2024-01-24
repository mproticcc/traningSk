package com.User.service.impl;

import com.User.domain.Client;
import com.User.domain.Manager;
import com.User.dto.*;
import com.User.exception.NotFoundException;
import com.User.mapper.ManagerMapper;
import com.User.repository.ManagerRepository;
import com.User.security.service.TokenService;
import com.User.service.ManagerService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImpl implements ManagerService {

    private TokenService tokenService;
    private ManagerRepository managerRepository;
    private ManagerMapper managerMapper;

    public ManagerServiceImpl(TokenService tokenService, ManagerRepository managerRepository, ManagerMapper managerMapper) {
        this.tokenService = tokenService;
        this.managerRepository = managerRepository;
        this.managerMapper = managerMapper;
    }

    @Override
    public Page<ManagerDto> findAll(Pageable pageable) {
        return managerRepository.findAll(pageable).map(managerMapper::managerToManagerDto);
    }

    @Override
    public ManagerDto add(ManagerCreateDto managerCreateDto) {
        Manager manager =  managerMapper.managerCreateDtoToManager(managerCreateDto);
        managerRepository.save(manager);
        return  managerMapper.managerToManagerDto(manager);
    }

    @Override
    public TokenResponseDto login(TokenRequestDto tokenRequestDto) {
        Manager manager = managerRepository
                .findUserByUsernameAndPassword(tokenRequestDto.getUsername(), tokenRequestDto.getPassword())
                .orElseThrow(() -> new NotFoundException(String
                        .format("Manager with username: %s and password: %s not found.", tokenRequestDto.getUsername(),
                                tokenRequestDto.getPassword())));
        //Create token payload
        Claims claims = Jwts.claims();
        claims.put("id", manager.getId());
        claims.put("role", manager.getRole().getName());
        //Generate token
        return new TokenResponseDto(tokenService.generate(claims));
    }

    @Override
    public ManagerDto findById(Long id) {
        Manager  manager = managerRepository.findById(id).orElseThrow(RuntimeException::new);

        ManagerDto managerDto = managerMapper.managerToManagerDto(manager);

        return managerDto;
    }

    @Override
    public void updateProfile(Long id, ManagerUdateDto updatedProfile) {
        Manager manager = managerRepository.findById(id).orElseThrow(RuntimeException::new);

        manager.setUsername(updatedProfile.getUsername());
        manager.setPassword(updatedProfile.getPassword());
        manager.setEmail(updatedProfile.getEmail());
        manager.setFirstName(updatedProfile.getFirstName());
        manager.setLastName(updatedProfile.getLastName());


        managerRepository.save(manager);
    }
}
