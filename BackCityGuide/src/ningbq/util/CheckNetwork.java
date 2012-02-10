package ningbq.util;

import android.content.Context;
import android.net.ConnectivityManager;

public class CheckNetwork {
	
	public static boolean check(Context context){
		boolean flag = false ;
		ConnectivityManager mgr = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE) ;
		if(mgr.getActiveNetworkInfo() != null){
			flag = mgr.getActiveNetworkInfo().isAvailable() ;
		}
		return flag ;
	}

}
