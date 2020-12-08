package com.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.dto.Recipe;
//import com.dto.Products;

public class RecipesDao {
	public void addRecipe(Recipe recipe){
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		session.save(recipe); //insert query
		Transaction tx = session.beginTransaction();
		tx.commit(); //permanent save
		session.close();
	}
	public List<Recipe> getAllRecipes() {
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		Query q1 = session.createQuery("from Recipe r");
		List<Recipe> recipeList = q1.list();
		session.close();
		return recipeList;
	}
	
	public List<Recipe> getUploadedRecipes(String loginId) {
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		Query q1 = session.createQuery("from Recipe r where r.user.loginId=:loginId");
		q1.setParameter("loginId" ,loginId);
		List<Recipe> recipeList = q1.list();
		session.close();
		return recipeList;
	}
	
	public Recipe getRecipe(int recipeId) {
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();	
	    //Employee employee = (Employee)session.load(Employee.class, empId);
		Recipe recipe = (Recipe) session.get(Recipe.class, recipeId);		
		System.out.println(recipe); 
		return recipe;
	}
	
	public void deleteRecipe(Recipe recipe) {
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		session.delete(recipe); //insert query
		Transaction tx = session.beginTransaction();
		tx.commit(); //permanent save
		session.close();
	}

	public void updateRecipe(Recipe recipe) {
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
        session.update(recipe);
		tx.commit();
		session.close();		
	}
	
	public int getRating(int recipeId){
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();	
		Query q1 = session.createQuery("select r.rating from Recipe r where r.recipeId=:recipeId");
		q1.setParameter("recipeId", recipeId);
		List<Integer> list = q1.list();
		session.close();
		return list.get(0);
	}
	
	public int getLikes(int recipeId){
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();	
		Query q1 = session.createQuery("select r.likes from Recipe r where r.recipeId=:recipeId");
		q1.setParameter("recipeId", recipeId);
		List<Integer> list = q1.list();
		session.close();
		return list.get(0);
	}
	public Set<Recipe> getProducts(String desc) {
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();	
		Set<Recipe> recipeList=new HashSet<Recipe>();
		System.out.println(desc);
    	String[] descList = desc.trim().split("\\s+");
    	for(String s:descList){
    		System.out.println(s);
    		Query q1 = session.createQuery("from Recipe r where r.title like :s");
    		q1.setParameter("s" ,"%"+s+"%");
    		List<Recipe> tempList = q1.list();
    		for(Recipe recipe:tempList){
    			recipeList.add(recipe);
    		}
    	}
		session.close();
		return recipeList;
	}

}
