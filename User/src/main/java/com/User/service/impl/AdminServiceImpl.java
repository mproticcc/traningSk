package com.User.service.impl;

import com.User.domain.Admin;
import com.User.domain.Client;
import com.User.domain.Manager;
import com.User.dto.AdminDto;
import com.User.dto.TokenRequestDto;
import com.User.dto.TokenResponseDto;
import com.User.exception.NotFoundException;
import com.User.mapper.AdminMapper;
import com.User.repository.AdminRepository;
import com.User.repository.ClientRepository;
import com.User.repository.ManagerRepository;
import com.User.security.service.TokenService;
import com.User.service.AdminService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service

public class AdminServiceImpl implements AdminService {

    private ClientRepository clientRepository;
    private TokenService tokenService;
    private AdminRepository adminRepository;

    private ManagerRepository managerRepository;

    private AdminMapper adminMapper;


    public AdminServiceImpl(ClientRepository clientRepository, TokenService tokenService, AdminRepository adminRepository, ManagerRepository managerRepository, AdminMapper adminMapper) {
        this.clientRepository = clientRepository;
        this.tokenService = tokenService;
        this.adminRepository = adminRepository;
        this.managerRepository = managerRepository;
        this.adminMapper = adminMapper;
    }

    @Override
    public void banClient(Long userId) {
        Optional<Client> banClient = (Optional<Client>) clientRepository.findById(userId)
                .map(user -> {
                    if (user instanceof Client) {
                        return Optional.of((Client) user);
                    }
                    return Optional.empty();
                })
                .orElse(Optional.empty());
        if (banClient.isPresent()) {
            Client client = banClient.get();
            client.setIsBanovan(true);
            clientRepository.save(client);
        } else {
            throw new NotFoundException("Client with ID: " + userId + " not found.");
        }
    }

    @Override
    public void unBanClient(Long userId) {
        Optional<Client> unBanClient = (Optional<Client>) clientRepository.findById(userId)
                .map(user -> {
                    if (user instanceof Client) {
                        return Optional.of((Client) user);
                    }
                    return Optional.empty();
                })
                .orElse(Optional.empty());

        if (unBanClient.isPresent()) {
            Client client = unBanClient.get();
            client.setIsBanovan(false);
            clientRepository.save(client);
        } else {
            throw new NotFoundException("Client with ID: " + userId + " not found.");
        }

    }

    @Override
    public void banManager(Long id) {
        Optional<Manager> banManager = (Optional<Manager>) managerRepository.findById(id)
                .map(user -> {
                    if (user instanceof Manager) {
                        return Optional.of((Manager) user);
                    }
                    return Optional.empty();
                })
                .orElse(Optional.empty());
        if (banManager.isPresent()) {
            Manager manager = banManager.get();
            manager.setIsBanovan(true);
            managerRepository.save(manager);
        } else {
            throw new NotFoundException("Manager with ID: " + id + " not found.");
        }
    }

    @Override
    public void unBanManager(Long id) {
        Optional<Manager> unBanManager = (Optional<Manager>) managerRepository.findById(id)
                .map(user -> {
                    if (user instanceof Manager) {
                        return Optional.of((Manager) user);
                    }
                    return Optional.empty();
                })
                .orElse(Optional.empty());

        if (unBanManager.isPresent()) {
            Manager manager = unBanManager.get();
            manager.setIsBanovan(false);
            managerRepository.save(manager);
        } else {
            throw new NotFoundException("Client with ID: " + id + " not found.");
        }
    }

    @Override
    public Page<AdminDto> findAll(Pageable pageable) {
        return adminRepository.findAll(pageable)
                .map(entity -> {
                    if (entity instanceof Admin) {
                        return adminMapper.adminToAdminDto(entity);
                    }
                    throw new NotFoundException("Expected Admin entity but found different entity type.");
                });
    }

    @Override
    public TokenResponseDto login(TokenRequestDto tokenRequestDto) {
        Admin admin = adminRepository
                .findUserByUsernameAndPassword(tokenRequestDto.getUsername(), tokenRequestDto.getPassword())
                .orElseThrow(() -> new NotFoundException(String
                        .format("Admin with username: %s and password: %s not found.", tokenRequestDto.getUsername(),
                                tokenRequestDto.getPassword())));

        Claims claims = Jwts.claims();
        claims.put("id", admin.getId());
        claims.put("role", admin.getRole().getName());

        return new TokenResponseDto(tokenService.generate(claims));
    }
}
