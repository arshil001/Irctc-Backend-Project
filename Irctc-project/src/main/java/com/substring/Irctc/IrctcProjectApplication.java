package com.substring.Irctc;

import com.substring.Irctc.entity.Role;
import com.substring.Irctc.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class IrctcProjectApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(IrctcProjectApplication.class, args);
	}

	@Autowired
	private RoleRepo roleRepo;

	@Override
	public void run(String... args) throws Exception {

		if(!roleRepo.existsByName("Role_Admin"))
		{

			Role adminRole = new Role();
			adminRole.setId(UUID.randomUUID().toString());
			adminRole.setName("ROLE_Admin");
			roleRepo.save(adminRole);
		}

		if (!roleRepo.existsByName("ROLE_NROMAL"))
		{
			Role userRole = new Role();
			userRole.setId(UUID.randomUUID().toString());
			userRole.setName("ROLE_NORMAL");
			roleRepo.save(userRole);

		}
		System.out.println(" Role Initialized Successfully");

	}
}
