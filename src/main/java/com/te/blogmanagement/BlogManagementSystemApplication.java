package com.te.blogmanagement;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.google.common.collect.Lists;
import com.te.blogmanagement.entity.Admin;
import com.te.blogmanagement.entity.AppUser;
import com.te.blogmanagement.entity.Roles;
import com.te.blogmanagement.repository.AdminRepository;
import com.te.blogmanagement.repository.AppUserRepository;
import com.te.blogmanagement.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@SpringBootApplication
public class BlogManagementSystemApplication {
	private final RoleRepository roleRepository;
	private final AdminRepository adminRepository;
	private final AppUserRepository appUserRepository;

	public static void main(String[] args) {
		SpringApplication.run(BlogManagementSystemApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner() {
		return agrs -> {
			Roles user = Roles.builder().roleName("ROLE_USER").build();
			Roles admin = Roles.builder().roleName("ROLE_ADMIN").appUser(Lists.newArrayList()).build();

			Admin adminCredentials = Admin.builder().adminId("adminId").adminName("adminName").build();

			AppUser appUser = AppUser.builder().userName(adminCredentials.getAdminId()).password("qwerty")
					.roles(Lists.newArrayList()).build();
			roleRepository.save(user);
			roleRepository.save(admin);

			adminRepository.save(adminCredentials);

			admin.getAppUser().add(appUser);
			appUser.getRoles().add(admin);
			roleRepository.save(admin);
			appUserRepository.save(appUser);

		};
	}

}
