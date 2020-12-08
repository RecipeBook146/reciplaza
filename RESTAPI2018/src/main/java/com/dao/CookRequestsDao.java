package com.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.dto.CookRequests;
import com.dto.User;


public class CookRequestsDao {
	public void add(CookRequests cookRequests) {
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		session.save(cookRequests); //insert query
		Transaction tx = session.beginTransaction();
		tx.commit(); //permanent save
		session.close();
	}
	public List<CookRequests> getRqsts(String loginId) {
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();	
		Query q1 = session.createQuery("from CookRequests c where c.user1.loginId=:loginId");
		q1.setParameter("loginId" ,loginId);
		List<CookRequests> cookRequests = q1.list();
		RecipesDao rDao=new RecipesDao();
		User user=new User();
		for(CookRequests obj:cookRequests){
			user=obj.getUser1();
			user.setLoginId(user.getLoginId()+","+obj.getRecipe().getTitle()+","+obj.getRecipe().getImageName());
			obj.setUser1(user);
			//obj.getUser().setLoginId(obj.getUser().getLoginId()+);
			//obj.setRecieverId(obj.getRecieverId()+","+rDao.getRecipe(obj.getRecipeId()).getTitle()+","+rDao.getRecipe(obj.getRecipeId()).getImageName());
		}
		session.close();
		return cookRequests;
	}
	
	public List<CookRequests> getAsks(String loginId) {
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();	
		Query q1 = session.createQuery("from CookRequests c where c.user.loginId=:loginId");
		q1.setParameter("loginId" ,loginId);
		List<CookRequests> cookRequests = q1.list();
		RecipesDao rDao=new RecipesDao();
		User user=new User();
		for(CookRequests obj:cookRequests){
			user=obj.getUser();
			user.setLoginId(user.getLoginId()+","+obj.getRecipe().getTitle()+","+obj.getRecipe().getImageName());
			obj.setUser(user);
			//obj.setAskerId(obj.getAskerId()+","+rDao.getRecipe(obj.getRecipeId()).getTitle()+","+rDao.getRecipe(obj.getRecipeId()).getImageName());
		}
		session.close();
		return cookRequests;
	}
	
	public void update(CookRequests cookRqst){
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(cookRqst);
		tx.commit();
		session.close();	
	}
	public void delete(CookRequests cookRqst){
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		Query q1 = session.createQuery("delete from CookRequests c where c.id=:id");
		q1.setParameter("id", cookRqst.getId());
		q1.executeUpdate();
		Transaction tx = session.beginTransaction();
		tx.commit(); //permanent save
		session.close();
	}
}
