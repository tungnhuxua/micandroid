package ningbq.application;

import ningbq.http.SearchNingboAPI;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class SearchNingboApplication extends Application{
	
	public static Context mContext;
	
	public static SearchNingboAPI api ; 
	
	public static SharedPreferences mPref;

	public static int networkType = 0;


}
