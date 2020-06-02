package com.example.demo.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table
//@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "cust_id")
public class Customer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long cust_id;
	@Column(name = "customer_name")
	private String custName;
	private String email;
	@Column(name = "contact_number")
	private long mobileNum;

	// @JsonBackReference
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	List<Accounts> accounts;

	public long getCust_id() {
		return cust_id;
	}

	public void setCust_id(long cust_id) {
		this.cust_id = cust_id;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(long mobileNum) {
		this.mobileNum = mobileNum;
	}

	public List<Accounts> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Accounts> accounts) {
		this.accounts = accounts;

	}

	@PrePersist
	private void prePersist() {
		accounts.forEach(c -> c.setCustomer(this));
	}

	@Override
	public String toString() {
		return "Customer [cust_id=" + cust_id + ", custName=" + custName + ", email=" + email + ", mobileNum="
				+ mobileNum + ", accounts=" + accounts + "]";
	}
	
	
	

}
