package ningbq.search;

import java.util.ArrayList;
import java.util.List;

import ningbq.Constant.Constaints;
import ningbq.application.BaiduMapApiApplication;
import ningbq.main.R;
import ningbq.service.FavoriteService;
import ningbq.util.CalculateDistance;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mapabc.mapapi.GeoPoint;
import com.mapabc.mapapi.ItemizedOverlay;
import com.mapabc.mapapi.MapActivity;
import com.mapabc.mapapi.MapController;
import com.mapabc.mapapi.MapView;
import com.mapabc.mapapi.OverlayItem;
import com.mapabc.mapapi.Projection;

public class LocationDetailScreen extends MapActivity implements
		OnClickListener {

	private static final String TAG = "LocationDetailScreen";
	static MapView mapView = null;
	static View popView = null;
	private GeoPoint point = null;

	private TextView txt_LocationName = null;
	private TextView txt_LocationTel = null;
	private TextView txt_LocationAddress = null;
	private TextView txt_Fev = null;
	private TextView txt_BeFevUserCount = null;
	private TextView txt_EventsCount = null;
	private String locationName = null;
	private String locationAddress = null;
	private String locationId = null;
	private Button locationDetailSliding = null;
	private Button locationDetailScreenMap = null;
	private Button following_btn = null;
	private FavoriteService fService = new FavoriteService();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.locationdetail);

		Bundle bundel = getIntent().getExtras();
		locationId = bundel.getString("locationId");
		// locatoinService = new LocationService();
		// location = locatoinService.getLocationById(locationId);
		locationName = bundel.getString("locationName");
		locationAddress = bundel.getString("locationAddress");
		String locationTel = bundel.getString("locationTel");
		Double lat = Double.valueOf(bundel.getString("locationLat"));
		Double lon = Double.valueOf(bundel.getString("locationLon"));
		createLayout();

		// 计算距离
		Double distance = CalculateDistance.getDistance(Constaints.LATITUDE,
				Constaints.LONGITUDE, lat, lon);

		StringBuffer buffer = new StringBuffer();
		buffer.append(distance);
		buffer.append("km");

		mapView = (MapView) findViewById(R.id.locationMapId);

		// mapView.setBuiltInZoomControls(true);

		MapController mMapController = mapView.getController();
		Log.i(TAG, "Lat:" + lat + " lon:" + lon.intValue());

		point = new GeoPoint((int) (lat * 1E6), (int) (lon * 1E6));
		mMapController.setCenter(point); // Set center point
		mMapController.setZoom(14); // Set zoom grade

		// 添加ItemizedOverlay
		Drawable marker = getResources().getDrawable(R.drawable.iconmarka); // 得到需要标在地图上的资源
		marker.setBounds(0, 0, marker.getIntrinsicWidth(),
				marker.getIntrinsicHeight()); // 为maker定义位置和边界

		popView = getLayoutInflater().inflate(R.layout.popview, null);
		mapView.getOverlays().add(new OverItemT(marker, this));
		mapView.addView(popView, new MapView.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, null,
				MapView.LayoutParams.TOP_LEFT));
		popView.setVisibility(View.GONE);

		txt_LocationName.setText(locationName);
		txt_LocationAddress.setText(locationAddress);

		txt_Fev.setText(buffer.toString());
		txt_LocationTel.setText(locationTel);
		txt_BeFevUserCount.setText("10000");
		txt_EventsCount.setText("100");

	}

	private void createLayout() {
		txt_LocationName = (TextView) findViewById(R.id.txtLocationName);
		txt_LocationTel = (TextView) findViewById(R.id.txtLocationTel);
		txt_LocationAddress = (TextView) findViewById(R.id.txtLocationAddress);
		txt_Fev = (TextView) findViewById(R.id.txtFev);
		txt_BeFevUserCount = (TextView) findViewById(R.id.txtBeFevUserCount);
		txt_EventsCount = (TextView) findViewById(R.id.txtEventsCount);

		locationDetailSliding = (Button) findViewById(R.id.locationDetailSliding);
		locationDetailScreenMap = (Button) findViewById(R.id.locationDetailScreenMap);
		following_btn = (Button) findViewById(R.id.following_btn);

		locationDetailSliding.setOnClickListener(this);
		locationDetailScreenMap.setOnClickListener(this);
		following_btn.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.locationDetailSliding:
			finish();
			break;
		case R.id.locationDetailScreenMap:
			intent.setClass(LocationDetailScreen.this, BigMapScreen.class);
			int lat1 = point.getLatitudeE6();
			int lon1 = point.getLongitudeE6();
			Bundle bundle = new Bundle();
			bundle.putInt("lat1", lat1);
			bundle.putInt("lon1", lon1);
			bundle.putString("locAddress", locationAddress);
			intent.putExtras(bundle);
			startActivity(intent);
		case R.id.following_btn:
			String json = fService.isExistsFavorite(String.valueOf(12),
					locationId);
			if (Boolean.getBoolean(json)) {
				following_btn.setText(getString(R.string.locationFavorite));
			} else {
				fService.addFavorite(String.valueOf(12), locationId,
						locationName);// 添加收藏
				following_btn.setText(getString(R.string.locationNoFavorite));
			}
			Log.i(TAG, "[json:" + getString(R.string.locationFavorite) + "]");
		default:
			break;
		}
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}


	class OverItemT extends ItemizedOverlay<OverlayItem> {

		private List<OverlayItem> geoList = new ArrayList<OverlayItem>();
		private Context context;
		private Drawable marker;

		public OverItemT(Drawable marker, Context context) {
			super(boundCenterBottom(marker));
			this.context = context;
			this.marker = marker;

			geoList.add(new OverlayItem(point, "", locationAddress));
			geoList.add(new OverlayItem(new GeoPoint(
					(int) (Constaints.LATITUDE * 1E6),
					(int) (Constaints.LONGITUDE * 1E6)), "", ""));
			Log.i(TAG, "[LAT:" + Constaints.LATITUDE + " LON:]"
					+ Constaints.LONGITUDE);
			populate();
		}

		@Override
		public void draw(Canvas canvas, MapView mapView, boolean shadow) {

			// Projection接口用于屏幕像素坐标和经纬度坐标之间的变换
			Projection projection = mapView.getProjection();
			for (int index = size() - 1; index >= 0; index--) { // 遍历mGeoList
				OverlayItem overLayItem = getItem(index); // 得到给定索引的item

				String title = overLayItem.getTitle();
				// 把经纬度变换到相对于MapView左上角的屏幕像素坐标
				Point point = projection.toPixels(overLayItem.getPoint(), null);

				// 可在此处添加您的绘制代码
				Paint paintText = new Paint();
				paintText.setColor(Color.BLUE);
				paintText.setTextSize(15);
				canvas.drawText(title, point.x - 30, point.y, paintText); // 绘制文本
			}

			super.draw(canvas, mapView, shadow);
			// 调整一个drawable边界，使得（0，0）是这个drawable底部最后一行中心的一个像素
			boundCenterBottom(marker);
		}

		@Override
		protected OverlayItem createItem(int positon) {
			return geoList.get(positon);
		}

		@Override
		public int size() {
			return geoList.size();
		}

		@Override
		// 处理当点击事件
		protected boolean onTap(int i) {
			setFocus(geoList.get(i));
			GeoPoint pt = geoList.get(i).getPoint();
			LocationDetailScreen.mapView.updateViewLayout(
					LocationDetailScreen.popView, new MapView.LayoutParams(
							LayoutParams.WRAP_CONTENT,
							LayoutParams.WRAP_CONTENT, pt,
							MapView.LayoutParams.BOTTOM_CENTER));

			Toast.makeText(this.context, geoList.get(i).getSnippet(),
					Toast.LENGTH_SHORT).show();
			return true;
		}

		@Override
		public boolean onTap(GeoPoint arg0, MapView arg1) {
			LocationDetailScreen.popView.setVisibility(View.GONE);
			return super.onTap(arg0, arg1);
		}

	}

}
