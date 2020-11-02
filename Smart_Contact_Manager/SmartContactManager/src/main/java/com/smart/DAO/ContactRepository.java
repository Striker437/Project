package com.smart.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.smart.Entities.Contact;

@Component
public interface ContactRepository extends JpaRepository<Contact, Integer> {
	
	
	@Query("select u from Contact u where u.user.id=:userId")
   public List<Contact> findContactById(@Param("userId") int userId);
}
