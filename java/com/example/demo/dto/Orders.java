package com.example.demo.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

@Entity
public class Orders implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Integer order_Id;
	@Column
	private String orderName;
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<Items> items;
	
	public Integer getOrderId() {
		return order_Id;
	}
	public void setOrderId(Integer order_Id) {
		this.order_Id = order_Id;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public List<Items> getItems() {
		return items;
	}
	public void setItems(List<Items> items) {
		this.items = items;
	}
	
	@PrePersist
	private void prePersist() {
		items.forEach(c -> c.setOrder(this));
	}

}
