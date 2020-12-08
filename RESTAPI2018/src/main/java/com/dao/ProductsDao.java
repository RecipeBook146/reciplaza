package com.dao;

import java.util.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.dto.Product;

public class ProductsDao {
	public Set<Product> getProducts(String desc) {
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();	
		Set<Product> prodList=new HashSet<Product>();
		System.out.println(desc);
    	String[] descList = desc.trim().split("\\s+");
    	for(String s:descList){
    		System.out.println(s);
    		Query q1 = session.createQuery("from Product p where p.produtName like :s");
    		q1.setParameter("s" ,"%"+s+"%");
    		List<Product> tempList = q1.list();
    		for(Product prod:tempList){
    			prodList.add(prod);
    		}
    	}
		session.close();
		return prodList;
	}
	
	
	public void addProduct(Product prod) {
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		session.save(prod); //insert query
		Transaction tx = session.beginTransaction();
		tx.commit(); //permanent save
		session.close();
	}
	public List<Product> getAllProducts() {
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		Query q1 = session.createQuery("from Product p");
		List<Product> prodList = q1.list();
		session.close();
		return prodList;
	}
	public Product getProduct(int productId) {
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();	
	    //Employee employee = (Employee)session.load(Employee.class, empId);
		Product product = (Product) session.get(Product.class, productId);		
		System.out.println(product); 
		return product;
	}
	
	public void deleteProduct(int productId) {
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		Query q1 = session.createQuery("delete from Product p where p.productId=:productId");
		q1.setParameter("productId", productId);
		q1.executeUpdate();
		Transaction tx = session.beginTransaction();
		tx.commit(); //permanent save
		session.close();
	}

	public void updateProduct(Product product) {
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
        session.update(product);
		tx.commit();
		session.close();		
	}
}
