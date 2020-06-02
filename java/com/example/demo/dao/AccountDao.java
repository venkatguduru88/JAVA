package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.Accounts;

@Repository
@Transactional
public interface AccountDao extends JpaRepository<Accounts, Long>{

	public List<Accounts> findByAccountType(String accountType);
	
	//public Accounts findByAcc_id(long id);

}
