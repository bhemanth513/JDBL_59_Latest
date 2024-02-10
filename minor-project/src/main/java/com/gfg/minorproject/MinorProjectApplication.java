package com.gfg.minorproject;

import com.gfg.minorproject.dto.CreateAdminRequest;
import com.gfg.minorproject.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MinorProjectApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MinorProjectApplication.class, args);
	}

	@Autowired
	AdminService adminService;
	@Override
	public void run(String... args) throws Exception {
//		adminService.addAdmin(CreateAdminRequest.builder()
//						.name("Raghava")
//						.username("raghava123@gmail.com")
//						.password("raghava123")
//				.build());
	}
}
