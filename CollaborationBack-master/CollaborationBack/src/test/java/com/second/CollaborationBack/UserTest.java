package com.second.CollaborationBack;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.second.CollaborationBack.DAO.UserDAO;
import com.second.CollaborationBack.model.User;

public class UserTest {
	public static void main(String[] args) {

		System.out.println("hai");
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		System.out.println("hai");
		context.scan("com.second.*");
		System.out.println("hai");
	
		context.refresh();
			
		
		System.out.println("hello");
		
		UserDAO userDAO = (UserDAO) context.getBean("UserDAO");
		
		
		User user = (User) context.getBean("user");
		
		
	
		
	}
	
	
}
