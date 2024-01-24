package com.User;

import com.User.domain.Admin;
import com.User.repository.AdminRepository;
import com.User.service.impl.AdminServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
		Admin admin = new Admin();

		admin.setUsername("admin");
		admin.setPassword("admin");

	}

}
