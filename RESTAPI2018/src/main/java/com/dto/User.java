package com.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class User {
	@Id
	private String loginId;
	private String email;
	private String password;

	@OneToMany(mappedBy="user",fetch = FetchType.LAZY)
	List<Recipe> recipeList=new ArrayList<Recipe>();
	
	@OneToMany(mappedBy="user",fetch = FetchType.LAZY)
	List<CookRequests> list=new ArrayList<CookRequests>();
	
	@OneToMany(mappedBy="user",fetch = FetchType.LAZY)
	List<CookRequests> list1=new ArrayList<CookRequests>();
	
	@OneToMany(mappedBy="user",fetch = FetchType.LAZY)
	List<Address> addressList=new ArrayList<Address>();
	
	@OneToMany(mappedBy="user",fetch = FetchType.LAZY)
	List<Cart> cartList=new ArrayList<Cart>();
	
	@OneToMany(mappedBy="user",fetch = FetchType.LAZY)
	List<Orders> orderList=new ArrayList<Orders>();
	
	@OneToMany(mappedBy="user",fetch = FetchType.LAZY)
	List<FavList> favList=new ArrayList<FavList>();
	
	@OneToMany(mappedBy="user",fetch = FetchType.LAZY)
	List<Rating> ratingList=new ArrayList<Rating>();
	
	/*@OneToMany(mappedBy="user",fetch = FetchType.LAZY)
	List<CookRequests> list1=new ArrayList<CookRequests>();
	
	@OneToMany(mappedBy="user",fetch = FetchType.LAZY)
	List<CookRequests> list2=new ArrayList<CookRequests>();*/
	
	
	
	

	public User() {
		super();
	}
	public User(String loginId, String email, String password) {
		super();
		this.loginId = loginId;
		this.email = email;
		this.password = password;
	}
	
	public User(String loginId) {
		super();
		this.loginId = loginId;
	}
	
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [loginId=" + loginId + ", email=" + email + ", password=" + password + "]";
	}
}
