package com.demo;

/*import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Orders {
	@Id@GeneratedValue
	private int orderId;
	private double amount;
	private int quantity;
	private java.util.Date orderDate;
	@Column(columnDefinition = "date default null")
	private java.util.Date deliveryDate;
	private String status;
	
	@ManyToOne
	@JoinColumn(name="loginId")
	User user;
	
	@ManyToOne
	@JoinColumn(name="productId")
	Products product;
	
	public Orders() {
		super();
	}

	public Orders(int orderId, double amount, int quantity, Date orderDate, Date deliveryDate, String status, User user,
			Products product) {
		super();
		this.orderId = orderId;
		this.amount = amount;
		this.quantity = quantity;
		this.orderDate = orderDate;
		this.deliveryDate = deliveryDate;
		this.status = status;
		this.user = user;
		this.product = product;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
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

	public java.util.Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(java.util.Date orderDate) {
		this.orderDate = orderDate;
	}

	public java.util.Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(java.util.Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Products getProduct() {
		return product;
	}

	public void setProduct(Products product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", amount=" + amount + ", quantity=" + quantity + ", orderDate="
				+ orderDate + ", deliveryDate=" + deliveryDate + ", status=" + status + ", user=" + user + ", product="
				+ product + "]";
	}
	
	
}*/