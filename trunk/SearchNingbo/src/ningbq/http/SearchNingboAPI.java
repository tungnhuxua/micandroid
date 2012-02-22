package ningbq.http;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.text.TextUtils;
import android.util.Log;

/**
 * Description:
 * 
 * @author Devon.Ning
 * @2012-2-2
 * 
 */
public class SearchNingboAPI extends SearchNingboSupport implements
		Serializable {

	private static final String TAG = "SearchNingboAPI";
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

	private Map<String, String> params = new HashMap<String, String>();

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
	 * @param email
	 * @param password
	 */
	public void setCredentials(String email, String password) {
		http.setCredentials(email, password);
	}

	/***
	 * check username and password is empty or no.
	 * 
	 * @param email
	 * @param password
	 * 
	 * @return true or false
	 */
	public static boolean isValidCredentials(String email, String password) {
		return !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password);
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
	protected ArrayList<BasicNameValuePair> createParams(
			BasicNameValuePair... values) {
		ArrayList<BasicNameValuePair> postParams = new ArrayList<BasicNameValuePair>();
		for (BasicNameValuePair param : postParams) {
			postParams.add(param);
		}
		return postParams;
	}

	public JSONObject registerUser(String username, String password,
			String email) throws HttpException {
		Log.i(TAG, "[username:" + username + " email:" + email + " ]");
		ArrayList<BasicNameValuePair> temp = new ArrayList<BasicNameValuePair>();
		temp.add(new BasicNameValuePair("username", username));
		temp.add(new BasicNameValuePair("password", password));
		temp.add(new BasicNameValuePair("email", email));

		return http.post(baseURL + "/user/register", temp).asJSONObject();

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
		params.put("email", email);
		params.put("password", password);
		return http.getForParams(baseURL + "/user/verification", params)
				.asJSONObject();
	}

	public JSONObject getLocationById(String id) throws ResponseException,
			HttpException {
		return http.get(baseURL + "/location/show/" + id).asJSONObject();
	}

	public JSONObject getNearByLocations(String latitude, String longitude,
			String area) throws ResponseException {
		params.put("latitude", latitude);
		params.put("longitude", longitude);
		params.put("area", area);
		return http.getForParams(baseURL + "location/nearby", params).asJSONObject();
	}

	/**
	 * Description:添加一条收藏.
	 * 
	 * 
	 * 2012-2-2
	 * 
	 * @param userId
	 * @param locationId
	 * @param locationName
	 * @return
	 * @throws ResponseException
	 * @throws HttpException
	 */
	public JSONObject addFavorite(String userId, String locationId,
			String locationName) throws ResponseException, HttpException {
		ArrayList<BasicNameValuePair> temp = new ArrayList<BasicNameValuePair>();
		temp.add(new BasicNameValuePair("userId", userId));
		temp.add(new BasicNameValuePair("locationId", locationId));
		temp.add(new BasicNameValuePair("locationName", locationName));

		return http.post(baseURL + "/favorite/add", temp).asJSONObject();
	}

	/**
	 * Description:判断位置是否已经收藏.
	 * 
	 * 
	 * 2012-2-2
	 * 
	 * @param userId
	 * @param locationId
	 * @return String
	 * @throws HttpException
	 */
	public String isExistsFavorite(String userId, String locationId)
			throws HttpException {
		// params.put("userId", userId);
		// params.put("locationId", locationId);
		return http.get(
				baseURL + "/favorite/check/" + userId + "/" + locationId)
				.asString();

	}

}
