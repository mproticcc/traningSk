package com.User.runner;

import com.User.domain.Admin;
import com.User.domain.Client;
import com.User.domain.Manager;
import com.User.domain.Role;
import com.User.repository.AdminRepository;
import com.User.repository.ClientRepository;
import com.User.repository.ManagerRepository;
import com.User.repository.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Profile({"default"})
@Component
@AllArgsConstructor
public class TestDataRunner implements CommandLineRunner {

    private RoleRepository roleRepository;
    private ClientRepository userRepository;
    private AdminRepository adminRepository;

    private ManagerRepository managerRepository;

//    public TestDataRunner(RoleRepository roleRepository, ClientRepository userRepository, AdminRepository adminRepository,
//                          ManagerRepository managerRepository) {
//        this.roleRepository = roleRepository;
//        this.userRepository = userRepository;
//        this.adminRepository=adminRepository;
//        this.managerRepository=managerRepository;
//    }

    @Override
    public void run(String... args) throws Exception {

        Role roleUser = new Role("ROLE_USER", "User role");
        Role roleAdmin = new Role("ROLE_ADMIN", "Admin role");
        Role roleClient = new Role("ROLE_CLIENT","Client role");
        Role roleManager = new Role ("ROLE_MANAGER","Manager role");
        roleRepository.save(roleUser);
        roleRepository.save(roleAdmin);
        roleRepository.save(roleClient);
        roleRepository.save(roleManager);

        Admin admin = new Admin();
        admin.setEmail("admin@gmail.com");
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setRole(roleAdmin);

        adminRepository.save(admin);
        //User statuses
        Manager manager = new Manager();
        manager.setFirstName("Arsenije");
        manager.setEmail("panteon566@gmail.com");
        manager.setRole(roleManager);
        manager.setNazivFisSale("Ahilej");
        manager.setUsername("Rokl123");
        manager.setPassword("arsen123");
        manager.setLastName("Petrovic");
        manager.setDatumZaposljavanja(new Date(122, 0, 8));
        manager.setDatumRodjenja(new Date(102, 5, 21));
        manager.setIsBanovan(false);

        Manager manager1 = new Manager();
        manager1.setFirstName("Milos");
        manager1.setEmail("milos@gmail.com");
        manager1.setRole(roleManager);
        manager1.setNazivFisSale("Ahilej");
        manager1.setUsername("milos123");
        manager1.setPassword("milos123");
        manager1.setLastName("Milosevic");
        manager1.setDatumZaposljavanja(new Date(122, 0, 8));
        manager1.setDatumRodjenja(new Date(102, 5, 21));
        manager1.setIsBanovan(false);

        Client client1 = new Client();
        client1.setFirstName("Petar");
        client1.setLastName("Petrovic");
        client1.setBrojClanskeKartice("987654321");
        client1.setEmail("petar@gmail.com");
        client1.setUsername("petar");
        client1.setRole(roleClient);
        client1.setPassword("petar123");
        client1.setDatumRodjenja(new Date(102, 2, 3));
        manager1.setClients(List.of(client1));
        client1.setManager(manager1);
        client1.setIsBanovan(false);

        Manager manager2 = new Manager();
        manager2.setFirstName("Nikola");
        manager2.setEmail("nikola@gmail.com");
        manager2.setRole(roleManager);
        manager2.setNazivFisSale("Panteon");
        manager2.setUsername("nikola123");
        manager2.setPassword("nikola123");
        manager2.setLastName("Nikolic");
        manager2.setDatumZaposljavanja(new Date(122, 0, 8));
        manager2.setDatumRodjenja(new Date(102, 5, 21));
        manager2.setIsBanovan(false);

        Client client2 = new Client();
        client2.setFirstName("Jovan");
        client2.setLastName("Jovanovic");
        client2.setBrojClanskeKartice("456789123");
        client2.setEmail("jovan@gmail.com");
        client2.setUsername("jovan");
        client2.setRole(roleClient);
        client2.setPassword("jovan123");
        client2.setDatumRodjenja(new Date(102, 2, 3));
        manager2.setClients(List.of(client2));
        client2.setManager(manager2);
        client2.setIsBanovan(false);

        Client client = new Client();
        client.setFirstName("Mihailo");
        client.setLastName("Protic");
        client.setBrojClanskeKartice("123456789");
        client.setEmail("test@gmail.com");
        client.setUsername("prota");
        client.setRole(roleClient);
        client.setPassword("1234");
        client.setIsBanovan(false);

        client.setDatumRodjenja(new Date(102, 2, 3));
        manager.setClients(List.of(client));
        client.setManager(manager);

        managerRepository.save(manager);
        managerRepository.save(manager1);
        managerRepository.save(manager2);

        userRepository.save(client);
        userRepository.save(client1);
        userRepository.save(client2);
    }
}
