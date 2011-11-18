package ningbq.main;

import ningbq.Constant.Constaints;
import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

/**
 * 
 * @author Saurabh Solanki Class used as a splash screen and find the current
 *         location
 * 
 */

public class SplashScreen extends Activity implements LocationListener {
	
	private RefreshHandler m_redrawHandler = null;
	private LocationManager m_gpsManager = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// for removing the title
														// bar
		setContentView(R.layout.main);
		m_redrawHandler = new RefreshHandler();
		m_gpsManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		startListening();
		updateUI();
		// modify

		Toast.makeText(this,
				"Please Make sure Your GPS is on While open this Application",
				Toast.LENGTH_SHORT).show();

	}

	/**
	 * This Function Start Listing the GPS
	 */
	private void startListening() {
		m_gpsManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,
				this);
	}

	/**
	 * This Function Stop Listing the GPS
	 */
	private void stopListening() {
		if (m_gpsManager != null) {
			m_gpsManager.removeUpdates(this);
		}
	}

	/**
	 * Getting the GPS location of user from Here
	 */
	public void onLocationChanged(Location location) {
		String GpsLocation = "";
		GpsLocation += "Time: " + location.getTime() + "\n";
		GpsLocation += "\tLatitude:  " + location.getLatitude() + "\n";
		GpsLocation += "\tLongitude: " + location.getLongitude() + "\n";
		GpsLocation += "\tAccuracy:  " + location.getAccuracy() + "\n";
		Log.d("User Current Location ---------------->", GpsLocation);
		Constaints.LATITUDE = location.getLatitude();
		Constaints.LONGITUDE = location.getLongitude();
		Toast.makeText(this, GpsLocation, Toast.LENGTH_SHORT).show();

	}

	private void updateUI() {
		m_redrawHandler.sleep(2000);
	}

	/**
	 * 
	 * This Class class Handle the Ui of this Class
	 * 
	 */
	class RefreshHandler extends Handler {
		public void handleMessage(Message msg) {
			try {
				Intent intent = new Intent(SplashScreen.this,
						MainScrrenWithRecentView.class);
				startActivity(intent);
				finish();
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}

		public void sleep(long delayMillis) {
			try {
				this.removeMessages(0);
				sendMessageDelayed(obtainMessage(0), delayMillis);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
	};
}