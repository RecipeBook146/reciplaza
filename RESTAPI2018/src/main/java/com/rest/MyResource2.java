package com.rest;


import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import com.dao.*;
import com.dto.*;
@Path("myresource2")
public class MyResource2 {

	@Path("getAllUsers")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public void getAllUsers(){
		UserDao dao=new UserDao();
		List<User> list = dao.getAllUsers();
		System.out.println(list);
		String dec;
		for(User u:list){
			dec="";
			for(String str:u.getPassword().split("[a-zA-Z]+")){
				if(str.length()>0)
					dec+=(char)Integer.parseInt(str);
				u.setPassword(dec);
			}
		}
		System.out.println(list);
	}

	@Path("orderDet")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public void orderDet(){
		List<Product> products=new ArrayList<Product>();
		products.add(new Product(1,"masala","it is masala",11,12.3,"MTR","xyz"));
		products.add(new Product(1,"masala","it is masala",12,12.3,"MTR","xyz"));
		products.add(new Product(1,"masala","it is masala",15,12.3,"MTR","xyz"));
		StringBuilder b = new StringBuilder();
		int i=1;
		/*for(Product product : products){
			b.append(i+". "+product.getManufacturer()+" "+product.getProdutName()+" - "+product.getPrice()+" x "+product.getQuantity()+"\n").append("\n");
			i++;
		}
		String prodsString = b.toString();*/
		System.out.println("Preparing to send mail!!");
		//System.out.println(otp);
		String recipient="fmail1356@gmail.com";
		Properties properties=new Properties();
		properties.put("mail.smtp.auth","true");
		properties.put("mail.smtp.starttls.enable","true");
		properties.put("mail.smtp.host","smtp.gmail.com");
		properties.put("mail.smtp.port","587");
		final String myAccountEmail="recipebook146@gmail.com";
		final String password="Recipe@123";
		Session session=Session.getInstance(properties,new Authenticator(){
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myAccountEmail, password);
			}
		});
		try {
			Message message=new MimeMessage(session);
			message.setFrom(new InternetAddress(myAccountEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			message.setSubject("sample mail");
			String htmlCode="<h1>Order Confirmed!</h1><h2>Products:</h2><h3>";
			for(Product product : products){
				htmlCode += i+". "+product.getManufacturer()+" "+product.getProdutName()+" - "+product.getPrice()+" x "+product.getQuantity()+"<br/>";
				i++;
			}
			htmlCode+="</h3>";
			message.setContent(htmlCode,"text/html");
			Transport.send(message);
			System.out.println("Mail Sent Successfully!");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	@Path("sendMail")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public void sendMail(){
		int otp=1234;
		System.out.println("Preparing to send mail!!");
		String recipient="18wh1a0560@bvrithyderabad.edu.in";
		Properties properties=new Properties();
		properties.put("mail.smtp.auth","true");
		properties.put("mail.smtp.starttls.enable","true");
		properties.put("mail.smtp.host","smtp.gmail.com");
		properties.put("mail.smtp.port","587");
		final String myAccountEmail="recipebook146@gmail.com";
		final String password="Recipe@123";
		Session session=Session.getInstance(properties,new Authenticator(){
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myAccountEmail, password);
			}
		});
		try {
			Message message=new MimeMessage(session);
			message.setFrom(new InternetAddress(myAccountEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			message.setSubject("sample mail");
			String htmlCode="<h1 style='color:orange;'>Demo Mail</h1>";
			message.setContent(htmlCode,"text/html");
			Transport.send(message);
			System.out.println("Mail Sent Successfully!");
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	@Path("hello")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt() {
		return "Got it!";
	}


	@Path("deleteOrder/{orderId}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public void deleteOrder(@PathParam("orderId") int orderId){
		System.out.println("Data Recieved in delete : " + orderId);
		OrdersDao dao = new OrdersDao();
		dao.deleteOrder(orderId);

	}	

	@Path("getOrders/{loginId}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public void getOrders(@PathParam("loginId") String loginId){
		OrdersDao ordersDao=new OrdersDao();
		List<Orders> orderList=ordersDao.getAllOrders(loginId);
		System.out.println(orderList.size());
		for(Orders o:orderList){
			System.out.println(o);
		}
	}

	@Path("getAllProd/{loginId}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)	
	public void getAllProd(@PathParam("loginId") String loginId){
		OrdersDao dao=new OrdersDao();
		System.out.println(dao.getAllProd(loginId));
	}

	@Path("registerUser")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String registerUser(){
		User u=new User("user1","user@gmail.com","user1");
		UserDao daoH=new UserDao();
		daoH.register(u);
		return "Success";
	}

	@Path("addAddress")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public void addAddress(){
		User u=new User("anju123","","");
		Address address=new Address(1,"hno","street","city","state","landmark","000000",u);
		AddressDao daoH=new AddressDao();
		daoH.addAddress(address);
	}

	@Path("verifyLogin/{loginId}&{password}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public User verifyLogin(@PathParam("loginId") String loginId,@PathParam("password") String password){
		System.out.println(loginId+" "+password);
		UserDao daoH=new UserDao();
		User user=daoH.verifyLogin(loginId);
		return user;
	}

	/* @Path("addProduct")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String addProduct() {
    	Product prod=new Product(1,"masala","it is masala",12,12.3,"MTR");
    	ProductsDao daoH=new ProductsDao();
    	daoH.addProduct(prod);
    	return "Success";
    }*/

	/*@Path("addRecipe")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String addRecipe() {
    	User u=new User("user1","user@gmail.com","user1");
    	Recipe recipe=new Recipe(1,"Tea","Beverge","5min",2,"Tasty Tea","Water-185ml,Milk-125ml,Sugar-2tsp,Tea Powder-2tsp","Boil the water.Place the Tea Powder and water in a teapot....","no tips",2,2,u);
    	RecipesDao daoH=new RecipesDao();
    	daoH.addRecipe(recipe);
    	//System.out.println(daoH.getRating(5));
    	return "Success";
    }*/
	@Path("addAddress1")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String addAddress1() {
		User u=new User("user1","user@gmail.com","user1");
		Address address=new Address(1,"13-5-1/A","XYZ","XYZ","XYZ","XYZ","000000",u);
		AddressDao daoH=new AddressDao();
		daoH.addAddress(address);
		List<Address> addressList=daoH.getAddress("user1");
		System.out.println(addressList);
		return "Success";
	}



	/*@Path("addOrder")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    //public void addOrder(Order order,OrderProducts obj)
    public String addOrder() {
    	User u=new User("user1","user@gmail.com","user1");
    	Orders order=new Orders(1,12.3,new Date("1/21/14"),null,"Not Delivered",u);
    	Product prod=new Product(1,"masala","it is masala",12,12.3,"MTR");
    	OrderProducts obj=new OrderProducts(2,24.6,prod,order);
    	OrdersDao daoH=new OrdersDao();
    	daoH.addOrder(order,obj);
    	return "Success";
    }*/
	/* @Path("addToCart")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String addToCart() {
    	User u=new User("user1","user@gmail.com","user1");
    	Product prod=new Product(1,"masala","it is masala",12,12.3,"MTR");
    	Orders order=new Orders(1,12.3,new Date("1/21/14"),null,"Not Delivered",u);
    	Cart c=new Cart(1,1,12.3,u,prod);
    	CartDao daoH=new CartDao();
    	daoH.addToCart(c);
    	return "Success";
    }*/
	@Path("deleteFromCart")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteFromCart() {
		CartDao daoH=new CartDao();
		daoH.deleteItem(1, "user1");
		return "Success";
	}

	@Path("getAllRecipes")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public void getAllRecipes(){
		RecipesDao daoH = new RecipesDao();
		List<Recipe> recipeList = daoH.getAllRecipes();	
		System.out.println(recipeList);	
	}

	@Path("getFavRecipes")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public void getFavRecipes(){
		String loginId="user1";
		FavListDao daoH=new FavListDao();
		//List<FavList> favList = daoH.getFavRecipes(loginId);
		List<Integer> recIdList=daoH.getFavRecipes(loginId);
		System.out.println("Success"+recIdList);
		/*for(FavList fav:favList){
			recIdList.add(fav.getId());
		}*/
	}
	@Path("getProducts/{desc}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getProducts(@PathParam("desc") String desc){
		ProductsDao dao = new ProductsDao();
		Set<Product> prodList=dao.getProducts(desc);
		System.out.println(prodList);
		return "b";
	}

	/* @Path("getCount/{recipeId}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public void getCount(@PathParam("recipeId") int recipeId){
    	RatingDao dao=new RatingDao();
    	System.out.println(dao.ratingCount(recipeId));
    	User u=new User("user2","","");
    	Recipe recipe=new Recipe(28);
    	Rating rating=new Rating(1,4,u,recipe);
    	dao.addRating(rating);
    	System.out.println("success!!");
    }*/

	@Path("getQuan")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public void getQuan(){
		String loginId="anju123";
		CartDao cartDao=new CartDao();
		System.out.println(cartDao.getQuan(loginId));
	}
	@Path("getRqsts/{loginId}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public void getRqsts(@PathParam("loginId") String loginId){
		CookRequestsDao dao=new CookRequestsDao();
		System.out.println(dao.getRqsts(loginId));
	}

	@Path("getAsks/{loginId}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public void getAsks(@PathParam("loginId") String loginId){
		CookRequestsDao dao=new CookRequestsDao();
		System.out.println(dao.getAsks(loginId));
	}
}
