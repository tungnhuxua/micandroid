package com.demo;

import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

import coffee.sqlite.Session;

import com.demo.bean.User;

public class Test {
	public static void main(String[] args) {
		
		System.out.println(UUID.randomUUID());
		
		Session session = new Session();
		
		User user = new User();
		user.setPassword("1111");
		user.setUsername("coffee");
		user.setBirthday(new Date());
		
		session.open();
		try {
			session.createTable(User.class);
			//添加记录
			session.insert(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		session.close();
	}
	
	
}
