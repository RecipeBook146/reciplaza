package com.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Rating {
	@Id@GeneratedValue
	private int Id;
	private int rating;
	
	@ManyToOne
	@JoinColumn(name="loginId")
	User user;
	
	@ManyToOne
	@JoinColumn(name="recipeId")
	Recipe recipe;

	public Rating() {
		super();
	}

	public Rating(int id, int rating, User user, Recipe recipe) {
		super();
		Id = id;
		this.rating = rating;
		this.user = user;
		this.recipe = recipe;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
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

	@Override
	public String toString() {
		return "Rating [Id=" + Id + ", rating=" + rating + ", user=" + user + ", recipe=" + recipe + "]";
	}
	
	
}
