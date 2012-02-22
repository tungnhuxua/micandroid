package ningbq.application;

import ningbq.http.SearchNingboAPI;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;

public class SearchNingboApplication extends Application{
	
	public static Context mContext;
	
	public static SearchNingboAPI api ; 
	
	public static SharedPreferences mPref;

	public static int networkType = 0;
	
	
	
	
	@Override
	public void onCreate() {
		super.onCreate();
		mContext = this.getApplicationContext() ;
		mPref = PreferenceManager.getDefaultSharedPreferences(this) ;
	}




	public String getNetworkType() {
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
		// NetworkInfo mobNetInfo = connectivityManager
		// .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		if (activeNetInfo != null) {
			return activeNetInfo.getExtraInfo(); 
		} else {
			return null;
		}
	}


}
