package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.dto.FavList;

public class FavListDao {
	public void addToFav(FavList favList) {
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		session.save(favList); //insert query
		Transaction tx = session.beginTransaction();
		tx.commit(); //permanent save
		session.close();
	}
	public List<Integer> getFavRecipes(String loginId) {
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();	
		Query q1 = session.createQuery("from FavList f where f.user.loginId=:loginId");
		q1.setParameter("loginId" ,loginId);
		List<FavList> favList = q1.list();
		List<Integer> recIdList=new ArrayList<Integer>();
		for(FavList fav:favList){
			recIdList.add(fav.getRecipe().getRecipeId());
		}
		session.close();
		return recIdList;
	}
	
	public void deleteFav(int recipeId,String loginId) {
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		Query q1 = session.createQuery("delete from FavList f where f.user.loginId=:loginId and f.recipe.recipeId=:recipeId");
		q1.setParameter("loginId", loginId);
		q1.setParameter("recipeId", recipeId);
		q1.executeUpdate();
		Transaction tx = session.beginTransaction();
		tx.commit(); //permanent save
		session.close();
	}

}
