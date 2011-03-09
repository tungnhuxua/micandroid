package team1.videoplay.userPay.dao.impl;

import org.junit.Test;

import team1.videoplay.review.model.Review;
import team1.videoplay.userPay.dao.impl.UserPayDao4MySqlImpl;
import team1.videoplay.userPay.model.UserPay;



public class UserPayDao4MySqlImplTest {
	@Test
	public void testDeletUserPay(){
		UserPay r = new UserPay();
		r.setUserpay_type(1);
		new UserPayDao4MySqlImpl().deleteUserPay(1);
	}
	@Test
	public void testGetAllUserPay(){
		Review r = new Review();
		r.setReview_content("ssssss");
		new UserPayDao4MySqlImpl().getAllUserPay(2, 3);
	}
	@Test
	public void testGetUserPay(){
		Review r = new Review();
		r.setReview_content("ssssss");
		new UserPayDao4MySqlImpl().getUserPay(2);
	}
	
	@Test
	public void testAddUserPay(){
		UserPay r = new UserPay();
		r.setUserpay_type(1);
		r.setUserpay_money(7.10f);
		new UserPayDao4MySqlImpl().addUserPay(r);
	}
}
