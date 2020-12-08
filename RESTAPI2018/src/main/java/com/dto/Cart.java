package com.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Cart {
	@Id@GeneratedValue
	private int Id;
	private int quantity;
	private double price;
	
	@ManyToOne
	@JoinColumn(name="loginId")
	User user;
	
	@ManyToOne
	@JoinColumn(name="productId")
	Product product;

	public Cart() {
		super();
	}

	public Cart(int id, int quantity, double price, User user, Product product) {
		super();
		Id = id;
		this.quantity = quantity;
		this.price = price;
		this.user = user;
		this.product = product;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Cart [Id=" + Id + ", quantity=" + quantity + ", price=" + price + ", user=" + user + ", product="
				+ product + "]";
	}
	
	

}
