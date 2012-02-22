package ningbq.bean;

import java.io.Serializable;
import java.util.Date;

import ningbq.http.HttpException;
import ningbq.http.Response;
import ningbq.http.ResponseException;
import ningbq.http.SearchNingboResponse;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class User extends SearchNingboResponse implements Serializable {

	private static final String TAG = "User" ;
	private static final long serialVersionUID = 6942374507726462244L;
	
	private String id ;

	private String username;

	private String password;

	private String name_en;

	private String name_cn;

	private String email;

	private String photo_path;

	private boolean isManager;

	private Date date_time;
	

	public User() {
	}

	public User(JSONObject jsonObject) {
		super();
		init(jsonObject);
	}
	
	public User(Response res){
		super();
		try {
			init(res.asJSONObject());
		} catch (ResponseException e) {
			e.printStackTrace();
		}
	}

	private void init(JSONObject json) {
		try {
			id          = json.getString("id") ;
			username 	= json.getString("username");
			password 	= json.getString("password");
			name_en 	= json.getString("name_en");
			name_cn 	= json.getString("name_cn");
			email       = json.getString("email") ;
			photo_path 	= json.getString("photo_path");
			isManager 	= json.getBoolean("isManager");
			Log.i(TAG, json.getString("date_time")) ;
			date_time 	= parseDate(json.getString("date_time"),
					"EEE MMM dd HH:mm:ss z yyyy");
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (HttpException e) {
			e.printStackTrace();
		}

	}
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setManager(boolean isManager) {
		this.isManager = isManager;
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
	
}
