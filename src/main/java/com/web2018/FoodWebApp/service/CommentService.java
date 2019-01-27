package com.web2018.FoodWebApp.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import com.web2018.FoodWebApp.model.Comment;
import com.web2018.FoodWebApp.model.Restaurant;
import com.web2018.FoodWebApp.repo.CommentRepo;

public class CommentService {
	
	private static CommentRepo commentRepo;
	
	@Context 
	private static ServletContext servletContext;

	private static Serializer serializer = new Persister();
	private static String tempUrl = "C:/Users/Sava/Documents/Projects/Fax/FoodWebApp/src/main/resources/data/comments.xml";
	//private static URL userURL = UserService.class.getResource("/data/comments.xml");
	private static String fixedUrl;
	
	public CommentService() {
		System.out.println("COMMENT 1");
		try {
			//fixedUrl = userURL.toString().replaceAll("file:/", "");
			//File file = new File(fixedUrl);
			File file = new File(tempUrl);
			commentRepo = serializer.read(CommentRepo.class, file);
		} catch (Exception e) {
			commentRepo = new CommentRepo();
		}

	}
	
	public void add(Comment comment) {
		comment.setId((long) commentRepo.getComments().size());
		comment.setActive(true);
		commentRepo.getComments().add(comment);
		updateData();
	}
	
	public void update(Comment comment) {
		for(int i=0; i<commentRepo.getComments().size(); i++) {
			if(commentRepo.getComments().get(i).getId()==comment.getId()) {
				commentRepo.getComments().set(i, comment);
				updateData();
				return;
			}
		}
	}
	
	public void delete(Long id) {
		for(int i=0; i<commentRepo.getComments().size(); i++) {
			if(commentRepo.getComments().get(i).getId()==id) {
				commentRepo.getComments().get(i).setActive(false);
				updateData();
				return;
			}
		}
	}
	
	public Comment getById(Long id) {
		for(int i=0; i<commentRepo.getComments().size(); i++) {
			if(commentRepo.getComments().get(i).getId()==id) {
				return commentRepo.getComments().get(i);
			}
		}
		return null;
	}
	
	public List<Comment> getAll() {
		List<Comment> ret = new ArrayList<>();
		commentRepo.getComments().forEach(c-> {
			if(c.isActive()) ret.add(c);
		});
		return ret;
	}
	
	public List<Comment> getByUser(Long userId) {
		List<Comment> ret = new ArrayList<>();
		commentRepo.getComments().forEach(c-> {
			if(c.isActive() && c.getUserId()==userId) ret.add(c);
		});
		return ret;
	}
	
	public List<Comment> getByOrder(Long orderId) {
		List<Comment> ret = new ArrayList<>();
		commentRepo.getComments().forEach(c-> {
			if(c.isActive() && c.getOrderId()==orderId) ret.add(c);
		});
		return ret;
	}
	
	private static void updateData() {
		try {
			//File file = new File(fixedUrl);
			File file = new File(tempUrl);
			serializer.write(commentRepo, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
