package org.phox.where.view;

import java.util.*;

import org.phox.where.R;
import org.phox.where.controller.BaiduMapApiApplication;
import org.phox.where.controller.HistoryController;
import org.phox.where.controller.LocationController;
import org.phox.where.controller.PreferencesSavedItemStorage;
import org.phox.where.controller.Storage;
import org.phox.where.model.SavedItem;
import org.phox.where.util.LocationSettings;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.ItemizedOverlay;
import com.baidu.mapapi.MapActivity;
import com.baidu.mapapi.MapController;
import com.baidu.mapapi.MapView;
import com.baidu.mapapi.MyLocationOverlay;
import com.baidu.mapapi.Overlay;
import com.baidu.mapapi.OverlayItem;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class WhereMain extends MapActivity implements LocationListener {

	private Storage<SavedItem> savedItemStorage = null;
	private Location currentLocation = null;
	private MapView mapView;
	private MapController mapController;
	private View currentPopup = null;
	private ItemOverlay itemOverlay = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_PROGRESS);
		setContentView(R.layout.main_map);

		/** 百度地图的初始化工作. */
		BaiduMapApiApplication app = (BaiduMapApiApplication) this
				.getApplication();
		if (app.mBMapMgr == null) {
			app.mBMapMgr = new BMapManager(this.getApplicationContext());
			app.mBMapMgr.init(BaiduMapApiApplication.BAIDU_KEY,
					new BaiduMapApiApplication.MyGeneralListener());
		}
		app.mBMapMgr.start();
		initMapActivity(app.mBMapMgr);
		/** ---end---- */

		savedItemStorage = Storage.getStorage(
				PreferencesSavedItemStorage.class, getApplicationContext());

		// Listen for history
		HistoryController historyController = HistoryController
				.getInstance(getApplicationContext());
		historyController.startRecording();

		// Set up location tracking
		Criteria locCriteria = new Criteria();
		locCriteria.setAccuracy(Criteria.ACCURACY_FINE);

		//Criteria locCriteria = LocationSettings.getDefaultCriteriaSetting() ;
		LocationManager locMan = (LocationManager) getSystemService(LOCATION_SERVICE);
		locMan.requestLocationUpdates(
				locMan.getBestProvider(locCriteria, true), 2000, 25, this);

		currentLocation = locMan.getLastKnownLocation(locMan.getBestProvider(
				locCriteria, true));
		Log.d("FindActivity", "Initial location: " + currentLocation);

		// Set up map view
		this.mapView = (MapView) findViewById(R.id.mapView);
		this.mapController = this.mapView.getController();

		// Overlay user location and item location
		MyLocationOverlay myLoc = new MyLocationOverlay(getApplicationContext(),
				this.mapView);
		myLoc.enableCompass(); // 启用指南针
		myLoc.enableMyLocation(); // 启用定位
		this.mapController.setZoom(16);
		
		
		itemOverlay = new ItemOverlay(getResources().getDrawable(
				R.drawable.map_marker), this);
		for (SavedItem si : savedItemStorage.getAll()) {
			addSavedItemToOverlay(si);
		}

		this.mapView.getOverlays().add(itemOverlay);
		this.mapView.getOverlays().add(myLoc);
		this.mapView.getOverlays().add(new Overlay() {
			@Override
			public boolean onTouchEvent(MotionEvent e, MapView mapView) {
				if (currentPopup != null)
					mapView.removeView(currentPopup);
				return false;
			}
		});
		
		
		if (currentLocation != null){
			showCurrentLocation();
		}

		// For the history button...
		((Button) findViewById(R.id.historyButton))
				.setOnClickListener(new Button.OnClickListener() {
					@Override
					public void onClick(View arg0) {
						startActivityForResult(
								new Intent(getApplicationContext(),
										HistoryListView.class), 0);
					}
				});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		((LocationManager) getSystemService(LOCATION_SERVICE))
				.removeUpdates(this);
	}

	public void addSavedItemToOverlay(SavedItem si) {
		this.itemOverlay.addOverlay(new OverlayItem(new GeoPoint(
				(int) (si.latitude * 1e6), (int) (si.longitude * 1e6)),
				si.name, ""));
	}

	/**
	 * Moves the map to the user's current location
	 */
	private void showCurrentLocation() {
		GeoPoint p = new GeoPoint((int) (currentLocation.getLatitude() * 1e6),
				(int) (currentLocation.getLongitude() * 1e6));
		this.mapController.setCenter(p) ;
		this.mapController.animateTo(p);
		Toast.makeText(
				WhereMain.this,
				"GPS-information:latitude:" + currentLocation.getLatitude()
						+ "\n" + "longitude:" + currentLocation.getLongitude(),
				Toast.LENGTH_LONG).show();
	}

	@Override
	public void onLocationChanged(Location newLoc) {
		currentLocation = newLoc;
		Log.d("FindActivity", "Got location update: " + newLoc);
		showCurrentLocation();
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	@Override
	protected void onPause() {
		BaiduMapApiApplication app = (BaiduMapApiApplication) this
				.getApplication();
		app.mBMapMgr.stop();
		super.onPause();
	}

	@Override
	protected void onResume() {
		BaiduMapApiApplication app = (BaiduMapApiApplication) this
				.getApplication();
		app.mBMapMgr.start();
		super.onResume();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflator = getMenuInflater();
		inflator.inflate(R.menu.menu, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.addMenuItem:
			addItem();
			return true;
		case R.id.aboutMenuItem:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/**
	 * Launches the add item dialog and saves the item and location
	 */
	private void addItem() {
		//final LocationController locCont = LocationController.getInstance(this);

		
/*		if (!locCont.hasGPSLock()) {
			new AlertDialog.Builder(WhereMain.this)
					.setMessage("Please allow the GPS to obtain a lock")
					.setNegativeButton("Got it",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
								}
							}).show();

			return;
		}*/

		// shows how to add a custom layout to an AlertDialog
		LayoutInflater factory = LayoutInflater.from(this);
		final View textEntryView = factory.inflate(
				R.layout.alert_dialog_text_entry, null);
		AlertDialog alert = new AlertDialog.Builder(this)
				.setTitle("Save Location")
				.setView(textEntryView)
				.setPositiveButton("Save",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								String item = ((EditText) textEntryView
										.findViewById(R.id.itemEdit)).getText()
										.toString();

								Location loc = currentLocation ;

								SavedItem i = new SavedItem();
								i.name = item;
								i.latitude = loc.getLatitude();
								i.longitude = loc.getLongitude();
								i.date = System.currentTimeMillis();

								savedItemStorage.put(i);
								addSavedItemToOverlay(i);
								itemOverlay.onTap(itemOverlay.size() - 1);

								// Notify the user it was saved
								Toast.makeText(getBaseContext(),
										"Saved " + item + "!",
										Toast.LENGTH_LONG).show();
							}
						})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								// Do nothing
							}
						}).create();
		alert.show();
	}

	private class ItemOverlay extends ItemizedOverlay<OverlayItem> {
		private ArrayList<OverlayItem> items = new ArrayList<OverlayItem>();
		@SuppressWarnings("unused")
		private Context context;

		public ItemOverlay(Drawable defaultMarker, Context context) {
			super(boundCenterBottom(defaultMarker));
			this.context = context;
			populate();
		}

		public void addOverlay(OverlayItem overlay) {
			items.add(overlay);
			populate();
			mapView.invalidate();
		}

		@Override
		protected OverlayItem createItem(int i) {
			return items.get(i);
		}

		@Override
		public int size() {
			return items.size();
		}

		@Override
		protected boolean onTap(int index) {
			if (currentPopup != null)
				mapView.removeView(currentPopup);
			else {
				//
				// There seems to be a bug when the map_popup is first rendered
				// (the triangle image point.png is
				// very large). This is a hack to get around that problem and
				// only use the second rendering on.
				//

				getLayoutInflater().inflate(R.layout.map_popup, mapView, false);
			}

			// Inspired by
			OverlayItem item = items.get(index);
			TextView popup = (TextView) (getLayoutInflater().inflate(
					R.layout.map_popup, mapView, false));
			popup.setText(item.getTitle());
			MapView.LayoutParams mapParams = new MapView.LayoutParams(
					MapView.LayoutParams.WRAP_CONTENT,
					MapView.LayoutParams.WRAP_CONTENT, item.getPoint(), 0, // x-offset
					-40, MapView.LayoutParams.BOTTOM_CENTER);

			mapView.addView(popup, mapParams);
			popup.startAnimation(AnimationUtils.loadAnimation(WhereMain.this,
					R.anim.fadeup_in));
			currentPopup = popup;

			return true;
		}
	}

	@Override
	public void onProviderDisabled(String provider) {
		// Do nothing
	}

	@Override
	public void onProviderEnabled(String provider) {
		// Do nothing
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// Do nothing
	}

}