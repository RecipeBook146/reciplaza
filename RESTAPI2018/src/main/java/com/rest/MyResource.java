package com.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.dao.*;
import com.dto.*;

@Path("myresource")
public class MyResource {
	@Path("hello")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt() {
		return "Got it!";
	}

	@Path("deleteProduct/{productId}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteProduct(@PathParam("productId") int productId){
		System.out.println("Data Recieved in delete : " + productId);
		ProductsDao prodDao = new ProductsDao();
		prodDao.deleteProduct(productId);

	}	

	@Path("deleteFav/{recipeId}/{loginId}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteFav(@PathParam("recipeId") int recipeId,@PathParam("loginId") String loginId){
		System.out.println("Data Recieved in delete : " + recipeId+" "+loginId);
		FavListDao favDao = new FavListDao();
		favDao.deleteFav(recipeId,loginId);

	}	

	@Path("updateProduct")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateProduct(Product product){
		System.out.println("Data Recieved in update : " + product); 
		//int x=EmployeeDao.updateRecord(employee);
		ProductsDao prodDao=new ProductsDao();
		prodDao.updateProduct(product);
	}	

	@Path("registerUser")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void registerUser(User u){
		String text=u.getPassword();
		String enc="";
		for(int i=0;i<text.length();i++){
			enc+=((int)text.charAt(i))+""+(char)(97+i);
		}
		u.setPassword(enc);
		UserDao daoH=new UserDao();
		daoH.register(u);
	}

	@Path("signInWithGoogle")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public User signInWithGoogle(User u){
		UserDao daoH=new UserDao();
		User user=daoH.verifyEmail(u.getEmail());
		if(user==null){
			daoH.register(u);
			return null;
		}
		return user;
	}

	@Path("addAddress")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void addAddress(Address address){
		AddressDao daoH=new AddressDao();
		daoH.addAddress(address);
	}

