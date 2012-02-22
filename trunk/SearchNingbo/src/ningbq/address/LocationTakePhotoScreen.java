package ningbq.address;

import java.io.ByteArrayOutputStream;
import java.io.File;

import ningbq.application.BaiduMapApiApplication;
import ningbq.main.R;
import ningbq.util.CheckNetwork;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.MapActivity;
import com.baidu.mapapi.MapController;
import com.baidu.mapapi.MapView;

public class LocationTakePhotoScreen extends MapActivity {

	MapView mapView = null;
	private TextView txt_lat = null;
	private TextView txt_lon = null;
	private GeoPoint geoPoint = null;
	private Location location = null;
	private ImageView but_Image = null;
	private static final int LOCAL_PHOTO = 100; // 本地相册
	private static final int TAKE_PHOTO = 101; // 拍照

	private static final int PHOTO_GRAPH = 1;// 拍照
	private static final int PHOTO_ZOOM = 2; // 缩放
	private static final int PHOTO_RESOULT = 3;// 结果
	private static final int NONE = 0;
	private static final String IMAGE_UNSPECIFIED = "image/*";
	private LocationInfoProgress locationInfoProgress = new LocationInfoProgress();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// remove the title.
		setContentView(R.layout.location_take_photo);
		createLayout();
		registerForContextMenu(but_Image) ;
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

	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == NONE) {
			return;
		}

		if (requestCode == PHOTO_GRAPH) {// 拍照
			// 设置文件保存路径
			File picture = new File(Environment.getExternalStorageDirectory()
					+ "/temp.jpg");
			// File picture = new File(getSystemDefaultHeaderPath() +
			// "header.jpg") ;
			startPhotoZoom(Uri.fromFile(picture));
		}

		if (data == null)
			return;

		if (requestCode == PHOTO_ZOOM) {// 读取相册缩放图片
			startPhotoZoom(data.getData());
		}

		if (requestCode == PHOTO_RESOULT) {// 处理结果
			Bundle extras = data.getExtras();
			if (extras != null) {
				Bitmap photo = extras.getParcelable("data");
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				photo.compress(Bitmap.CompressFormat.JPEG, 75, stream);// (0-100)压缩文件
				// 此处可以把Bitmap保存到sd卡中，
				but_Image.setImageBitmap(photo); // 把图片显示在ImageView控件上
			}

		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		Intent intent = null;
		switch (item.getItemId()) {
		case TAKE_PHOTO:
			intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(
					Environment.getExternalStorageDirectory(), "temp.jpg")));
			// intent.putExtra(MediaStore.ACTION_IMAGE_CAPTURE,
			// Uri.fromFile(new File(getSystemDefaultHeaderPath(),
			// "header.jpg")));
			startActivityForResult(intent, PHOTO_GRAPH);
			break;
		case LOCAL_PHOTO:
			intent = new Intent(Intent.ACTION_PICK, null);
			intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
					IMAGE_UNSPECIFIED);
			startActivityForResult(intent, PHOTO_ZOOM);
			break;
		default:
			break;
		}
		return true;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		menu.setHeaderTitle(R.string.headImage_title);
		// menu.set

		menu.add(0, TAKE_PHOTO, 0, R.string.graph_photo);
		menu.add(0, LOCAL_PHOTO, 0, R.string.local_photo);
		// super.onCreateContextMenu(menu, v, menuInfo);
	}
	
	/**
	 * 收缩图片
	 * 
	 * @param uri
	 */
	public void startPhotoZoom(Uri uri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, IMAGE_UNSPECIFIED);
		intent.putExtra("crop", "true");
		// aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// outputX outputY 是裁剪图片宽高
		intent.putExtra("outputX", 100);
		intent.putExtra("outputY", 100);
		intent.putExtra("return-data", true);
		startActivityForResult(intent, PHOTO_RESOULT);
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
				txt_lat.setText(String.valueOf(0.0));
				txt_lon.setText(String.valueOf(0.0));
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
