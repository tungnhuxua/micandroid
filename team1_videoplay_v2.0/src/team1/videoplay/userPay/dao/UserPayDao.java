package team1.videoplay.userPay.dao;

import java.util.ArrayList;

import team1.videoplay.userPay.model.UserPay;


public interface UserPayDao {
	public int addUserPay(UserPay userpay);
	public int deleteUserPay(int userpay_id);
	
	public ArrayList<UserPay> getAllUserPay(int page,int pageSize);
	public UserPay getUserPay(int userpay_id);
	public int getUserPayCount();
	//¡¡
	public ArrayList<UserPay> getAllUserPayByUserID(int userID);
}
