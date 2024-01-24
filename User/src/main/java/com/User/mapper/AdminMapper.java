package com.User.mapper;

import com.User.repository.RoleRepository;
import com.User.domain.Admin;
import com.User.dto.AdminCreateDto;
import com.User.dto.AdminDto;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {
    private RoleRepository roleRepository;

    public AdminMapper(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public AdminDto adminToAdminDto(Admin admin) {
        AdminDto adminDto = new AdminDto();
        adminDto.setId(admin.getId());
        adminDto.setEmail(admin.getEmail());
        adminDto.setFirstName(admin.getFirstName());
        adminDto.setLastName(admin.getLastName());
        adminDto.setUsername(admin.getUsername());
        adminDto.setDatumRodjenja(admin.getDatumRodjenja());
        adminDto.setPassword(admin.getPassword());
        adminDto.setRole(admin.getRole());
        return adminDto;
    }
    public Admin adminCreateDtoToAdmin(AdminCreateDto adminCreateDto) {
        Admin admin = new Admin();
        admin.setEmail(adminCreateDto.getEmail());
        admin.setFirstName(adminCreateDto.getFirstName());
        admin.setLastName(adminCreateDto.getLastName());
        admin.setUsername(adminCreateDto.getUsername());
        admin.setPassword(adminCreateDto.getPassword());
        admin.setDatumRodjenja(adminCreateDto.getDatumRodjenja());
        admin.setPassword(adminCreateDto.getPassword());
        admin.setRole(roleRepository.findRoleByName("ROLE_ADMIN").get());
        return admin;
    }
}
