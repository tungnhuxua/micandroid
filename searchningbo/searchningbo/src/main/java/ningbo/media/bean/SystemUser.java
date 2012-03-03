package ningbo.media.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
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
	
	@Expose
	private String username; 
	
	@Expose
	private String password;
	
	@Expose
	private String name_en;
	
	@Expose
	private String name_cn;
	
	@Expose
	private String email;
	
	@Expose
	private String photo_path;
	
	@Column(name="manager")
	@Expose
	private Boolean isManager;
	
	@Column(name = "userKey")
	private String userKey ;
	
	@Expose
	private Date date_time;
	
	@Transient
	private String error ;
	
	public SystemUser(){}
	
	public SystemUser(String error){
		this.error = error ;
	}

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

	public String getName_en() {
		return name_en;
	}

	public void setName_en(String name_en) {
		this.name_en = name_en;
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

	public Date getDate_time() {
		return date_time;
	}

	public void setDate_time(Date date_time) {
		this.date_time = date_time;
	}
	
	
	
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
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
