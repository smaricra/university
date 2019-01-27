package com.web2018.FoodWebApp.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import com.web2018.FoodWebApp.core.AppListener;
import com.web2018.FoodWebApp.model.OrderProduct;
import com.web2018.FoodWebApp.model.Product;
import com.web2018.FoodWebApp.repo.OrderProductRepo;

public class OrderProductService {
	
	private static OrderProductRepo orderProductRepo;
	
	@Context 
	private static ServletContext servletContext;

	private static Serializer serializer = new Persister();
	private static String tempUrl = "C:/Users/Sava/Documents/Projects/Fax/FoodWebApp/src/main/resources/data/order_products.xml";
	//private static URL userURL = UserService.class.getResource("/data/order_products.xml");
	private static String fixedUrl;
	
	public OrderProductService() {
		System.out.println("ORDER_PRODUCT 3");
		try {
			//fixedUrl = userURL.toString().replaceAll("file:/", "");
			//File file = new File(fixedUrl);
			File file = new File(tempUrl);
			orderProductRepo = serializer.read(OrderProductRepo.class, file);
		} catch (Exception e) {
			orderProductRepo = new OrderProductRepo();
		}

	}
	
	public void add(OrderProduct orderProduct) {
		orderProduct.setId((long) orderProductRepo.getOrderProducts().size());
		orderProduct.setActive(true);
		orderProductRepo.getOrderProducts().add(orderProduct);
		updateData();
	}
	
	public void add(List<OrderProduct> orderProducts) {
		for(OrderProduct orderProduct : orderProducts) {
			add(orderProduct);
		}
	}
	
	public void delete(Long id) {
		for(int i=0; i<orderProductRepo.getOrderProducts().size(); i++) {
			if(orderProductRepo.getOrderProducts().get(i).getId()==id) {
				orderProductRepo.getOrderProducts().get(i).setActive(false);
				updateData();
			}
		}
	}
	
	public List<Product> getProductsByOrderId(Long orderId) {
		List<Long> productIds = new ArrayList<>();
		orderProductRepo.getOrderProducts().forEach(op-> {
			if(op.isActive() && op.getOrderId()==orderId) productIds.add(op.getProductId());
		});
		List<Product> ret = new ArrayList<>();
		productIds.forEach(pId -> {
			ret.add(AppListener.productService.getById(pId));
		});
		return ret;
	}
	
	public List<Product> getAll() {
		Map<Long, Integer> bestProducts = new HashMap<>();
		orderProductRepo.getOrderProducts().forEach(op -> {
			if(bestProducts.containsKey(op.getProductId())) {
				Integer count = bestProducts.get(op.getProductId())+1;
				bestProducts.replace(op.getProductId(), count);
			} else {
				bestProducts.put(op.getProductId(), 1);
			}
		});
		Object[] a = bestProducts.entrySet().toArray();
		Arrays.sort(a, new Comparator() {
		    public int compare(Object o1, Object o2) {
		        return ((Map.Entry<Long, Integer>) o2).getValue()
		                   .compareTo(((Map.Entry<Long, Integer>) o1).getValue());
		    }
		});
		List<Product> best10Products = new ArrayList<>();
		ProductService ps = new ProductService();
		for (Object e : a) {
			Long productId = ((Map.Entry<Long, Integer>) e).getKey();
		    System.out.println(productId + " : "
		            + ((Map.Entry<Long, Integer>) e).getValue() + " orders");
		    if(best10Products.size()<10) {
		    	best10Products.add(AppListener.productService.getById(productId));
		    }
		}
		if(best10Products.size()<10) {
			List<Product> products = AppListener.productService.getAll();
			for(int i = best10Products.size(); i<=10; i++) {
				for(int j = 0; j<products.size(); j++) {
					if(!best10Products.contains(products.get(j))) {
						best10Products.add(products.get(j));
					}
				}
			}
		}
		return best10Products;
	}
	
	private static void updateData() {
		try {
			//File file = new File(fixedUrl);
			File file = new File(tempUrl);
			serializer.write(orderProductRepo, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
