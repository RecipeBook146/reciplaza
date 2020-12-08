package com.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Product {
	@Id@GeneratedValue
	private int productId;
	private String produtName;
	private String description;
	private int quantity;
	private double price;
	private String manufacturer;
	private String imageName;
	
	@OneToMany(mappedBy="product",fetch = FetchType.LAZY)
	List<Cart> cartList=new ArrayList<Cart>();
	
	
	@OneToMany(mappedBy="product",fetch = FetchType.LAZY)
	List<OrderProducts> orderProducts=new ArrayList<OrderProducts>();
	
	public Product() {
		super();
	}

	
	
	public Product(int productId, String produtName, String description, int quantity, double price,
			String manufacturer, String imageName) {
		super();
		this.productId = productId;
		this.produtName = produtName;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
		this.manufacturer = manufacturer;
		this.imageName = imageName;
	}
	
	public Product(int productId) {
		super();
		this.productId = productId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProdutName() {
		return produtName;
	}

	public void setProdutName(String produtName) {
		this.produtName = produtName;
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

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", produtName=" + produtName + ", description=" + description
				+ ", quantity=" + quantity + ", price=" + price + ", manufacturer=" + manufacturer + ", imageName="
				+ imageName + "]";
	}
	
	
}
