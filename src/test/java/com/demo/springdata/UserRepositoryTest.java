package com.demo.springdata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.springdata.repo.User;
import com.demo.springdata.repo.criteria.UserRepository;

@SpringBootTest
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	void search_shouldWork() {
		User user = new User();
		user.setFirstName("yaqine");
		user.setLastName("essadiki");
		user.setEmail("test@email.com");
		
		userRepository.save(user);
		
		List<User> users = userRepository.search("yaq", "ess", "test@email.com");
		assertNotNull(users);
		assertEquals(1, users.size());
	}

}
