package com.demo.springdata.repo.specification;

import org.springframework.data.jpa.domain.Specification;

import com.demo.springdata.repo.User;
import com.demo.springdata.repo.User_;

public class UserSpecifications {
	
	public static Specification<User> likeFirstName(String firstName) {
		if (firstName == null) {
			return null;
		}
		return (root, query, cb) -> {
			return cb.like(root.get(User_.FIRST_NAME), "%" + firstName + "%");
		};
	}
	
	public static Specification<User> likeLastName(String lastName) {
		if (lastName == null) {
			return null;
		}
		return (root, query, cb) -> {
			return cb.like(root.get(User_.LAST_NAME), "%" + lastName + "%");
		};
	}
	
	public static Specification<User> equalEmail(String email) {
		if (email == null) {
			return null;
		}
		return (root, query, cb) -> {
			return cb.equal(root.get(User_.EMAIL), email);
		};
	}

}
