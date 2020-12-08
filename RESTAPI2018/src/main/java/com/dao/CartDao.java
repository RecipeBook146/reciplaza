package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.dto.Address;
import com.dto.Cart;
import com.dto.Product;

public class CartDao {
	public String addToCart(Cart cart) {
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		int quan=checkExists(cart.getUser().getLoginId(),cart.getProduct().getProductId());
		if(quan>0){
			updateCart(quan+1,cart.getProduct().getProductId(),cart.getUser().getLoginId());
			return "updated";
		}
		session.save(cart);
		Transaction tx = session.beginTransaction();
		tx.commit(); //permanent save
		session.close();
		return "inserted";
	}

	public int checkExists(String loginId,int productId){
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		Query q1 = session.createQuery("from Cart c where c.user.loginId=:loginId and c.product.productId=:productId");
		q1.setParameter("loginId", loginId);
		q1.setParameter("productId", productId);
		List<Cart> cartList=q1.list();
		if(cartList.size()>0){
			return cartList.get(0).getQuantity();
		}
		Transaction tx = session.beginTransaction();
		tx.commit(); //permanent save
		session.close();
		return 0;
	}

	public List<Product> getcartItems(String loginId) {
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();	
		Query q1 = session.createQuery("from Cart c where c.user.loginId=:loginId");
		q1.setParameter("loginId" ,loginId);
		List<Cart> cartList = q1.list();
		List<Product> prodList=new ArrayList<Product>();
		ProductsDao dao=new ProductsDao();
		Product prod=new Product();
		for(Cart cart:cartList){
			prod=dao.getProduct(cart.getProduct().getProductId());
			prod.setQuantity(cart.getQuantity());
			prodList.add(prod);
		}
		session.close();
		return prodList;
	}
	public void deleteItem(int productId,String loginId) {
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		Query q1 = session.createQuery("delete from Cart c where c.user.loginId=:loginId and c.product.productId=:productId");
		q1.setParameter("loginId", loginId);
		q1.setParameter("productId", productId);
		q1.executeUpdate();
		Transaction tx = session.beginTransaction();
		tx.commit(); //permanent save
		session.close();
	}

	public List<Integer> getQuan(String loginId) {
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		List<Product> prodList=getcartItems(loginId);
		List<Integer> quanList=new ArrayList<Integer>();
		int productId;
		for(Product prod:prodList){
			productId=prod.getProductId();
			Query q1 = session.createQuery("select c.quantity from Cart c where c.user.loginId=:loginId and c.product.productId=:productId");
			q1.setParameter("loginId", loginId);
			q1.setParameter("productId", productId);
			quanList.add((Integer) q1.list().get(0));
		}
		Transaction tx = session.beginTransaction();
		tx.commit(); //permanent save
		session.close();
		return quanList;
	}


	public double getTot(String loginId) {
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		List<Product> prodList=getcartItems(loginId);
		int productId,c;
		double totPrice=0;
		for(Product prod:prodList){
			productId=prod.getProductId();
			Query q1 = session.createQuery("select c.quantity from Cart c where c.user.loginId=:loginId and c.product.productId=:productId");
			q1.setParameter("loginId", loginId);
			q1.setParameter("productId", productId);
			c=(Integer) q1.list().get(0);
			totPrice+=prod.getPrice()*c;
		}
		Transaction tx = session.beginTransaction();
		tx.commit(); //permanent save
		session.close();
		return totPrice;
	}

	public void updateCart(int quan,int productId,String loginId) {
		if(quan==0){
			deleteItem(productId, loginId);
		}else{
			Configuration config = new Configuration();
			config.configure("hibernate.cfg.xml");
			SessionFactory factory = config.buildSessionFactory();
			Session session = factory.openSession();
			Query q1=session.createQuery("update Cart c set c.quantity=:quan where c.user.loginId=:loginId and c.product.productId=:productId");
			q1.setParameter("quan", quan);
			q1.setParameter("loginId", loginId);
			q1.setParameter("productId", productId);
			q1.executeUpdate();
			Transaction tx = session.beginTransaction();
			tx.commit();
			session.close();		
		}
	}
}
