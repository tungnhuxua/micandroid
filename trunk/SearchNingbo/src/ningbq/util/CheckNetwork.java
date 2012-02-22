package ningbq.util;

import ningbq.main.R;
import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.widget.Toast;

public class CheckNetwork {

	public static boolean check(Context context) {
		boolean flag = false;
		ConnectivityManager mgr = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (mgr.getActiveNetworkInfo() != null) {
			flag = mgr.getActiveNetworkInfo().isAvailable();
		}
		return flag;
	}

	public static boolean openGPS(Context context) {
		LocationManager m_locationMgr = (LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE);
		if (m_locationMgr
				.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)) {
			return true;
		} else {
			Toast.makeText(context,
					context.getResources().getString(R.string.location_gps),
					Toast.LENGTH_SHORT).show();
			//Intent intent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
			return false ;
		}
	}

}
