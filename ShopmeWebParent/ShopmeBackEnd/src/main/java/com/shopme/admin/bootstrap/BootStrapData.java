package com.shopme.admin.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.shopme.admin.repositories.RoleRepository;
import com.shopme.admin.services.UserService;
import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;

@Component
public class BootStrapData implements CommandLineRunner {
	
	private final UserService userService;
	private final RoleRepository roleRepository;
	
	public BootStrapData(UserService userService, RoleRepository roleRepository) {
		this.userService = userService;
		this.roleRepository = roleRepository;
	}

	@Transactional
	@Override
	public void run(String... args) throws Exception {
		if (roleRepository.count() == 0)
			createRoles();
		
		if (userService.getUsers().size() == 0)
			createSampleUsers();
	}
	
	private void createRoles() {
		Role roleAdmin = new Role("Admin", "manage everything");
		
		Role roleSalesperson = new Role("Salesperson", "manage product price, "
				+ "customers, shipping, orders and sales report");
		
		Role roleEditor = new Role("Editor", "manage categories, brands, "
				+ "products, articles and menus");
		
		Role roleShipper = new Role("Shipper", "view products, view orders "
				+ "and update order status");
		
		Role roleAssistant = new Role("Assistant", "manage questions and reviews");
		
		roleRepository.save(roleAdmin);
		roleRepository.save(roleSalesperson);
		roleRepository.save(roleEditor);
		roleRepository.save(roleShipper);
		roleRepository.save(roleAssistant);
	}
	
	private void createSampleUsers() {
		User userShinmen = new User("shinmen.dokkodo@gmail.com", "shinmen123", "Shinmen", "Dokkodo");
		Role roleEditor = new Role(3);
		Role roleAssistant = new Role(5);

		userShinmen.addRole(roleEditor);
		userShinmen.addRole(roleAssistant);

		User userTabaraq = new User("tabaraq.sms@gmail.com", "Anarkali77", "Tabaraq", "Sal");
		Role roleAdmin = new Role(1);
		userTabaraq.addRole(roleAdmin);

		userService.save(userTabaraq);
		userService.save(userShinmen);
	}
}
