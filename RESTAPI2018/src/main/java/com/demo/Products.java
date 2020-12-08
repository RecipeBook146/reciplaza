package com.demo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Products {
	@Id@GeneratedValue
	private int productId;
	private String productName;
	private String description;
	private int quantity;
	private double price;
	private String manufacturer;
	
	@OneToMany(mappedBy="products")
	List<Cart> prodList=new ArrayList<Cart>();
	
	
	/*@OneToMany(mappedBy="products")
	List<Orders> orderList=new ArrayList<Orders>();*/
	
	public Products() {
		super();
	}
	public Products(int productId, String productName, String description, int quantity, double price,
			String manufacturer) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
		this.manufacturer = manufacturer;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	@Override
	public String toString() {
		return "Products [productId=" + productId + ", productName=" + productName + ", description=" + description
				+ ", quantity=" + quantity + ", price=" + price + ", manufacturer=" + manufacturer + "]";
	}

}
