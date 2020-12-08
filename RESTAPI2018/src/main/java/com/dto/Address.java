package com.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Address {
	@Id@GeneratedValue
	private int Id;
	private String hno;
	private String street;
	private String city;
	private String state;
	private String landmark;
	private String pincode;
	
	@OneToMany(mappedBy="address",fetch = FetchType.LAZY)
	List<Orders> orders=new ArrayList<Orders>();
	
	@ManyToOne
	@JoinColumn(name="loginId")
	User user;

	public Address() {
		super();
	}

	

	public Address(int id) {
		super();
		Id = id;
	}



	public Address(int id, String hno, String street, String city, String state, String landmark, String pincode, User user) {
		super();
		Id = id;
		this.hno = hno;
		this.street = street;
		this.city = city;
		this.state = state;
		this.landmark = landmark;
		this.user = user;
		this.pincode=pincode;
	}


	public int getId() {
		return Id;
	}


	public void setId(int id) {
		Id = id;
	}


	public String getHno() {
		return hno;
	}

	public void setHno(String hno) {
		this.hno = hno;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	


	public String getPincode() {
		return pincode;
	}


	public void setPincode(String pincode) {
		this.pincode = pincode;
	}


	@Override
	public String toString() {
		return "Address [Id=" + Id + ", hno=" + hno + ", street=" + street + ", city=" + city + ", state=" + state
				+ ", landmark=" + landmark + ", pincode=" + pincode + ", user=" + user + "]";
	}


	

	
	
	

}
