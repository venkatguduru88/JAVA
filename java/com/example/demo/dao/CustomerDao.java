package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.Accounts;
import com.example.demo.dto.Customer;
@Repository
@Transactional
public interface CustomerDao extends JpaRepository<Customer, Long>{
	
	public List<Customer> findByCustName(String custName);
	
	//public Accounts findByCustId(String accountType);

}
