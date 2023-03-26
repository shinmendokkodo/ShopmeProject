package com.shopme.admin.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.shopme.common.entity.Role;

@DataJpaTest
@Rollback
class RoleRepositoryTest {

	@Autowired
	private RoleRepository roleRepository;
	
	@Test
	public void testCreateFirstRole() {
		Role roleAdmin = new Role("Admin", "manage everything");
		Role savedRole = roleRepository.save(roleAdmin);
		assertThat(savedRole.getId()).isGreaterThan(0);
		System.out.println(savedRole);
	}
	
	@Test
	public void testCreateRestRoles() {
		Role roleSalesperson = new Role("Salesperson", "manage product price, "
				+ "customers, shipping, orders and sales report");
		
		Role roleEditor = new Role("Editor", "manage categories, brands, "
				+ "products, articles and menus");
		
		Role roleShipper = new Role("Shipper", "view products, view orders "
				+ "and update order status");
		
		Role roleAssistant = new Role("Assistant", "manage questions and reviews");
		
		roleRepository.saveAll(List.of(roleSalesperson, roleEditor, roleShipper, roleAssistant));
		
		int expectedSize = 4;
		
		assertThat(roleRepository.count()).isEqualTo(expectedSize);
	}

}
