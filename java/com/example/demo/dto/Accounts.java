package com.example.demo.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
//@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="acc_id")
public class Accounts implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long acc_id;
	@Column(name="account_type")
	private String accountType;
	@Column(name="opening_date")
	private Date openingDate;
	@Column(name="description")
	private String description;
	
	//@JsonManagedReference
	@JsonIgnore
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name = "customer_fk")
	private Customer customer;
	
	
	public long getAcc_id() {
		return acc_id;
	}
	public void setAcc_id(long acc_id) {
		this.acc_id = acc_id;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public Date getOpeningDate() {
		return openingDate;
	}
	public void setOpeningDate(Date openingDate) {
		this.openingDate = openingDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Accounts [acc_id=" + acc_id + ", accountType=" + accountType + ", openingDate=" + openingDate
				+ ", description=" + description +"]";
	}
	
	

}
