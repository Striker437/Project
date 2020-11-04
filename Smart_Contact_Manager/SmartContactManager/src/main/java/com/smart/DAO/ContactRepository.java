package com.smart.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.smart.Entities.Contact;

@Component
public interface ContactRepository extends JpaRepository<Contact, Integer> {
	//pagination
	
	//get contact list from databse
	@Query("select u from Contact u where u.user.id=:userId")
	   public List<Contact> findContactById(@Param("userId") int userId);
	
	
	//current page
	//contact per page-5
	@Query("select u from Contact u where u.user.id=:userId")
   public Page<Contact> findPageContactById(@Param("userId") int userId,Pageable pageable);
}
