package com.demo.springdata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;

import com.demo.springdata.repo.User;
import com.demo.springdata.repo.specification.UserRepositorySpec;
import com.demo.springdata.repo.specification.UserSpecifications;

@SpringBootTest
public class UserRepositorySpecTest {
	
	@Autowired
	private UserRepositorySpec userRepositorySpec;
	
	User user;
	
    @BeforeEach
    void init() {
    	user = new User();
    	user.setEmail("yaqineessadiki@gmail.com");
    	user.setFirstName("yaqine");
    	user.setLastName("essadiki");
    	
    	userRepositorySpec.save(user);
    }
    
    @Test
	void search_with_one_spec__shouldWork() {
		List<User> users = userRepositorySpec.findAll(UserSpecifications.likeFirstName("yaq"));
		assertNotNull(users);
		assertEquals(1, users.size());
	}
    
	@Test
	void search_with_all_specs__shouldWork() {
		Specification<User> specs = Specification.where(UserSpecifications.likeFirstName("yaq"))
				.and(UserSpecifications.likeLastName("ess"))
				.and(UserSpecifications.equalEmail("yaqineessadiki@gmail.com"));
		
		List<User> users = userRepositorySpec.findAll(specs);
		assertNotNull(users);
		assertEquals(1, users.size());
	}
	
	@Test
 	void search_using_or__shouldWork() {
 		Specification<User> specs = Specification.where(UserSpecifications.likeFirstName("joe"))
 				.or(UserSpecifications.likeFirstName("yaq"));
 		
 		List<User> users = userRepositorySpec.findAll(specs);
 		assertNotNull(users);
 		assertEquals(1, users.size());
 	}
	
    @Test
	void search_with_null_spec__shouldWork() {
		Specification<User> specs = Specification.where(UserSpecifications.likeFirstName(null))
				.and(UserSpecifications.likeLastName("ess"))
				.and(UserSpecifications.equalEmail("yaqineessadiki@gmail.com"));
		
		List<User> users = userRepositorySpec.findAll(specs);
		assertNotNull(users);
		assertEquals(1, users.size());
	}
	
    @Test
	void search_with_one_spec_no_data__shouldWork() {
		Specification<User> specs = Specification.where(UserSpecifications.likeFirstName("joe"));
		
		List<User> users = userRepositorySpec.findAll(specs);
		assertNotNull(users);
		assertEquals(0, users.size());
	}
    
    
    @AfterEach
    void tearDown() {
    	userRepositorySpec.delete(user);
    	
    }
}
