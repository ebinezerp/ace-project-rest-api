package com.exportimport.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.exportimport.restapi.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	@Query("select c from Customer c where (c.email=:email and c.password=:password and c.enabled=true) or (c.username=:username and c.password=:password and c.enabled=true)")
	public Customer login(String email,String username,String password);
}
