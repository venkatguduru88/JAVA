package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.FoodOrderDao;
import com.example.demo.dto.Orders;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	FoodOrderDao orderDao;
	
	@GetMapping("/orders")
	public ResponseEntity<?> getAllOrders(){
		ResponseEntity<?> response; 
		List<Orders> list=orderDao.findAll();
		
		if(list.size()> 0) 
			response=new ResponseEntity<List<Orders>>(list, HttpStatus.ACCEPTED);	
		
		else
			response=new ResponseEntity<String>("No data found...",HttpStatus.ACCEPTED);
		
		return response;
		
	}
	
	@PostMapping("/orders")	
	public String saveOrder(@RequestBody Orders order) {
		orderDao.save(order);
		return "Order saved successfully...";
	}
	
	@DeleteMapping("/orders/{id}")	
	public String deleteOrder(@PathVariable Integer id) {
		orderDao.deleteById(id);
		return "Order deleted successfully...";
	}
	
	@PutMapping("/orders")	
	public String updateOrder(@RequestBody Orders order) {
		orderDao.save(order);
		return "Order updated successfully...";
	}

}
