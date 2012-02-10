package ningbq.http;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import ningbq.bean.User;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.text.TextUtils;

public class SearchNingboAPI extends SearchNingboSupport implements
		Serializable {

	private static final long serialVersionUID = -2445193189518562887L;

	// public static final String CONSUMER_KEY = Configuration.getSource();
	// public static final String CONSUMER_SECRET = "";
	private SimpleDateFormat format = new SimpleDateFormat(
			"EEE, d MMM yyyy HH:mm:ss z", Locale.US);

	// private String baseURL = Configuration.getScheme() +
	// "api.searchningbo.com/";
	// private String searchBaseURL = Configuration.getScheme() +
	// "api.searchningbo.com/";
	private String baseURL = "http://192.168.1.100:8080";
	private String searchBaseURL = "http://192.168.1.100:8080";
	
	private Map<String,String> params = new HashMap<String,String>() ;

	public SearchNingboAPI() {
		super();
		format.setTimeZone(TimeZone.getTimeZone("GMT"));
	}

	public SearchNingboAPI(String userId, String password) {
		super(userId, password);
		format.setTimeZone(TimeZone.getTimeZone("GMT"));
	}

	public SearchNingboAPI(String userId, String password, String baseURL) {
		this(userId, password);
		this.baseURL = baseURL;
	}

	/***
	 * Set http oauth
	 * 
	 * @param username
	 * @param password
	 */
	public void setCredentials(String username, String password) {
		http.setCredentials(username, password);
	}

	/***
	 * check username and password is empty or no.
	 * 
	 * @param username
	 * @param password
	 * 
	 * @return true or false
	 */
	public static boolean isValidCredentials(String username, String password) {
		return !TextUtils.isEmpty(username) && !TextUtils.isEmpty(password);
	}

	public String getBaseURL() {
		return baseURL;
	}

	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}

	public String getSearchBaseURL() {
		return searchBaseURL;
	}

	public void setSearchBaseURL(String searchBaseURL) {
		this.searchBaseURL = searchBaseURL;
	}

	/**
	 * Returns authenticating userid 注意：此userId不一定等同用户的user_id参数
	 * 它可能是任意一种当前用户所使用的ID类型（如邮箱，用户名等），
	 * 
	 * @return userid
	 */
	public String getUserId() {
		return http.getUserId();
	}

	/**
	 * Returns authenticating password
	 * 
	 * @return password
	 */
	public String getPassword() {
		return http.getPassword();
	}

	/**
	 * set post request parameter
	 * 
	 * @param the
	 *            collection of post request parameter.
	 * 
	 * @return ArrayList<BasicNameValuePair>
	 */
	public ArrayList<BasicNameValuePair> createParams(
			BasicNameValuePair... values) {
		ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		for (BasicNameValuePair param : params) {
			params.add(param);
		}
		return params;
	}

	public User registerUser(String jsonObject) throws HttpException {
		return new User(http.post("http://192.168.1.101:8080/user/register",
				createParams(new BasicNameValuePair("jsonObject", jsonObject))));
	}

	public JSONObject getFirstCategoryAll() throws ResponseException,
			HttpException {
		return http.get(baseURL + "/category/first/showAll").asJSONObject();
	}

	public JSONObject getSecondCategoryAll(String id) throws ResponseException,
			HttpException {
		return http.get(baseURL + "/category/second/show/" + id).asJSONObject();
	}

	public JSONObject getLocationsAll(String id) throws ResponseException,
			HttpException {
		return http.get(baseURL + "/location/category/" + id).asJSONObject();
	}

	public JSONObject userLogin(String email, String password)
			throws ResponseException, HttpException {
		params.put("email", email) ;
		params.put("password", password) ;
		return http.getForParams(baseURL + "/user/verification", params).asJSONObject() ;
	}

	public JSONObject getLocationById(String id) throws ResponseException,
			HttpException {
		return http.get(baseURL + "/location/show/" + id).asJSONObject();
	}

}
