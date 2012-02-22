package ningbq.service;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import ningbq.bean.Location;
import ningbq.http.HttpException;
import ningbq.http.ResponseException;
import ningbq.http.SearchNingboAPI;

public class LocationService {

	private static final String TAG = "LocationService" ;
	private SearchNingboAPI api ;
	private ArrayList<Location> locationList = new ArrayList<Location>() ;
	
	public LocationService(){
		api = new SearchNingboAPI() ;
	}
	
	
	public ArrayList<Location> getLocationsAll(String secondId){
		try {
			JSONObject jsonObject = api.getLocationsAll(secondId) ;
			
			JSONArray jsonArray = jsonObject.getJSONArray("location") ;
			for(int i=0,j = jsonArray.length();i < j ;i++){
				JSONObject json = jsonArray.getJSONObject(i) ;
				String id = json.getString("id") ;
				String name_cn = json.getString("name_cn") ;
				String name_en = json.getString("name_en") ;
				String address_cn = json.getString("address_cn") ;
				String address_en = json.getString("address_en") ;
				String telephone = json.getString("telephone") ;
				String latitude = json.getString("latitude") ;
				String longitude = json.getString("longitude") ;
				
				Location location = new Location() ;
				location.setLocationId(id) ;
				location.setName_cn(name_cn) ;
				location.setName_en(name_en) ;
				location.setAddress_cn(address_cn) ;
				location.setAddress_en(address_en) ;
				location.setTelephone(telephone) ;
				location.setLatitude(Double.valueOf(latitude)) ;
				location.setLongitude(Double.valueOf(longitude)) ;
				
				Log.i(TAG, "id:" + id + " name_cn:" + name_cn + " address_cn:"
						+ address_cn);
				locationList.add(location) ;
			}
		} catch (ResponseException e) {
			e.printStackTrace();
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return locationList ;
	}

	
	public ArrayList<Location> getNearByLocations(){
		
		
		return new ArrayList<Location>() ;
	}
	
	public Location getLocationById(String locationId){
		Location location = new Location() ;
		try {
			JSONObject jsonObject = api.getLocationById(locationId) ;
			String id = jsonObject.getString("id") ;
			String name_cn = jsonObject.getString("name_cn") ;
			String name_en = jsonObject.getString("name_en") ;
			String address_cn = jsonObject.getString("address_cn") ;
			String address_en = jsonObject.getString("address_en") ;
			String telephone = jsonObject.getString("telephone") ;
			String latitude = jsonObject.getString("latitude") ;
			String longitude = jsonObject.getString("longitude") ;
			
			location.setLocationId(id) ;
			location.setName_cn(name_cn) ;
			location.setName_en(name_en) ;
			location.setAddress_cn(address_cn) ;
			location.setAddress_en(address_en) ;
			location.setTelephone(telephone) ;
			location.setLatitude(Double.valueOf(latitude)) ;
			location.setLongitude(Double.valueOf(longitude)) ;
			
		} catch (ResponseException e) {
			e.printStackTrace();
			return null ;
		} catch (HttpException e) {
			e.printStackTrace();
			return null ;
		} catch (JSONException e) {
			e.printStackTrace();
			return null ;
		}
		
		return location ;
	}

}
