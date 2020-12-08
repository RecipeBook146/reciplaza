package com.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Cart{
	@Id@GeneratedValue
	private int Id;
	private double amount;
	private int quantity;
	
	/*@ManyToOne
	@JoinColumn(name="loginId")
	User user;*/
	
	@ManyToOne
	@JoinColumn(name="productId")
	public Products prod;

	public Cart() {
		super();
	}

	public Cart(int id, double amount, int quantity, Products product) {
		super();
		Id = id;
		this.amount = amount;
		this.quantity = quantity;
		this.prod = product;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/*public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}*/

	public Products getProduct() {
		return prod;
	}

	public void setProduct(Products product) {
		this.prod = product;
	}

	@Override
	public String toString() {
		return "Cart [Id=" + Id + ", amount=" + amount + ", quantity=" + quantity + ", user="  + ", product="
				+ prod + "]";
	}
	
	
	
	
}