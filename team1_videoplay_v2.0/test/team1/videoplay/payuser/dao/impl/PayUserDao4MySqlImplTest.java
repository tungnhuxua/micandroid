package team1.videoplay.payuser.dao.impl;


import org.junit.Test;

import team1.videoplay.payuser.model.PayUser;
import team1.videoplay.video.dao.impl.VideoDao4MySqlImpl;


public class PayUserDao4MySqlImplTest {
	@Test
	public void testDeletPayUser(){
		new PayUserDao4MySqlImpl().deletePayUser(1);
	}
	@Test
	public void testGetAllPayUser(){
		new PayUserDao4MySqlImpl().getAllPayUser(2, 3);
	}
	@Test
	public void testGetPayUser(){
		new PayUserDao4MySqlImpl().getPayUser(2);
	}
	
	@Test
	public void testAddPayUser(){
		
		PayUser r = new PayUser();
		r.setUserId(1);
		r.setPayuserApplymoney(12);
		r.setPayuserRemark("2");
		r.setPayuserSupplymoney(7.3f);
	
		new PayUserDao4MySqlImpl().addPayUser(r);
	}
	@Test
	public void testAA(){
		
	
	}
	@Test
	public void updatePayUser(){
		PayUser r = new PayUser();
//		r.setPayuserSupplytime(new Date());
		r.setPayuserApplymoney(7.2f);
		r.setPayuserId(1);
	//	new PayUserDao4MySqlImpl().updatePayUser(r);
	}
	
}
