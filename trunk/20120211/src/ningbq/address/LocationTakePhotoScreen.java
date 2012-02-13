package ningbq.address;

import ningbq.application.BaiduMapApiApplication;
import ningbq.main.R;
import ningbq.util.CheckNetwork;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.MapActivity;
import com.baidu.mapapi.MapController;
import com.baidu.mapapi.MapView;

public class LocationTakePhotoScreen extends MapActivity implements
		android.view.View.OnClickListener {

	MapView mapView = null;

	private TextView txt_lat = null;
	private TextView txt_lon = null;
	private GeoPoint geoPoint = null;
	private Location location = null;
	private ImageView but_Image = null;
	private LocationInfoProgress locationInfoProgress = new LocationInfoProgress();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// remove the title.
		setContentView(R.layout.location_take_photo);
		createLayout();
		locationInfoProgress.execute();

		BaiduMapApiApplication app = (BaiduMapApiApplication) this
				.getApplication();
		if (app.mBMapMgr == null) {
			app.mBMapMgr = new BMapManager(getApplication());
			app.mBMapMgr.init(BaiduMapApiApplication.BAIDU_KEY,
					new BaiduMapApiApplication.MyGeneralListener());
		}
		app.mBMapMgr.start();
		super.initMapActivity(app.mBMapMgr);

		mapView = (MapView) findViewById(R.id.locationBigMap);
		MapController mMapController = mapView.getController();
		mMapController.setZoom(14); // Set zoom grade
		if (location != null) {
			double tmpLat = location.getLatitude();
			double tmpLon = location.getLongitude();
			geoPoint = new GeoPoint((int) (tmpLat * 1E6), (int) (tmpLon * 1E6));
			mMapController.setCenter(geoPoint);
		}

	}

	public void createLayout() {
		txt_lat = (TextView) findViewById(R.id.edtLocationLat);
		txt_lon = (TextView) findViewById(R.id.edtLocationLon);
		but_Image = (ImageView) findViewById(R.id.ButImage);
		but_Image.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.ButImage:
			intent.setClass(this, SelectionTakePhotoScreen.class);
			startActivity(intent);
			overridePendingTransition(R.anim.selection_take_photo_fadein,
					R.anim.selection_take_photo_fadeout);
			break;
		default:
			break;
		}
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

	class LocationInfoProgress extends AsyncTask<Void, Void, Boolean> implements
			LocationListener {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			if (!CheckNetwork.openGPS(LocationTakePhotoScreen.this)) {
				AlertDialog dialog = new AlertDialog.Builder(
						LocationTakePhotoScreen.this)
						.setTitle(
								getResources().getString(
										R.string.location_dialog_setting))
						.setPositiveButton(R.string.location_dialog_button_ok,
								onClick)
						.setNegativeButton(
								R.string.location_dialog_button_cancel, onClick)
						.create();
			}
		}

		@Override
		protected Boolean doInBackground(Void... params) {
			boolean flag = false;
			LocationManager locationManager = null;
			try {
				String serviceName = Context.LOCATION_SERVICE;
				locationManager = (LocationManager) getSystemService(serviceName);
				location = locationManager
						.getLastKnownLocation(LocationManager.GPS_PROVIDER);
				updateToNewLocation(location);
				locationManager.requestLocationUpdates(
						LocationManager.GPS_PROVIDER, 0, 0, this);

				flag = true;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return flag;
		}

		private void updateToNewLocation(Location location) {
			if (location != null) {
				double latitude = location.getLatitude();
				double longitude = location.getLongitude();
				txt_lat.setText(String.valueOf(latitude));
				txt_lon.setText(String.valueOf(longitude));
			} else {
				Toast.makeText(LocationTakePhotoScreen.this, "获取定位地址失败!",
						Toast.LENGTH_LONG).show();
			}
		}

		@Override
		public void onLocationChanged(Location location) {

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

		OnClickListener onClick = new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch (which) {
				case DialogInterface.BUTTON_POSITIVE:
					Intent intent = new Intent(
							Settings.ACTION_SECURITY_SETTINGS);
					startActivityForResult(intent, 0);
					break;
				case DialogInterface.BUTTON_NEGATIVE:
					finish();
					break;
				}
			}
		};

	}

}
