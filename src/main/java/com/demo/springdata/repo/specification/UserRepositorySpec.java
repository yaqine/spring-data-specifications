package com.demo.springdata.repo.specification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.demo.springdata.repo.User;

@Repository
public interface UserRepositorySpec extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

}