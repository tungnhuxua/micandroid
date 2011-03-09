package team1.videoplay.userPay.service;

import java.util.ArrayList;

import team1.videoplay.userPay.model.UserPay;
import team1.videoplay.utils.FenYe;

public interface UserPayService {
	public int addUserPay(UserPay userpay);
	public int deleteUserPay(int userpay_id);
	public FenYe getAllUserPay(int page);
	public UserPay getUserPay(int userpay_id);
	public int getUserPayCount();
	//¡¡
	public ArrayList<UserPay> getAllUserPayByUserID(int userID);
}
