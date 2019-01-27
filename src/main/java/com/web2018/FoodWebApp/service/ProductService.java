package com.web2018.FoodWebApp.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import com.web2018.FoodWebApp.core.Constants.PRODUCT_TYPE;
import com.web2018.FoodWebApp.model.OrderProduct;
import com.web2018.FoodWebApp.model.Product;
import com.web2018.FoodWebApp.model.Restaurant;
import com.web2018.FoodWebApp.repo.ProductRepo;

public class ProductService {
	
	private static ProductRepo productRepo;
	
	@Context 
	private static ServletContext servletContext;

	private static Serializer serializer = new Persister();
	private static String tempUrl = "C:/Users/Sava/Documents/Projects/Fax/FoodWebApp/src/main/resources/data/products.xml";
	//private static URL userURL = UserService.class.getResource("/data/products.xml");
	private static String fixedUrl;
	
	public ProductService() {
		System.out.println("PRODUCT 5");
		try {
			//fixedUrl = userURL.toString().replaceAll("file:/", "");
			//File file = new File(fixedUrl);
			File file = new File(tempUrl);
			productRepo = serializer.read(ProductRepo.class, file);
		} catch (Exception e) {
			productRepo = new ProductRepo();
		}

	}
	
	public Long add(Product product) {
		product.setId((long) productRepo.getProducts().size());
		product.setActive(true);
		productRepo.getProducts().add(product);
		updateData();
		return product.getId();
	}
	
	public void update(Product product) {
		for(int i=0; i<productRepo.getProducts().size(); i++) {
			if(productRepo.getProducts().get(i).getId()==product.getId()) {
				productRepo.getProducts().set(i, product);
				updateData();
			}
		}
	}
	
	public void delete(Long id) {
		for(int i=0; i<productRepo.getProducts().size(); i++) {
			if(productRepo.getProducts().get(i).getId()==id) {
				productRepo.getProducts().get(i).setActive(false);
				updateData();
			}
		}
	}
	
	public Product getById(Long id) {
		for(int i=0; i<productRepo.getProducts().size(); i++) {
			if(productRepo.getProducts().get(i).getId()==id) {
				return productRepo.getProducts().get(i);
			}
		}
		return null;
	}
	
	public List<Product> getAll() {
		List<Product> ret = new ArrayList<>();
		productRepo.getProducts().forEach(p-> {
			if(p.isActive()) ret.add(p);
		});
		return ret;
	}
	
	public List<Product> getByRestaurant(Long retaurantId, PRODUCT_TYPE type) {
		List<Product> ret = new ArrayList<>();
		for(Product p : productRepo.getProducts()) {
			if(p.getRestaurantId()==retaurantId) {
				if(type!=null && p.getType()==type) ret.add(p);
				else if(type==null) ret.add(p);
			}
		}
		return ret;
	}
	
	public List<Product> filter(String name, Long restaurantId, PRODUCT_TYPE type, double minPrice, double maxPrice) {
		List<Product> ret = new ArrayList<>();
		for(Product p : productRepo.getProducts()) {
			if(p.getName().contains(name) && p.getPrice()>=minPrice && p.getPrice()<=maxPrice) {
				if(restaurantId!=null && type!=null) {
					if(p.getRestaurantId()==restaurantId && p.getType()==type) ret.add(p);				
				} else if(restaurantId!=null) {
					if(p.getRestaurantId()==restaurantId) ret.add(p);			
				} else if(type!=null) {
					if(p.getType()==type) ret.add(p);	
				} else {
					ret.add(p);	
				}
			}
		}
		return ret;
	}
	
	private static void updateData() {
		try {
			//File file = new File(fixedUrl);
			File file = new File(tempUrl);
			serializer.write(productRepo, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
