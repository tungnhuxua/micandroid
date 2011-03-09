package team1.videoplay.payuser.model;

import java.util.Date;

public class PayUser {
	private int payuserId;
	private int userId;
	private Date payuserApplytime;
	private Date payuserSupplytime;
	private float payuserApplymoney;
	private String payuserRemark;
	private float payuserSupplymoney;
	private int payuserState; //0 ÉêÇë 1 Í¨¹ı
	
	
	public int getPayuserId() {
		return payuserId;
	}
	public void setPayuserId(int payuserId) {
		this.payuserId = payuserId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getPayuserApplytime() {
		return payuserApplytime;
	}
	public void setPayuserApplytime(Date payuserApplytime) {
		this.payuserApplytime = payuserApplytime;
	}
	public Date getPayuserSupplytime() {
		return payuserSupplytime;
	}
	public void setPayuserSupplytime(Date payuserSupplytime) {
		this.payuserSupplytime = payuserSupplytime;
	}
	public float getPayuserApplymoney() {
		return payuserApplymoney;
	}
	public void setPayuserApplymoney(float payuserApplymoney) {
		this.payuserApplymoney = payuserApplymoney;
	}
	public String getPayuserRemark() {
		return payuserRemark;
	}
	public void setPayuserRemark(String payuserRemark) {
		this.payuserRemark = payuserRemark;
	}
	public float getPayuserSupplymoney() {
		return payuserSupplymoney;
	}
	public void setPayuserSupplymoney(float payuserSupplymoney) {
		this.payuserSupplymoney = payuserSupplymoney;
	}
	public int getPayuserState() {
		return payuserState;
	}
	public void setPayuserState(int payuserState) {
		this.payuserState = payuserState;
	}
	
	
}
