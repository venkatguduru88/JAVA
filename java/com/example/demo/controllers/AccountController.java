package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.AccountDao;
import com.example.demo.dao.CustomerDao;
import com.example.demo.dto.Accounts;

@RestController
@RequestMapping("/accounts")
public class AccountController {
	
	@Autowired
	AccountDao accountDao;
	
		
	@GetMapping("/accounts")
	public ResponseEntity<?> getAllAccounts(){
		List<Accounts> list= accountDao.findAll();
		ResponseEntity<?> response;
		if(list == null) {
			response=new ResponseEntity<String>("No data found..", HttpStatus.OK);
		}
		response=new ResponseEntity<List<Accounts>>(list, HttpStatus.OK);
		
		return response;
	}
	@PostMapping("/accounts")
	public ResponseEntity<?> saveAccount(@RequestBody Accounts account){
			accountDao.save(account);
		return new ResponseEntity<List<Accounts>>(accountDao.findAll(), HttpStatus.OK);
	}
	
	//@RequestMapping(value="/getAccount",produces={"application/xml", "application/json"})
	@GetMapping("/accounts/{accountType}")
	public ResponseEntity<?> getAccountDetails(@PathVariable String accountType){
		ResponseEntity<?> response;
		List<Accounts> account=accountDao.findByAccountType(accountType);
		if(account == null)
			response=new ResponseEntity<String>("No data found..", HttpStatus.OK);
		else
			response= new ResponseEntity<List<Accounts>>(account,HttpStatus.OK);
		
		return response;
	}

}
