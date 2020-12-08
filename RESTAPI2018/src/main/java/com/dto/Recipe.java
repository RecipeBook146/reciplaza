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
public class Recipe {
	@Id@GeneratedValue
	private int recipeId;
	private String title;
	private String category;
	private String cookTime;
	private int persons;
    private String description;
	private String ingredients;
	private String proc;
	private String tips;
	private String imageName;
	private int likes;
	private int rating;
	private String stat;
	private double price;
	private int np;
	private String conf;
	
	@ManyToOne
	@JoinColumn(name="loginId")
	User user;
	
	@OneToMany(mappedBy="recipe",fetch = FetchType.LAZY)
	List<FavList> favList=new ArrayList<FavList>();
	
	@OneToMany(mappedBy="recipe",fetch = FetchType.LAZY)
	List<Rating> ratingList=new ArrayList<Rating>();
	
	public Recipe() {
		super();
	}
	
	public Recipe(int recipeId) {
		super();
		this.recipeId = recipeId;
	}


	public Recipe(int recipeId, String title, String category, String cookTime, int persons, String description,
			String ingredients, String proc, String tips, String imageName, int likes, int rating, String stat,
			double price, int np, String conf, User user) {
		super();
		this.recipeId = recipeId;
		this.title = title;
		this.category = category;
		this.cookTime = cookTime;
		this.persons = persons;
		this.description = description;
		this.ingredients = ingredients;
		this.proc = proc;
		this.tips = tips;
		this.imageName = imageName;
		this.likes = likes;
		this.rating = rating;
		this.stat = stat;
		this.price = price;
		this.np = np;
		this.conf = conf;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCookTime() {
		return cookTime;
	}

	public void setCookTime(String cookTime) {
		this.cookTime = cookTime;
	}

	public int getPersons() {
		return persons;
	}

	public void setPersons(int persons) {
		this.persons = persons;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public String getProc() {
		return proc;
	}

	public void setProc(String proc) {
		this.proc = proc;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
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
	
	

	public String getStat() {
		return stat;
	}



	public void setStat(String stat) {
		this.stat = stat;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}



	public int getNp() {
		return np;
	}



	public void setNp(int np) {
		this.np = np;
	}



	public String getConf() {
		return conf;
	}



	public void setConf(String conf) {
		this.conf = conf;
	}



	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}



	@Override
	public String toString() {
		return "Recipe [recipeId=" + recipeId + ", title=" + title + ", category=" + category + ", cookTime=" + cookTime
				+ ", persons=" + persons + ", description=" + description + ", ingredients=" + ingredients + ", proc="
				+ proc + ", tips=" + tips + ", imageName=" + imageName + ", likes=" + likes + ", rating=" + rating
				+ ", stat=" + stat + ", price=" + price + ", np=" + np + ", conf=" + conf + ", user=" + user + "]";
	}

	

	

}
