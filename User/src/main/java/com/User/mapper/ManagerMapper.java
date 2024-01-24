package com.User.mapper;

import com.User.domain.Client;
import com.User.repository.ClientRepository;
import com.User.repository.RoleRepository;
import com.User.domain.Manager;
import com.User.dto.ManagerCreateDto;
import com.User.dto.ManagerDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ManagerMapper {

    private RoleRepository roleRepository;

    private ClientRepository clientRepository;

    public ManagerMapper(RoleRepository roleRepository, ClientRepository clientRepository) {
        this.roleRepository = roleRepository;
        this.clientRepository = clientRepository;
    }

    public ManagerDto managerToManagerDto(Manager manager) {
        ManagerDto managerDto = new ManagerDto();
        managerDto.setId(manager.getId());
        managerDto.setEmail(manager.getEmail());
        managerDto.setFirstName(manager.getFirstName());
        managerDto.setPassword(manager.getPassword());
        managerDto.setRole(manager.getRole());
        managerDto.setLastName(manager.getLastName());
        managerDto.setUsername(manager.getUsername());
        managerDto.setDatumRodjenja(manager.getDatumRodjenja());
        managerDto.setBanovan(manager.getIsBanovan());
        managerDto.setDatumZaposljavanja(manager.getDatumZaposljavanja());
        managerDto.setNazivFisSale(manager.getNazivFisSale());
//        List<Client> client = new ArrayList<>();
//        for (Client client1 : manager.getClients()) {
//            client.add(client1);
//        }
//        managerDto.setClients(manager.getClients());
        return managerDto;
    }

    public Manager managerCreateDtoToManager(ManagerCreateDto managerCreateDto) {
        Manager manager = new Manager();
        manager.setEmail(managerCreateDto.getEmail());
        manager.setFirstName(managerCreateDto.getFirstName());
        manager.setLastName(managerCreateDto.getLastName());
        manager.setUsername(managerCreateDto.getUsername());
        manager.setPassword(managerCreateDto.getPassword());
        manager.setDatumRodjenja(managerCreateDto.getDatumRodjenja());
        manager.setRole(roleRepository.findRoleByName("ROLE_MANAGER").get());
        manager.setIsBanovan(true);
        return manager;
    }
}