package com.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.db.HibernateTemplate;
import com.dto.CookRequests;
import com.dto.Recipe;
//import com.dto.Products;
import com.dto.User;

public class UserDao {
	public void register(User u) {
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		session.save(u); //insert query
		Transaction tx = session.beginTransaction();
		tx.commit(); //permanent save
		session.close();
	}

	public User verifyEmail(String email) {
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();	
		//System.out.println(loginId);
		Query q1 = session.createQuery("from User u where u.email=:email");
		q1.setParameter("email" ,email);
		List<User> userList = q1.list();	
		if(userList.size()>0){
			return userList.get(0);
		}
		return null;
	}


	public User verifyLogin(String loginId) {
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();	
		System.out.println(loginId);
		User user = (User) session.get(User.class, loginId);		
		return user;
	}
	public List<User> getAllUsers() {
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		Query q1 = session.createQuery("from User u");
		List<User> userList = q1.list();
		session.close();
		return userList;
	}





}
