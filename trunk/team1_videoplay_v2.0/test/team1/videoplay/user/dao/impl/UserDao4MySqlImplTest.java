package team1.videoplay.user.dao.impl;


import org.junit.Test;

import team1.videoplay.user.dao.impl.UserDao4MySqlImpl;
import team1.videoplay.user.model.User;


public class UserDao4MySqlImplTest {
	@Test
	public void testDeletUser(){
		new UserDao4MySqlImpl().deleteUser(1);
	}
	@Test
	public void testGetUserByUserName(){
		User user = new UserDao4MySqlImpl().getUserByUserName("yinzhaohua1");
		System.out.println(user.getUser_password());
	}
	@Test
	public void testGetAllUser(){
		new UserDao4MySqlImpl().getAllUser(2, 3);
	}
	@Test
	public void testGetUser(){
		User user = new UserDao4MySqlImpl().getUser(3);
		System.out.println(user.getUserStatus());
	}
	
	@Test
	public void testAddUser(){
		User r = new User();
		r.setUser_name("111");
		r.setUser_password("sssss");
		r.setUser_account("dddd");
		r.setUser_email("aaa");
		r.setUser_telephone("wwwww");
		new UserDao4MySqlImpl().addUser(r);
	}
	
	@Test
	public void updateUser(){
		User r = new User();
		r.setUser_name("111");
		r.setUser_password("sssss");
		r.setAnswer("fsf");
		r.setQuestion("fdsfe");
		r.setUser_account("dddd");
		r.setUser_email("aaa");
		r.setUser_telephone("wwwww");
		r.setUser_id(2);
		new UserDao4MySqlImpl().updateUser(r);
	}
	@Test
	public void testUpdateUserStatus(){
		User user = new UserDao4MySqlImpl().getUser(1);
		user.setUserStatus(1);
		int num = new UserDao4MySqlImpl().updateUserStatus(user);
		System.out.println(num);
	}

}
