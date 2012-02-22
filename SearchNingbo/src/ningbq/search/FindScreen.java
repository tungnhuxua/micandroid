package ningbq.search;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

import ningbq.main.BaseActivity;
import ningbq.main.R;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class FindScreen extends BaseActivity implements LocationListener {

	private MapView mapView;
	private Location currentLocation;
	private MapController mapController;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		ViewGroup mapGroup = (ViewGroup) appView
				.findViewById(R.id.findScrennMapLayout);
		this.mapView = (MapView) mapGroup.findViewById(R.id.findScreenMap);
		ImageView imageView = (ImageView) viewGroup
				.findViewById(R.id.findScreenliding);
		imageView.setOnClickListener(new ClickListenerForScrolling(scrollView,
				menuView));
		children = new View[] { menuView, appView };
		int scrollToViewIdx = 1;
		scrollView.initViews(children, scrollToViewIdx,
				new SizeCallbackForMenu(imageView));

		Criteria locCriteria = new Criteria();
		locCriteria.setAccuracy(Criteria.ACCURACY_FINE);
		LocationManager locMgr = (LocationManager) getSystemService(LOCATION_SERVICE);
		locMgr.requestLocationUpdates(
				locMgr.getBestProvider(locCriteria, true), 2000, 25, this);
		currentLocation = locMgr.getLastKnownLocation(locMgr.getBestProvider(locCriteria, true)) ;
		
		this.mapController = this.mapView.getController();
		this.mapController.setZoom(16); // Set zoom grade
		//this.mapView.displayZoomControls(true) ;
		//this.mapView.setBuiltInZoomControls(true); 
		//this.mapView.setStreetView(true) ;
		//this.mapView.setSatellite(true) ;
		
		
		MyLocationOverlay myLoc = new MyLocationOverlay(getBaseContext(), this.mapView) ;
		myLoc.enableCompass() ;
		myLoc.enableMyLocation() ;
		
		this.mapView.getOverlays().add(myLoc) ;
		if(currentLocation != null){
			showCurrentLocation() ;
		}

	}

	@Override
	public int getApplicationLayoutResource() {
		return R.layout.find;
	}

	@Override
	public int getViewGroupResource() {
		return R.id.findScreenHeadBar;
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		((LocationManager) getSystemService(LOCATION_SERVICE))
				.removeUpdates(this);
	}

	/**
	 * Moves the map to the user's current location
	 */
	private void showCurrentLocation() {
		GeoPoint p = new GeoPoint((int) (currentLocation.getLatitude() * 1e6),
				(int) (currentLocation.getLongitude() * 1e6));
		this.mapController.animateTo(p);
	}

	@Override
	public void onLocationChanged(Location newLoc) {
		currentLocation = newLoc;
		Log.d("FindActivity", "Got location update: " + newLoc);
		showCurrentLocation();
	}

	@Override
	public void onProviderDisabled(String provider) {

	}

	@Override
	public void onProviderEnabled(String provider) {

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {

	}

}
