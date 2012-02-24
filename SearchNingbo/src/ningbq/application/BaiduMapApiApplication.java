package ningbq.application;

import org.json.JSONException;
import org.json.JSONObject;

import ningbq.main.R;

import com.baidu.location.LocationChangedListener;
import com.baidu.location.LocationClient;
import com.baidu.location.ReceiveListener;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class BaiduMapApiApplication extends Application{
	static BaiduMapApiApplication baiduMapApp ;
	public static final String BAIDU_KEY = "CB5CC089E3D8BFB17CF20A81FF235B35EBBC0A9A";
	public boolean m_bKeyRight = true;	// 授权Key正确，验证通过
	public LocationClient locationClient = null ;
	public TextView txt_LocationInfo ;
	public static double locationLatitude  = 0.0 ;
	public static double locationLongitude = 0.0 ;
	public static Context mContext;

	
	
	public class MyLocationListener implements LocationChangedListener{

		@Override
		public void onLocationChanged() {
			Log.d("LocationChanged", "Location is moving.") ;
		}
		
	}
	
	public class MyReceiveListenner implements ReceiveListener{

		@Override
		public void onReceive(String result) {
			String defaultValue = getResources().getString(R.string.location_add_listView_headTitle) ;
			if(result == null){
				txt_LocationInfo.setText(defaultValue) ;
				return ;
			}
			String info = getLocationAddress(result) ;
			txt_LocationInfo.setText(info) ;
		}
		
		public String getLocationAddress(String result){
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
				Log.d("LocationInformation", "Longitude:" + longitude) ;
				Log.d("LocationInformation", "Latitude:" + latitude) ;
				
				
				Preferences.LOCATION_LATITUDE = Double.valueOf(latitude) ;
				Preferences.LOCATION_LONGITUDE = Double.valueOf(longitude) ;
				return address ;
			} catch (JSONException e) {
				e.printStackTrace();
				return null ;
			}
			
		}
		
	}
	
	
}
