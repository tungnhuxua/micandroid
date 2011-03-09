package team1.videoplay.payuser.service;


import java.util.ArrayList;

import team1.videoplay.payuser.model.PayUser;
import team1.videoplay.utils.FenYe;

public interface PayUserService {
	public int addPayUser(PayUser payuser);
	public int deletePayUser(int payuserId);
	public FenYe getAllPayUser(int page);
	public PayUser getPayUser(int payuserId);
	public int getPayUserCount();
	public int updatePayUser(float supplyMoney, int payuserID);
	//¡¡
	public ArrayList<PayUser> getAllPayUserByUserID(int userID) ;
}
