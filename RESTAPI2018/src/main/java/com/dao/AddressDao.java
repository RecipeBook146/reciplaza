package com.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import com.dto.Address;

public class AddressDao {
	public void addAddress(Address address) {
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		session.save(address); //insert query
		Transaction tx = session.beginTransaction();
		tx.commit(); //permanent save
		session.close();
	}
	public List<Address> getAddress(String loginId) {
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();	
		Query q1 = session.createQuery("from Address a where a.user.loginId=:loginId");
		q1.setParameter("loginId" ,loginId);
		List<Address> addressList = q1.list();
		session.close();
		return addressList;
	}
	
	public Address getAddressById(int chosenId) {
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();	
		Query q1 = session.createQuery("from Address a where a.Id=:chosenId");
		q1.setParameter("chosenId" ,chosenId);
		List<Address> addressList = q1.list();
		session.close();
		return addressList.get(0);
	}

	public void deleteAddress(int id) {
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		Query q1 = session.createQuery("delete from Address a where a.id=:id");
		q1.setParameter("id", id);
		q1.executeUpdate();
		Transaction tx = session.beginTransaction();
		tx.commit(); //permanent save
		session.close();
	}

	public void updateAddress(Address address) {
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(address);
		tx.commit();
		session.close();		
	}	
}