	@Path("addRqst")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void addRqst(CookRequests obj){
		Properties properties=new Properties();
		//CookRequests c = new CookRequests(1,1,new Date(0),new java.sql.Time(100,90,1),"","","","",1);
		System.out.println(obj);
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
			UserDao uDao=new UserDao();
			RecipesDao rdao = new RecipesDao();
			message.setFrom(new InternetAddress(myAccountEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(uDao.verifyLogin(obj.getUser1().getLoginId()).getEmail()));
			message.setSubject("Request");
			String htmlCode=obj.getUser().getLoginId()+" has requested you to cook "+rdao.getRecipe(obj.getRecipe().getRecipeId()).getTitle();
			message.setContent(htmlCode,"text/html");
			Transport.send(message);
			System.out.println("Mail Sent Successfully!");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		CookRequestsDao cDao = new CookRequestsDao();
		cDao.add(obj);
	}

	@Path("deleteAddress/{id}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteAddress(@PathParam("id") int id){
		System.out.println("Data Recieved in delete : " + id);
		AddressDao dao = new AddressDao();
		dao.deleteAddress(id);

	}	

	@Path("deleteOrder/{orderId}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteOrder(@PathParam("orderId") int orderId){
		System.out.println("Data Recieved in delete : " + orderId);
		OrdersDao dao = new OrdersDao();
		dao.deleteOrder(orderId);

	}	


	@Path("updateAddress")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateAddress(Address address){
		System.out.println("Data Recieved in update : " + address); 
		AddressDao dao=new AddressDao();
		dao.updateAddress(address);
	}	

	@Path("addToCart")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public CookRequests addToCart(Cart cart){
		CookRequests d=new CookRequests();
		CartDao daoH=new CartDao();
		d.setMessage(daoH.addToCart(cart));
		return d;
	}

	@Path("deleteItem/{loginId}/{productId}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteItem(@PathParam("loginId") String loginId,@PathParam("productId") int productId){
		CartDao cartDao=new CartDao();
		cartDao.deleteItem(productId, loginId);
	}


	@Path("addOrder/{loginId}/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String addOrder(@PathParam("loginId") String loginId,@PathParam("id") int id){
		UserDao obj=new UserDao();
		CartDao cDao=new CartDao();
		User u=obj.verifyLogin(loginId);
		List<Product> products=cDao.getcartItems(loginId);
		orderDet(u.getEmail(), loginId, products);
		OrdersDao dao=new OrdersDao();
		dao.addOrder(loginId, id);
		return "Inserted";
	}

	@Path("sendMail/{email}/{loginId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public int sendMail(@PathParam("email") String recipient,@PathParam("loginId") String loginId){
		String numbers = "1234567890";
		Random random = new Random();
		char[] otpArr = new char[4];
		for(int i = 0; i< 4 ; i++) {
			otpArr[i] = numbers.charAt(random.nextInt(numbers.length()));
		}
		int otp=Integer.parseInt(new String(otpArr));
		System.out.println("Preparing to send mail!!");
		System.out.println(otp);
		//String recipient="18wh1a0560@bvrithyderabad.edu.in";
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
			String htmlCode="<div style='background-color: #ffccff;width: 100%;'><h2 style='color:#AE388B;margin-left:20px;'>  Hey! "+loginId+", Thanks for Registering with us!! :)</h2><br/><h1 style='margin-left:20px;'>  Your OTP: "+otp+"</h1><h2 style='color:tomato;margin-left:20px;'>  Enjoy the recipes for a delighted tongue!! :)</h2></div>";
			message.setContent(htmlCode,"text/html");
			Transport.send(message);
			System.out.println("Mail Sent Successfully!");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return otp;
	}


	public void orderDet(String recipient,String loginId,List<Product> products){

		System.out.println("Preparing to send mail!!");
		int i=1;
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
			String htmlCode="<h1>Hey, "+loginId+"! Order Confirmed!</h1><h2>Products:</h2><h3>";
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

	@Path("getQuan/{loginId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Integer> getQuan(@PathParam("loginId") String loginId){
		CartDao cartDao=new CartDao();
		return cartDao.getQuan(loginId);
	}


	@Path("getRqsts/{loginId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<CookRequests> getRqsts(@PathParam("loginId") String loginId){
		CookRequestsDao dao=new CookRequestsDao();
		return dao.getRqsts(loginId);
	}

	@Path("getAsks/{loginId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<CookRequests> getAsks(@PathParam("loginId") String loginId){
		CookRequestsDao dao=new CookRequestsDao();
		return dao.getAsks(loginId);
	}

	@Path("getOrders/{loginId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Orders> getOrders(@PathParam("loginId") String loginId){
		OrdersDao ordersDao=new OrdersDao();
		return ordersDao.getAllOrders(loginId);
	}

	@Path("getAllProd/{loginId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<List<Product>> getAllProd(@PathParam("loginId") String loginId){
		OrdersDao dao=new OrdersDao();
		return dao.getAllProd(loginId);
	}

	@Path("getTot/{loginId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public double getTot(@PathParam("loginId") String loginId){
		CartDao cartDao=new CartDao();
		return cartDao.getTot(loginId);
	}

	@Path("getAddress/{loginId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Address> getAddress(@PathParam("loginId") String loginId){
		AddressDao dao=new AddressDao();
		return dao.getAddress(loginId);
	}

	@Path("getAddressById/{chosenId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Address getAddressById(@PathParam("chosenId") int chosenId){
		AddressDao dao=new AddressDao();
		return dao.getAddressById(chosenId);
	}

	@Path("updateCart/{quan}/{loginId}/{productId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public void updateCart(@PathParam("quan")int quan, @PathParam("loginId") String loginId,@PathParam("productId") int productId){
		CartDao cartDao=new CartDao();
		cartDao.updateCart(quan, productId, loginId);
	}

	@Path("verifyLogin/{loginId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public User verifyLogin(@PathParam("loginId") String loginId){
		System.out.println(loginId);
		UserDao daoH=new UserDao();
		User user=daoH.verifyLogin(loginId);
		String dec="";
		for(String str:user.getPassword().split("[a-zA-Z]+")){
			if(str.length()>0)
				dec+=(char)Integer.parseInt(str);
		}
		user.setPassword(dec);
		return user;
	}

	@Path("allRecipe")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Recipe> allRecipe(){
		RecipesDao recDao = new RecipesDao();
		List<Recipe> recList = recDao.getAllRecipes();	
		return recList;	
	}

	@Path("getAllUsers")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAllUsers(){
		UserDao userDao = new UserDao();
		List<User> userList = userDao.getAllUsers();	
		return userList;	
	}

	@Path("addToFav")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void addToFav(FavList fav) {
		System.out.println(fav);
		FavListDao daoH=new FavListDao();
		daoH.addToFav(fav);
	}

	@Path("getCartProducts/{loginId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getCartProducts(@PathParam("loginId") String loginId){
		System.out.println(loginId);
		CartDao daoH=new CartDao();
		List<Product> prodList=daoH.getcartItems(loginId);
		return prodList;	
	}

	@Path("getFavRecipes/{loginId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Integer> getFavRecipes(@PathParam("loginId") String loginId){
		System.out.println(loginId);
		FavListDao daoH=new FavListDao();
		List<Integer> recIdList=daoH.getFavRecipes(loginId);
		return recIdList;	
	}



	@Path("getUploadedRecipes/{loginId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Recipe> getUploadedRecipes(@PathParam("loginId") String loginId){
		System.out.println(loginId);
		RecipesDao daoH=new RecipesDao();
		List<Recipe> recipes=daoH.getUploadedRecipes(loginId);
		return recipes;	
	}

	@Path("getProducts/{desc}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Set<Product> getProducts(@PathParam("desc") String desc){
		ProductsDao dao = new ProductsDao();
		Set<Product> prodList=dao.getProducts(desc);
		return prodList;
	}

	@Path("getAllProducts")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getAllProducts(){
		ProductsDao dao = new ProductsDao();
		List<Product> prodList=dao.getAllProducts();
		return prodList;
	}

	@Path("getCount/{recipeId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public int getCount(@PathParam("recipeId") int recipeId){
		System.out.println(recipeId);
		RatingDao dao=new RatingDao();
		return dao.ratingCount(recipeId);
	}

	@Path("getStat/{recipeId}/{loginId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public boolean getStat(@PathParam("recipeId") int recipeId,@PathParam("loginId") String loginId){
		System.out.println(recipeId);
		RatingDao dao=new RatingDao();
		return dao.getStat(recipeId,loginId);
	}

	@Path("addToRating")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void addToRating(Rating rating){
		System.out.println(rating);
		RatingDao dao=new RatingDao();
		dao.addRating(rating);
	}

	@Path("/uploadImage")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public void uploadImage(@FormDataParam("Image") InputStream fileInputStream,@FormDataParam("Image") FormDataContentDisposition formDataContentDisposition,@FormDataParam("demo") String demo){
		int read=0;
		byte[] bytes =new byte[1024];
		String path=this.getClass().getClassLoader().getResource("").getPath();
		String pathArr[]=path.split("/WEB-INF/classes/");
		FileOutputStream out;
		try {
			out = new FileOutputStream(new File(pathArr[0]+"/image/",formDataContentDisposition.getFileName()));
			while((read=fileInputStream.read(bytes))!=-1){
				out.write(bytes,0,read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Images img=new Images();
		img.setId(1);
		img.setDemo(demo);
		img.setImageName(formDataContentDisposition.getFileName());
		System.out.println(img);
		//EmployeeDaoH daoh=new EmployeeDaoH();
		//daoh.imageDemo(img);

	}

	@Path("addProduct")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public void addProduct(@FormDataParam("Image") InputStream fileInputStream,@FormDataParam("Image") FormDataContentDisposition formDataContentDisposition,
			@FormDataParam("produtName") String produtName,@FormDataParam("description") String description,@FormDataParam("quantity") int quantity,@FormDataParam("price") double price,@FormDataParam("manufacturer") String manufacturer) {
		int read=0;
		byte[] bytes =new byte[1024];
		String path=this.getClass().getClassLoader().getResource("").getPath();
		String pathArr[]=path.split("/WEB-INF/classes/");
		FileOutputStream out;
		try {
			out = new FileOutputStream(new File(pathArr[0]+"/image/",formDataContentDisposition.getFileName()));
			while((read=fileInputStream.read(bytes))!=-1){
				out.write(bytes,0,read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Product prod=new Product();
		prod.setProdutName(produtName);
		prod.setDescription(description);
		prod.setQuantity(quantity);
		prod.setPrice(price);
		prod.setManufacturer(manufacturer);
		prod.setImageName(formDataContentDisposition.getFileName());
		System.out.println(prod);
		ProductsDao daoH=new ProductsDao();
		daoH.addProduct(prod);
	}

	@Path("addRecipe")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public void addRecipe(@FormDataParam("Image") InputStream fileInputStream,@FormDataParam("Image") FormDataContentDisposition formDataContentDisposition,
			@FormDataParam("title") String title,@FormDataParam("category") String category,@FormDataParam("cookTime") String cookTime,@FormDataParam("persons") int persons,@FormDataParam("description") String description,@FormDataParam("ingredients") String ingredients,@FormDataParam("proc") String proc,@FormDataParam("tips") String tips,@FormDataParam("stat") String stat,@FormDataParam("price") double price,@FormDataParam("np") int np,@FormDataParam("conf") String conf,@FormDataParam("loginId") String loginId) {

		int read=0;
		byte[] bytes =new byte[1024];
		String path=this.getClass().getClassLoader().getResource("").getPath();
		String pathArr[]=path.split("/WEB-INF/classes/");
		FileOutputStream out;
		try {
			out = new FileOutputStream(new File(pathArr[0]+"/image/",formDataContentDisposition.getFileName()));
			while((read=fileInputStream.read(bytes))!=-1){
				out.write(bytes,0,read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Recipe recipe=new Recipe();
		User u=new User();
		u.setLoginId(loginId);
		recipe.setTitle(title);
		recipe.setCategory(category);
		recipe.setCookTime(cookTime);
		recipe.setPersons(persons);
		recipe.setDescription(description);
		recipe.setIngredients(ingredients);
		recipe.setProc(proc);
		recipe.setTips(tips);
		recipe.setUser(u);
		recipe.setStat(stat);
		recipe.setPrice(price);
		recipe.setNp(np);
		recipe.setConf(conf);
		recipe.setImageName(formDataContentDisposition.getFileName());
		System.out.println(recipe);
		RecipesDao daoH=new RecipesDao();
		daoH.addRecipe(recipe);
	}

	public void sendStat(String recipient,String htmlCode){
		System.out.println("Preparing to send mail!!");
		int i=1;
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
			message.setSubject("RecipeBook");
			
			message.setContent(htmlCode,"text/html");
			Transport.send(message);
			System.out.println("Mail Sent Successfully!");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	@Path("acceptRqst")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public void acceptRqst(CookRequests cookRqst){
		cookRqst.setUser(new User(cookRqst.getUser().getLoginId().split(",")[0]));
		cookRqst.setUser1(new User(cookRqst.getUser1().getLoginId().split(",")[0]));
		cookRqst.setStatus("a");
		CookRequestsDao cookRqstDao=new CookRequestsDao();
		cookRqstDao.update(cookRqst);
		UserDao udao=new UserDao();
		RecipesDao rdao=new RecipesDao();
		String recipient=udao.verifyLogin(cookRqst.getUser().getLoginId()).getEmail();
		String id=udao.verifyLogin(cookRqst.getUser1().getLoginId().split(",")[0]).getEmail();
		String title=rdao.getRecipe(cookRqst.getRecipe().getRecipeId()).getTitle();
		String htmlCode=cookRqst.getUser1().getLoginId()+" has accepted your request to cook "+title+" for the event on "+cookRqst.getDate()+"<br/>You can further contact "+cookRqst.getUser1().getLoginId()+" through "+id;
		sendStat(recipient,htmlCode);
		htmlCode="You have accepted the request to cook "+title+" for the event on "+cookRqst.getDate()+"<br/>You can further contact "+cookRqst.getUser().getLoginId()+" through "+recipient;
		sendStat(id,htmlCode);
	}
	
	@Path("rejectRqst")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public void rejectRqst(CookRequests cookRqst){
		cookRqst.setUser(new User(cookRqst.getUser().getLoginId().split(",")[0]));
		cookRqst.setUser1(new User(cookRqst.getUser1().getLoginId().split(",")[0]));
		cookRqst.setStatus("r");
		CookRequestsDao cookRqstDao=new CookRequestsDao();
		cookRqstDao.update(cookRqst);
		UserDao udao=new UserDao();
		RecipesDao rdao=new RecipesDao();
		String recipient=udao.verifyLogin(cookRqst.getUser().getLoginId()).getEmail();
		//String id=udao.verifyLogin(cookRqst.getRecieverId()).getEmail();
		String title=rdao.getRecipe(cookRqst.getRecipe().getRecipeId()).getTitle();
		String htmlCode=cookRqst.getUser1().getLoginId()+" has rejected yor request to cook "+title+" for the event on "+cookRqst.getDate();
		sendStat(recipient,htmlCode);
	}
	
	@Path("updateAsk")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public void updateAsk(CookRequests cookRqst){
		cookRqst.setUser(new User(cookRqst.getUser().getLoginId().split(",")[0]));
		cookRqst.setUser1(new User(cookRqst.getUser1().getLoginId().split(",")[0]));
		CookRequestsDao cookRqstDao=new CookRequestsDao();
		cookRqstDao.update(cookRqst);
		UserDao udao=new UserDao();
		RecipesDao rdao=new RecipesDao();
		//String recipient=udao.verifyLogin(cookRqst.getRecieverId()).getEmail();
		String recipient=udao.verifyLogin(cookRqst.getUser1().getLoginId().split(",")[0]).getEmail();
		String title=rdao.getRecipe(cookRqst.getRecipe().getRecipeId()).getTitle();
		String htmlCode=cookRqst.getUser().getLoginId()+" has updated their request to cook "+title+" for the event on "+cookRqst.getDate()+".<br/>Please check the updated details by logging into your account.";
		sendStat(recipient,htmlCode);
	}
	
	@Path("deleteAsk")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteAsk(CookRequests cookRqst){
		CookRequestsDao cookRqstDao=new CookRequestsDao();
		cookRqstDao.delete(cookRqst);
		UserDao udao=new UserDao();
		RecipesDao rdao=new RecipesDao();
		//String recipient=udao.verifyLogin(cookRqst.getRecieverId()).getEmail();
		String recipient=udao.verifyLogin(cookRqst.getUser1().getLoginId().split(",")[0]).getEmail();
		String title=rdao.getRecipe(cookRqst.getRecipe().getRecipeId()).getTitle();
		String htmlCode=cookRqst.getUser().getLoginId().split(",")[0]+" has deleted their request to cook "+title+" for the event on "+cookRqst.getDate();
		sendStat(recipient,htmlCode);
	}
}
