package com.web2018.FoodWebApp.core;

import com.web2018.FoodWebApp.model.OrderProduct;
import com.web2018.FoodWebApp.model.Product;
import com.web2018.FoodWebApp.service.OrderProductService;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//    	EmailValidator emailValidator = new EmailValidator();
//    	Boolean q1 = emailValidator.validateEmail("sava.maric@hotmail.com");
//    	Boolean q2 = emailValidator.validateEmail("cdfdf@hotmail.com");
//    	Boolean q3 = emailValidator.validateEmail("sada@sa.sdsd");
//    	Boolean q4 = emailValidator.validateEmail("sadsda");
//    	Boolean q5 = emailValidator.validateEmail("sadsda@sdsd.sdsd.sds");
//    	
//    	if(!q1) System.out.println("1");
//    	if(!q2) System.out.println("2");
//    	if(!q3) System.out.println("3");
//    	if(!q4) System.out.println("4");
//    	if(!q5) System.out.println("5");
//    	
//    	System.out.println(q1);
//    	System.out.println(q2);
//    	System.out.println(q3);
//    	System.out.println(q4);
//    	System.out.println(q5);
		
		OrderProductService ops = new OrderProductService();
		ops.getAll();
		
		System.out.println("***********");
		for(Product pr : ops.getAll()) {
			System.out.println(pr.getId());
		}
			

	}

}
