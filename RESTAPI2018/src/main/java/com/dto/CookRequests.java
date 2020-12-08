package com.dto;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class CookRequests {
	@Id@GeneratedValue
	private int id;
	private int persons;
	private java.util.Date date;
	private Time time;
	private String address;
	private String message;
	private String status;
	@ManyToOne
	@JoinColumn(name="askerId")
	User user;
	
	@ManyToOne
	@JoinColumn(name="recieverId")
	User user1;
	
	@ManyToOne
	@JoinColumn(name="recipeId")
	Recipe recipe;

	public CookRequests() {
		super();
	}

	public CookRequests(int id, int persons, Date date, Time time, String address, String message, String status, User user,
			User user1, Recipe recipe) {
		super();
		this.id = id;
		this.persons = persons;
		this.date = date;
		this.time = time;
		this.address = address;
		this.message = message;
		this.status = status;
		this.user = user;
		this.user1 = user1;
		this.recipe = recipe;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPersons() {
		return persons;
	}

	public void setPersons(int persons) {
		this.persons = persons;
	}

	public java.util.Date getDate() {
		return date;
	}

	public void setDate(java.util.Date date) {
		this.date = date;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

	public User getUser1() {
		return user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	@Override
	public String toString() {
		return "Demo [id=" + id + ", persons=" + persons + ", date=" + date + ", time=" + time + ", address=" + address
				+ ", message=" + message + ", status=" + status + ", user=" + user + ", user1=" + user1 + ", recipe="
				+ recipe + "]";
	}

	

	
}
