package ningbq.service;

import org.json.JSONObject;

import android.util.Log;

import ningbq.http.HttpException;
import ningbq.http.ResponseException;
import ningbq.http.SearchNingboAPI;

public class UserService {
	
	private static final String TAG = "UserService" ;
	private SearchNingboAPI api ;
	
	public UserService(){
		api = new SearchNingboAPI() ;
	}
	
	public JSONObject login(String email,String password){
			try {
				return api.userLogin(email, password) ;
			} catch (ResponseException e) {
				e.printStackTrace();
			} catch (HttpException e) {
				e.printStackTrace();
			}
			Log.i(TAG, "User call Login API error!") ;
			return null ;
		
	}
	
	public JSONObject register(String username,String password,String email){
		try {
			return api.registerUser(username, password, email) ;
		} catch (HttpException e) {
			e.printStackTrace();
		}
		Log.i(TAG, "Register call Login API error!") ;
		return null;
	}

}
