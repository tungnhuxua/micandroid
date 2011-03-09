package team1.videoplay.userPay.model;

import java.util.Date;

public class UserPay {
	 private int userpay_id;
	 private int user_id;
	 private Date userpay_date;
	 private int userpay_type;
	 private Float userpay_money;
	 
	 
	public int getUserpay_id() {
		return userpay_id;
	}
	
	public void setUserpay_id(int userpayId) {
		userpay_id = userpayId;
	}
	
	
	public int getUser_id() {
		return user_id;
	}
	
	public void setUser_id(int userId) {
		user_id = userId;
	}
	
	
	public Date getUserpay_date() {
		return userpay_date;
	}
	
	public void setUserpay_date(Date userpayDate) {
		userpay_date = userpayDate;
	}
	
	
	public int getUserpay_type() {
		return userpay_type;
	}
	
	public void setUserpay_type(int userpayType) {
		userpay_type = userpayType;
	}
	
	
	public Float getUserpay_money() {
		return userpay_money;
	}
	
	public void setUserpay_money(Float userpayMoney) {
		userpay_money = userpayMoney;
	}
}
