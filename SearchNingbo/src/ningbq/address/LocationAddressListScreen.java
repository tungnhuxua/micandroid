package ningbq.address;

import java.util.ArrayList;
import java.util.List;

import ningbq.db.dao.LocationAddressDao;
import ningbq.main.BaseActivity;
import ningbq.main.R;
import ningbq.util.CalculateDistance;
import ningbq.util.LocationSettings;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class LocationAddressListScreen extends BaseActivity implements
		LocationListener {

	private static final int LOCATION_ADDRESS_NEARBY = 13;
	private static final int LOCATION_ADDRESS_NEARBY_NULL = 14;
	private LoadNearbyDataThread dataThread;
	static MapView mapView;
	static View popView = null;
	private Location currentLocation;
	private MapController mapController;
	private LocationManager locMgr;
	private List<Double> listDistance = new ArrayList<Double>();
	private List<GeoPoint> listPoint = new ArrayList<GeoPoint>();
	private GeoPoint loc ;
	private MyLocationOverlay myLoc;
	private Button but_addLocation = null;
	private Button but_NearbyList = null;

	private Handler mhandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case LOCATION_ADDRESS_NEARBY:
				if (listDistance != null) {
					for (int i = 0, j = listDistance.size(); i < j; i++) {
						ItemOverlay itemOverlay = new ItemOverlay(
								getResources().getDrawable(
										R.drawable.map_marker),
								LocationAddressListScreen.this);
						itemOverlay.addOverlay(new OverlayItem(
								listPoint.get(i), "距离" + listDistance.get(i)
										* 1000 + " 米", ""));
						//popView = getLayoutInflater().inflate(R.layout.popview,
						//		null);
						//mapView.addView(popView, new MapView.LayoutParams(
						//		LayoutParams.WRAP_CONTENT,
						//		LayoutParams.WRAP_CONTENT, null,
						//		MapView.LayoutParams.TOP_LEFT));
						mapView.getOverlays().add(itemOverlay);
					}
					mapView.getOverlays().add(myLoc);
					mapView.getOverlays().add(new Overlay() {

						@Override
						public boolean onTap(GeoPoint p, MapView mapView) {
							if (popView != null) {
								mapView.removeView(popView);
							}
							return false;
						}
					});

				}
				//mapController.animateTo(loc);
				//mapController.setCenter(loc);
				break;
			case LOCATION_ADDRESS_NEARBY_NULL:
				mapController.setCenter(loc);
				break;
			}

		}

	};

	class LoadNearbyDataThread extends Thread {
		@Override
		public void run() {
			if (currentLocation != null) {
				double temp1 = currentLocation.getLatitude();
				double temp2 = currentLocation.getLongitude();
				loc = new GeoPoint((int) (temp1 * 1E6), (int) (temp2 * 1E6));

				LocationAddressDao dao = new LocationAddressDao(
						LocationAddressListScreen.this);
				List<ningbq.bean.Location> list = dao.getNearByLocations(temp1,
						temp2, 1.0E-3);
				if (list == null) {
					return;
				}

				for (int i = 0, j = list.size(); i < j; i++) {
					ningbq.bean.Location temp = list.get(i);
					GeoPoint tempPoint = new GeoPoint(
							(int) (temp.getLatitude() * 1E6),
							(int) (temp.getLongitude() * 1E6));

					double distance = CalculateDistance.getDistance(temp1,
							temp2, temp.getLatitude(), temp.getLongitude());

					listPoint.add(tempPoint);
					listDistance.add(distance);
				}
				mhandler.sendEmptyMessage(LOCATION_ADDRESS_NEARBY);
			} else {
				loc = new GeoPoint(0, 0);
				mhandler.sendEmptyMessage(LOCATION_ADDRESS_NEARBY_NULL);
			}
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewGroup listMap = (ViewGroup) appView
				.findViewById(R.id.locationListViewContent);
		ImageView imageView = (ImageView) viewGroup
				.findViewById(R.id.locationListBtnSlide);
		ViewGroup addLocationGroup = (ViewGroup) appView
				.findViewById(R.id.locactionListViewForButton);
		but_addLocation = (Button) addLocationGroup
				.findViewById(R.id.ButAddLocation);
		but_NearbyList = (Button) viewGroup
				.findViewById(R.id.nearbyLocationList);

		imageView.setOnClickListener(new ClickListenerForScrolling(scrollView,
				menuView));

		children = new View[] { menuView, appView };
		scrollView.initViews(children, 1, new SizeCallbackForMenu(imageView));

		mapView = (MapView) listMap.findViewById(R.id.locationListScreenMap);

		Criteria locCriteria = new Criteria();
		locCriteria.setAccuracy(Criteria.ACCURACY_FINE);
		locMgr = (LocationManager) getSystemService(LOCATION_SERVICE);
		locMgr.requestLocationUpdates(
				locMgr.getBestProvider(locCriteria, true), 2000, 25, this);
		currentLocation = locMgr.getLastKnownLocation(locMgr.getBestProvider(
				locCriteria, true));

		this.mapController = mapView.getController();
		this.mapController.setZoom(16); // Set zoom grade
		myLoc = new MyLocationOverlay(getBaseContext(), mapView);
		myLoc.enableCompass();
		myLoc.enableMyLocation();

		dataThread = new LoadNearbyDataThread();
		dataThread.start();
		Log.v("lat", currentLocation.getLatitude() + "") ;
		Log.v("lon", currentLocation.getLongitude() + "") ;
		
		but_NearbyList.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putDouble("currentLat",currentLocation.getLatitude());//29.8688295)
				bundle.putDouble("currentLon",currentLocation.getLongitude());//121.5497843)
				intent.putExtras(bundle);
				intent.setClass(LocationAddressListScreen.this,
						LocationListScreen.class);
				startActivity(intent);
			}
		});
		but_addLocation.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(LocationAddressListScreen.this,
						LocationAddressScreen.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public int getApplicationLayoutResource() {
		return R.layout.list_location_address;
	}

	@Override
	public int getViewGroupResource() {

		return R.id.locationListViewHeadBar;
	}

	@Override
	protected void onResume() {

		super.onResume();
		locMgr.requestLocationUpdates(
				locMgr.getBestProvider(
						LocationSettings.getDefaultCriteriaSetting(), true),
				1000, 1, this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		locMgr.removeUpdates(this);

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		((LocationManager) getSystemService(LOCATION_SERVICE))
				.removeUpdates(this);
	}

	private class ItemOverlay extends ItemizedOverlay<OverlayItem> {
		private ArrayList<OverlayItem> items = new ArrayList<OverlayItem>();
		private Context context;
		private Drawable marker;

		public ItemOverlay(Drawable defaultMarker, Context context) {
			super(boundCenterBottom(defaultMarker));
			this.context = context;
			this.marker = defaultMarker;
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
		// 处理当点击事件
		protected boolean onTap(int i) {
			if (popView != null) {
				mapView.removeView(popView);
			} else {
				getLayoutInflater().inflate(R.layout.map_popup, mapView, false);
			}

			OverlayItem item = items.get(i);
			TextView popup = (TextView) (getLayoutInflater().inflate(
					R.layout.map_popup, mapView, false));
			popup.setText(item.getTitle());
			MapView.LayoutParams mapParams = new MapView.LayoutParams(
					MapView.LayoutParams.WRAP_CONTENT,
					MapView.LayoutParams.WRAP_CONTENT, item.getPoint(), 0, // x-offset
					-40, MapView.LayoutParams.BOTTOM_CENTER);

			mapView.addView(popup, mapParams);
			popView = popup;
			return true;
		}

	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		setUpdateLocation(location);

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	private void setUpdateLocation(Location newLocation) {
		currentLocation = newLocation;
		double temp1 = newLocation.getLatitude();
		double temp2 = newLocation.getLongitude();
		loc = new GeoPoint((int) (temp1 * 1E6), (int) (temp2 * 1E6));
		mapController.animateTo(loc);
	}

}
