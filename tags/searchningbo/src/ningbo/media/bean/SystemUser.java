package ningbo.media.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

@Entity
@Table(name = "users")
@XmlRootElement
public class SystemUser implements Serializable {

	private static final long serialVersionUID = -4367047739963786995L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private Integer id;
	//用户名
	@Expose
	private String username; 
	//密码
	@Expose
	private String password;
	//真实姓名
	@Expose
	private String name_cn;
	//电子邮箱
	@Expose
	private String email;
	//是否已经通过验证（新注册的用户上否已经激活）
	@Expose
	private boolean status;
	//用户头像
	@Expose
	private String photo_path;
	//性别
	@Expose
	private boolean gender;
	//出生日期
	@Expose
	private Date birthday;
	//是否是管理员
	@Column(name="manager")
	@Expose
	private Boolean isManager;
	
	@Column(name = "userKey")
	private String userKey ;
	//注册日期
	@Expose
	private Date datetime;
	
	public SystemUser(){}
	

	public String getUserKey() {
		return userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName_cn() {
		return name_cn;
	}

	public void setName_cn(String name_cn) {
		this.name_cn = name_cn;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoto_path() {
		return photo_path;
	}

	public void setPhoto_path(String photo_path) {
		this.photo_path = photo_path;
	}

	public Boolean getIsManager() {
		return isManager;
	}

	public void setIsManager(Boolean isManager) {
		this.isManager = isManager;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String toJson(){
		Gson gson = new Gson() ;
		String json = gson.toJson(this, SystemUser.class) ;
		return  json ;
	}
	
	public static SystemUser fromJson(String json){
		Gson gson = new Gson() ;
		SystemUser sUser = gson.fromJson(json, SystemUser.class) ;
		return sUser ;
	}
	
	public String toString(){
		return "SystemUser[username:"+this.username+" ->password:"+this.password+"]" ;
	}
	
}
