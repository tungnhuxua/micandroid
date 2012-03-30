package com.test;

import java.sql.SQLException;

import coffee.sqlite.Session;
import coffee.sqlite.TSqliteUtils;

import com.test.bean.User;

public class Test {
	public static void main(String[] args) {
		//生成数据表sql
		TSqliteUtils.generateTableSql(User.class);
		
		Session session = new Session();
		
		User user = new User();
		user.setPassword("1111");
		user.setUsername("coffee");
		
		session.open();
		try {
			//添加记录
			session.insert(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		session.close();
	}
}
