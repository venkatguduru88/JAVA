package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import javax.json.JsonPatch;
import javax.json.JsonStructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.CustomerDao;
import com.example.demo.dto.Customer;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr353.JSR353Module;

@RestController
//@Service
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	CustomerDao customerDao;
	
	
	
	@GetMapping("/customers")
	public ResponseEntity<?> getAllCustomers(){
		ResponseEntity<?> response; 
		List<Customer> list=customerDao.findAll();
		
		if(list.size()> 0) 
			response=new ResponseEntity<List<Customer>>(list, HttpStatus.ACCEPTED);	
		
		else
			response=new ResponseEntity<String>("No data found...",HttpStatus.ACCEPTED);
		
		return response;
	}
	
	@GetMapping("/customers/{customerName}")
	public ResponseEntity<?> getAllCustomersByName(@PathVariable("customerName") String custName){
		ResponseEntity<?> response; 
		List<Customer> list=customerDao.findByCustName(custName);
		
		if(list.size()> 0) 
			response=new ResponseEntity<List<Customer>>(list, HttpStatus.ACCEPTED);	
		
		else
			response=new ResponseEntity<String>("No data found...",HttpStatus.ACCEPTED);
		
		return response;
	}
	@PostMapping("/customers")	
	public String saveCustomer(@RequestBody Customer customer) {
		customerDao.save(customer);
		return "Customer data saved successfully...";
	}
	
	@DeleteMapping("/customers/{id}")	
	public String deleteCustomer(@PathVariable long id) {
		customerDao.deleteById(id);
		return "Customer data deleted successfully...";
	}
	
	@PutMapping("/customers")	
	public ResponseEntity<?> updateCustomerUsingPut(@RequestBody Customer customer) {
		Customer cust=customerDao.save(customer);
		return new ResponseEntity<Customer>(cust, HttpStatus.ACCEPTED);
	}
	
	@PatchMapping(value="/customers/{id}", consumes = "application/json-patch+json")	
	public ResponseEntity<?> updateCustomerUsingPatch(@PathVariable Long id,@RequestBody JsonPatch patchDocument) {
		Optional<Customer> cust=customerDao.findById(id);
		// Convert the Java bean to a JSON document
		JsonStructure target=null;
		ObjectMapper mapper =new ObjectMapper();
				mapper.registerModule(new JSR353Module());;
		if(cust != null)
			target= mapper.convertValue(cust, JsonStructure.class);
	    
	    // Apply the JSON Patch to the JSON document
	    JsonValue patched = (JsonValue) patchDocument.apply(target);
	    
	    // Convert the JSON document to a Java bean and return it
	     Customer custpatch=mapper.convertValue(patched, Customer.class);
	     Customer c2=customerDao.save(custpatch);
		return new ResponseEntity<Customer>(c2, HttpStatus.ACCEPTED);
	}

}
