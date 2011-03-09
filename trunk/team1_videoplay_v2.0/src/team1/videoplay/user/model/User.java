package team1.videoplay.user.model;


import java.util.Date;

public class User{
	private int  user_id;
	private String user_name;
	private String  user_password;
	private Date   user_registedate ;
	private  Date user_lastlogindate;
	private  long  user_point;//100为兵，200为尉，300为校，400--为将
	private  String user_account ;
	private String user_email ;
	private String user_telephone;
	private float user_money;
	private int userStatus;//0 正常 1 冻结
	private String question;
	private String answer;
	
	public int getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public Date getUser_registedate() {
		return user_registedate;
	}
	public void setUser_registedate(Date user_registedate) {
		this.user_registedate = user_registedate;
	}
	public Date getUser_lastlogindate() {
		return user_lastlogindate;
	}
	public void setUser_lastlogindate(Date user_lastlogindate) {
		this.user_lastlogindate = user_lastlogindate;
	}
	public long getUser_point() {
		return user_point;
	}
	public void setUser_point(long user_point) {
		this.user_point = user_point;
	}
	public String getUser_account() {
		return user_account;
	}
	public void setUser_account(String user_account) {
		this.user_account = user_account;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_telephone() {
		return user_telephone;
	}
	public void setUser_telephone(String user_telephone) {
		this.user_telephone = user_telephone;
	}
	public float getUser_money() {
		return user_money;
	}
	public void setUser_money(float user_money) {
		this.user_money = user_money;
	}
}
	