package ningbq.search;

import java.util.ArrayList;
import java.util.List;

import ningbq.application.BaiduMapApiApplication;
import ningbq.main.R;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.ItemizedOverlay;
import com.baidu.mapapi.MapActivity;
import com.baidu.mapapi.MapController;
import com.baidu.mapapi.MapView;
import com.baidu.mapapi.OverlayItem;
import com.baidu.mapapi.Projection;

public class LocationDetailScreen extends MapActivity {

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
	private Button locationDetailSliding = null ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.locationdetail);
		// 百度地图
		BaiduMapApiApplication app = (BaiduMapApiApplication) this
				.getApplication();
		if (app.mBMapMgr == null) {
			app.mBMapMgr = new BMapManager(getApplication());
			app.mBMapMgr.init(BaiduMapApiApplication.BAIDU_KEY,
					new BaiduMapApiApplication.MyGeneralListener());
		}
		app.mBMapMgr.start() ;
		 // 如果使用地图SDK，请初始化地图Activity
		super.initMapActivity(app.mBMapMgr) ;

		Bundle bundel = getIntent().getExtras();
		// String locationId = bundel.getString("locationId");
		// locatoinService = new LocationService();
		// location = locatoinService.getLocationById(locationId);
		locationName = bundel.getString("locationName");
		locationAddress = bundel.getString("locationAddress");
		String locationTel = bundel.getString("locationTel");
		Double lat = Double.valueOf(bundel.getString("locationLat"));
		Double lon = Double.valueOf(bundel.getString("locationLon"));
		createLayout();

	
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
		txt_Fev.setText("已经收藏");
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
		locationDetailSliding = (Button)findViewById(R.id.locationDetailSliding) ;
		locationDetailSliding.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish() ;
			}
		}) ;

	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}


	@Override
	protected void onPause() {
		BaiduMapApiApplication app = (BaiduMapApiApplication)this.getApplication() ;
		app.mBMapMgr.stop() ;
		super.onPause();
	}

	@Override
	protected void onResume() {
		BaiduMapApiApplication app = (BaiduMapApiApplication)this.getApplication() ;
		app.mBMapMgr.start() ;
		super.onResume();
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
