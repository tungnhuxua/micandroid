package team1.videoplay.payuser.dao;

import java.util.ArrayList;

import team1.videoplay.payuser.model.PayUser;




public interface PayUserDao {
	public int addPayUser(PayUser payuser);
	public int deletePayUser(int payuserId);
	public int updatePayUser(float supplyMoney,int payuserID);
	
	public ArrayList<PayUser> getAllPayUser(int page,int pageSize);
	public PayUser getPayUser(int payuserId);
	public int getPayUserCount();
	//¡¡
	public ArrayList<PayUser> getAllPayUserByUserID(int userID) ;
}
