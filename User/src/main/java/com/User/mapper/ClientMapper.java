package com.User.mapper;


import com.User.repository.RoleRepository;
import com.User.domain.Client;
import com.User.dto.ClientCreateDto;
import com.User.dto.ClientDto;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    private RoleRepository roleRepository;

    public ClientMapper(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public ClientDto clientToClientDto(Client client) {
        ClientDto clientDto = new ClientDto();
        clientDto.setId(client.getId());
        clientDto.setRole(client.getRole());
        clientDto.setEmail(client.getEmail());
        clientDto.setFirstName(client.getFirstName());
        clientDto.setLastName(client.getLastName());
        clientDto.setPassword(client.getPassword());
        clientDto.setUsername(client.getUsername());
        clientDto.setDatumRodjenja(client.getDatumRodjenja());
        clientDto.setIsBanovan(client.isIsBanovan());
        clientDto.setBrojZakazanihTreninga(client.getBrojZakazanihTreninga());
        clientDto.setBrojClanskeKartice(client.getBrojClanskeKartice());
        clientDto.setManager(client.getManager());
        return clientDto;
    }
    public Client clientCreateDtoToClient(ClientCreateDto clientCreateDto) {
        Client client = new Client();
        client.setEmail(clientCreateDto.getEmail());
        client.setFirstName(clientCreateDto.getFirstName());
        client.setLastName(clientCreateDto.getLastName());
        client.setUsername(clientCreateDto.getUsername());
        client.setPassword(clientCreateDto.getPassword());
        client.setDatumRodjenja(clientCreateDto.getDatumRodjenja());
        client.setRole(roleRepository.findRoleByName("ROLE_CLIENT").orElseThrow(RuntimeException::new));
        client.setIsBanovan(clientCreateDto.getIsBanovan());
        client.setBrojClanskeKartice(clientCreateDto.getBrojClanskeKartice());
//        client.setBrojZakazanihTreninga(clientCreateDto.getBrojZakazanihTreninga());
//        client.setManager(clientCreateDto.getManager());

        return client;
    }
}
