package ningbq.application;

import ningbq.main.R;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Application;
import android.widget.TextView;

import com.baidu.location.LocationChangedListener;
import com.baidu.location.LocationClient;
import com.baidu.location.ReceiveListener;

public class LocationService extends Application {
	
	public LocationClient locationClient = null ;
	public TextView txt_LocationInfo ;
	public static double locationLatitude  = 0.0 ;
	public static double locationLongitude = 0.0 ;

	@Override
	public void onCreate() {
		super.onCreate();
		locationClient = new LocationClient(this) ;
	}
	
	
	public class MyLocationListener implements LocationChangedListener{

		@Override
		public void onLocationChanged() {
			
		}
		
	}
	
	public class MyReceiveListenner implements ReceiveListener{

		@Override
		public void onReceive(String result) {
			String defaultValue = getResources().getString(R.string.location_add_listView_headTitle) ;
			if(result == null){
				txt_LocationInfo.setText(defaultValue) ;
			}
			String info = getLocationAddress(result) ;
			txt_LocationInfo.setText(info) ;
		}
		
	}
	
	
	private String getLocationAddress(String result){
		try {
			JSONObject json = new JSONObject(result) ;
			JSONObject content =  json.getJSONObject("content") ;
			//JSONObject result1 = json.getJSONObject("result") ;
			JSONObject point = content.getJSONObject("point") ;
			JSONObject addr = content.getJSONObject("addr") ;
			String address = String.valueOf(addr.get("detail")) ;
			String longitude = String.valueOf(point.getString("y")) ; //经度
			String latitude = String.valueOf(point.getString("x")) ; //纬度
			//String time = result1.getString("time") ;
			locationLongitude = point.getDouble("y") ;
			locationLatitude  = point.getDouble("x") ;
			
			Preferences.LOCATION_LATITUDE = Double.valueOf(latitude) ;
			Preferences.LOCATION_LONGITUDE = Double.valueOf(longitude) ;
			return address ;
		} catch (JSONException e) {
			e.printStackTrace();
			return null ;
		}
		
	}

}
