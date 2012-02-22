package ningbq.service;

import org.json.JSONObject;

import android.util.Log;

import ningbq.http.HttpException;
import ningbq.http.ResponseException;
import ningbq.http.SearchNingboAPI;

public class FavoriteService {

	private static final String TAG = "FavoriteService";

	private SearchNingboAPI api;

	public FavoriteService() {
		api = new SearchNingboAPI();
	}

	public JSONObject addFavorite(String userId, String locationId,
			String locationName) {
		try {
			return api.addFavorite(userId, locationId, locationName);
		} catch (ResponseException e) {
			e.printStackTrace();
			Log.i(TAG, "Add Favorite Error!");
			return null;
		} catch (HttpException e) {
			e.printStackTrace();
			Log.i(TAG, "Add Favorite Error!");
			return null;
		}
	}

	public String isExistsFavorite(String userId, String locationId) {
		try {
			return api.isExistsFavorite(userId, locationId) ;
		} catch (HttpException e) {
			e.printStackTrace();
			return null;
		}
	}

}
