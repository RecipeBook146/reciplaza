package com.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.dto.Rating;

public class RatingDao {
	public void addRating(Rating rating) {
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		session.save(rating); //insert query
		Transaction tx = session.beginTransaction();
		tx.commit(); //permanent save
		session.close();
	}
	
	public int ratingCount(int recipeId){
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();	
		Query q1 = session.createQuery("from Rating r where r.recipe.recipeId=:recipeId");
		q1.setParameter("recipeId" ,recipeId);
		List<Rating> count = q1.list();
		session.close();
		if(count.size()>0){
			return count.size();
		}
		return 0;
	}

	public boolean getStat(int recipeId, String loginId) {
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();	
		Query q1 = session.createQuery("from Rating r where r.recipe.recipeId=:recipeId and r.user.loginId=:loginId");
		q1.setParameter("recipeId" ,recipeId);
		q1.setParameter("loginId" ,loginId);
		List<Rating> count = q1.list();
		session.close();
		if(count.size()>0){
			return true;
		}
		return false;
	}
	

}
