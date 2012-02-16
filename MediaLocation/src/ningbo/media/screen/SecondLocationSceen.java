package ningbo.media.screen;

import ningbo.media.MainScreenActivity;
import ningbo.media.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

public class SecondLocationSceen extends Activity{

	
	private LocationManager locationManager = null ;
	private boolean gps_enabled = false ;    //GPS是否可用.
	private boolean network_enabled = false ;//网络是否可用.
	private Location currentLocation = null ;//
	private double currentLatitude = 0.0 ;
	private double currentLongitude = 0.0 ;
	
	private EditText other_Latitude = null ;
	private EditText other_Longitude = null ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.other) ;
		other_Latitude = (EditText)findViewById(R.id.otherLatitude) ;
		other_Longitude = (EditText)findViewById(R.id.otherLongitude) ;
		FindLocation(this) ;
	}

	
	 public void FindLocation(Context context) {

         locationManager = (LocationManager) context
                 .getSystemService(Context.LOCATION_SERVICE);

         gps_enabled = locationManager
                 .isProviderEnabled(LocationManager.GPS_PROVIDER);
         network_enabled = locationManager
                 .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

         if (network_enabled) {
             locationManager.requestLocationUpdates(
                     LocationManager.NETWORK_PROVIDER, 0, 0,
                     locationListenerNetwork);
             Log.i("@@@@@@@@@@ Network provider is enabled", "Network Provider");
             Toast.makeText(SecondLocationSceen.this,
                     "Network provider is enabled", 2000);
         } else {
             Toast.makeText(SecondLocationSceen.this,
                     "Network provider is not enabled", 2000);
         }

         if (gps_enabled) {
             locationManager.requestLocationUpdates(
                     LocationManager.GPS_PROVIDER, 0, 0, locationListenerGPS);
             Log.i("@@@@@@@@@@ GPS provider is enabled", "GPS Provider");
             Toast.makeText(SecondLocationSceen.this,
                     "GPS provider is enabled", 2000);
         } else {
             Toast.makeText(SecondLocationSceen.this, "GPS provider is not enabled",
                     2000);
         }

         if(!network_enabled && !gps_enabled) {
             currentLocation = getMyLastKnownLocation();
             currentLatitude = currentLocation.getLatitude();
             currentLongitude = currentLocation.getLongitude();

             Log.i("@@@@@@@@ Both location provider disabled",
                     "getMylastKnownLocation = "+String.valueOf(currentLatitude)
                     + " : " + String.valueOf(currentLongitude));
             Toast.makeText(SecondLocationSceen.this,"LastKnownLocation\n"+String.valueOf(currentLatitude) + "\n"
                             + String.valueOf(currentLongitude), 3000).show();
             Intent intent = new Intent(SecondLocationSceen.this, MainScreenActivity.class);
             startActivity(intent);
         }

     }

     LocationListener locationListenerNetwork = new LocationListener() {
         public void onLocationChanged(Location location) {
             updateLocation(location);
             Log.i("@@@@@@@@ Inside FindLocation", "Inside FindLocation");
         }

         public void onStatusChanged(String provider, int status, Bundle extras) {

         }

         public void onProviderEnabled(String provider) {
         }

         public void onProviderDisabled(String provider) {
         }

     };

     LocationListener locationListenerGPS = new LocationListener() {

         @Override
         public void onLocationChanged(Location location) {
             updateLocation(location);

             Log.i("@@@@@@@@@@ Inside onLocationChangedGPS", String
                     .valueOf(currentLatitude)
                     + " : " + String.valueOf(currentLongitude));

             Toast.makeText(
                     SecondLocationSceen.this,
                     "GPS Location \n" + String.valueOf(currentLatitude) + "\n"
                             + String.valueOf(currentLongitude), 5000).show();

         }

         @Override
         public void onStatusChanged(String provider, int status, Bundle extras) {
             // TODO Auto-generated method stub

         }

         @Override
         public void onProviderEnabled(String provider) {
             // TODO Auto-generated method stub

         }

         @Override
         public void onProviderDisabled(String provider) {
             // TODO Auto-generated method stub

         }

     };

     public Location getMyLastKnownLocation () {
             Location locNetwrok = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
             Location locGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
             if(locNetwrok != null)
                 return locNetwrok;
             else if(locGPS != null)
                 return locGPS;

             return null;
     }

     void updateLocation(Location location) {
         currentLocation = location;
         currentLatitude = currentLocation.getLatitude();
         currentLongitude = currentLocation.getLongitude();
         other_Latitude.setText(String.valueOf(currentLatitude)) ;
         other_Longitude.setText(String.valueOf(currentLongitude)) ;

         Log.i("@@@@@@@@ Inside LeopardScreen locationChanged",
                 "locationChanged");

     }
	
}
