package com.demo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

/*@Entity
@XmlRootElement*/
public class Recipes {
	/*@Id@GeneratedValue
	private int recipeId;
	private String title;
	private String ingredients;
	private String proc;
	private int likes;
	private int rating;
	
	@ManyToOne
	@JoinColumn(name="loginId")
	User user;
	
	@OneToMany(mappedBy="recipes")
	List<FavList> favList=new ArrayList<FavList>();

	public Recipes(User user) {
		super();
	}

	public Recipes(int recipeId, String title, String ingredients, String procedure, int likes, int rating, User user) {
		super();
		this.recipeId = recipeId;
		this.title = title;
		this.ingredients = ingredients;
		this.proc = procedure;
		this.likes = likes;
		this.rating = rating;
		this.user = user;
	}

	public int getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public String getProcedure() {
		return proc;
	}

	public void setProcedure(String procedure) {
		this.proc = procedure;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
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

	@Override
	public String toString() {
		return "Recipes [recipeId=" + recipeId + ", title=" + title + ", ingredients=" + ingredients + ", procedure="
				+ proc + ", likes=" + likes + ", rating=" + rating + ", user=" + user + "]";
	}
	*/

}
