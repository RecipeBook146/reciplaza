package com.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class OrderProducts {
	@Id@GeneratedValue
	private int Id;
	
	private int quantity;
	private double price;
	
	@ManyToOne
	@JoinColumn(name="productId")
	Product product;
	
	@ManyToOne
	@JoinColumn(name="orderId")
	Orders orders;

	public OrderProducts() {
		super();
	}

	public OrderProducts(int Id,int quantity, double price, Product product, Orders orders) {
		super();
		this.Id=Id;
		this.quantity = quantity;
		this.price = price;
		this.product = product;
		this.orders = orders;
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "OrderProducts [quantity=" + quantity + ", price=" + price + ", product=" + product + ", orders="
				+ orders + "]";
	}
	
	
	
	
}
