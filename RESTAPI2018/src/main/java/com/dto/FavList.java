package com.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class FavList {
	@Id@GeneratedValue
	private int Id;
	
	@ManyToOne
	@JoinColumn(name="loginId")
	User user;
	
	@ManyToOne
	@JoinColumn(name="recipeId")
	Recipe recipe;
	
	public FavList() {
		super();
	}


	public FavList(int id, User user, Recipe recipe) {
		super();
		Id = id;
		this.user = user;
		this.recipe = recipe;
	}



	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}


	@Override
	public String toString() {
		return "FavList [Id=" + Id + ", user=" + user + ", recipe=" + recipe + "]";
	}
	

	

}
