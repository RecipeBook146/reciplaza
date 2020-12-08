package com.dao;

import java.util.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.dto.*;

public class OrdersDao {
	public void addOrder(String loginId,int id) {
		User user=new User(loginId);
		Address address=new Address(id);
		CartDao dao=new CartDao();
		double price=dao.getTot(loginId);
		Orders order=new Orders(1,price,new Date(),null,"Not Delivered",user,address);
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
	    session.save(order);
	    Orders obj=new Orders(order.getOrderId());
		Transaction tx = session.beginTransaction();
		tx.commit(); //permanent save
		session.close();		
		List<Product> prodList= dao.getcartItems(loginId);
		List<Integer> quanList=dao.getQuan(loginId);
		for(int i=0;i<prodList.size();i++){
			dao.deleteItem(prodList.get(i).getProductId(), loginId);
			OrderProducts op=new OrderProducts(1,quanList.get(i),quanList.get(i)*prodList.get(i).getPrice(),prodList.get(i),obj);
			OrderProductsDao oPDao=new OrderProductsDao();
			oPDao.addOrderProducts(op);
		}
	}
	
	public List<Product> getProducts(int orderId) {
		List<Product> prodList=new ArrayList<Product>();
		OrderProductsDao oPDao=new OrderProductsDao();
		List<OrderProducts> list=oPDao.getOrderProducts(orderId);
		ProductsDao pDao=new ProductsDao();
		Product prod=new Product();
		for(OrderProducts op:list){
			prod=pDao.getProduct(op.getProduct().getProductId());
			prod.setQuantity(op.getQuantity());
			prodList.add(prod);
		}
        return prodList;	
	}
	
	public List<List<Product>> getAllProd(String loginId){
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();	
		Query q1 = session.createQuery("from Orders o where o.user.loginId=:loginId");
		q1.setParameter("loginId" ,loginId);
		List<Orders> orderList = q1.list();
		session.close();
		List<List<Product>> prodList = new ArrayList<List<Product>>();
		for(Orders order:orderList){
			prodList.add(getProducts(order.getOrderId()));
		}
		return prodList;
	}
	
	
	
	public List<Orders> getAllOrders(String loginId){
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();	
		Query q1 = session.createQuery("from Orders o where o.user.loginId=:loginId");
		q1.setParameter("loginId" ,loginId);
		List<Orders> orderList = q1.list();
		List<Orders> tempList=new ArrayList<Orders>();
		Orders ord;
		for(int i=0;i<orderList.size();i++){
			ord=new Orders(orderList.get(i).getOrderId(),orderList.get(i).getPrice(),orderList.get(i).getOrderDate(),orderList.get(i).getDeliveryDate(),orderList.get(i).getStatus());
			System.out.println(ord);
			tempList.add(ord);
		}
		session.close();
		return tempList;
		
	}
	public void deleteOrder(int orderId) {
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		Query q1 = session.createQuery("update Orders o set o.status='Cancelled' where o.orderId=:orderId");
		q1.setParameter("orderId", orderId);
		q1.executeUpdate();
		
		Transaction tx = session.beginTransaction();
		tx.commit(); //permanent save
		session.close();
	}
	
	public void updateOrder(Orders order,OrderProducts op) {
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
        session.update(order);
		OrderProductsDao oPDao=new OrderProductsDao();
		oPDao.updateOrder(op);
		tx.commit();
		session.close();		
	}
}
