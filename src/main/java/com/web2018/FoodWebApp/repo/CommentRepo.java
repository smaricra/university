package com.web2018.FoodWebApp.repo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.simpleframework.xml.ElementList;

import com.web2018.FoodWebApp.model.Comment;

@XmlRootElement(name="root")
public class CommentRepo {
	
	@ElementList(required=false)
	private List<Comment> comments;
	
	public CommentRepo() {
		comments = new ArrayList<>();
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

}
