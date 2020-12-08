package com.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Orders {
	@Id@GeneratedValue
	private int orderId;
	private double price;
	private java.util.Date orderDate;
	@Column(columnDefinition = "date default null")
	private java.util.Date deliveryDate;
	private String status;
	
	@ManyToOne
	@JoinColumn(name="loginId")
	User user;
	
	@ManyToOne
	@JoinColumn(name="Id")
	Address address;
	
	@OneToMany(mappedBy="orders",fetch = FetchType.LAZY)
	List<OrderProducts> orderProducts=new ArrayList<OrderProducts>();
	
	public Orders() {
		super();
	}
	
	public Orders(int orderId) {
		super();
		this.orderId = orderId;
	}
	
	public Orders(int orderId, double price, Date orderDate, Date deliveryDate, String status) {
		super();
		this.orderId = orderId;
		this.price = price;
		this.orderDate = orderDate;
		this.deliveryDate = deliveryDate;
		this.status = status;
		
	}

	public Orders(int orderId, double price, Date orderDate, Date deliveryDate, String status, User user,Address address) {
		super();
		this.orderId = orderId;
		this.price = price;
		this.orderDate = orderDate;
		this.deliveryDate = deliveryDate;
		this.status = status;
		this.user = user;
		this.address=address;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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
	
	

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<OrderProducts> getOrderProducts() {
		return orderProducts;
	}

	public void setOrderProducts(List<OrderProducts> orderProducts) {
		this.orderProducts = orderProducts;
	}

	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", price=" + price + ", orderDate=" + orderDate + ", deliveryDate="
				+ deliveryDate + ", status=" + status + ", user=" + user + ", address=" + address + ", orderProducts="
				+ orderProducts + "]";
	}

	
	
}
