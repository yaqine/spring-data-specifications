package com.demo.springdata.repo.criteria;

import java.util.List;

import com.demo.springdata.repo.User;

public interface UserRepositoryCustom {
	
	List<User> search(String firstName, String lastName, String email);

}
