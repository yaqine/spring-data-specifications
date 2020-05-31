package com.demo.springdata.repo.criteria;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.springdata.repo.User;
import com.demo.springdata.repo.User_;

@Repository
public class UserRepositoryCustomImpl implements UserRepositoryCustom {
	
	@Autowired EntityManager em;
	
	@Override
    public List<User> search(String firstName, String lastName, String email) {
		
		// create user query 
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> user = cq.from(User.class);
        
        // firstName predicate
        Predicate firstNamePredicate = cb.like(user.get(User_.FIRST_NAME), "%" + firstName + "%");
        
        // lastName predicate
        Predicate lastNamePredicate = cb.like(user.get(User_.LAST_NAME), "%" + lastName + "%");
        
        // email predicate
        Predicate emailPredicate = cb.equal(user.get(User_.EMAIL), email);
        
        // apply predicates
        cq.where(firstNamePredicate, lastNamePredicate, emailPredicate);
 
        // return results
        TypedQuery<User> query = em.createQuery(cq);
        return query.getResultList();
    }

}
