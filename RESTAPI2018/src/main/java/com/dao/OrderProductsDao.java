package com.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.dto.OrderProducts;
import com.dto.Orders;


public class OrderProductsDao {
	public void addOrderProducts(OrderProducts op) {
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		session.save(op); //insert query
		Transaction tx = session.beginTransaction();
		tx.commit(); //permanent save
		session.close();
	}
	public List<OrderProducts> getOrderProducts(int orderId) {
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();	
		Query q1 = session.createQuery("from OrderProducts o where o.orders.orderId=:orderId");
		q1.setParameter("orderId" ,orderId);
		List<OrderProducts> prodList = q1.list();
		session.close();
		return prodList;
	}
	public void deleteOrder(int orderId) {
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		Query q1 = session.createQuery("delete from OrderProducts o where o.orders.orderId=:orderId");
		q1.setParameter("orderId", orderId);
		q1.executeUpdate();
		Transaction tx = session.beginTransaction();
		tx.commit(); //permanent save
		session.close();
	}
	
	public void updateOrder(OrderProducts op) {
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
        session.update(op);
		tx.commit();
		session.close();		
	}

}
