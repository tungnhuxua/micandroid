package ningbq.search;

import java.util.ArrayList;
import java.util.List;

import ningbq.Constant.Constaints;
import ningbq.application.BaiduMapApiApplication;
import ningbq.main.R;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Toast;

import com.mapabc.mapapi.GeoPoint;
import com.mapabc.mapapi.ItemizedOverlay;
import com.mapabc.mapapi.MapActivity;
import com.mapabc.mapapi.MapController;
import com.mapabc.mapapi.MapView;
import com.mapabc.mapapi.OverlayItem;
import com.mapabc.mapapi.Projection;

public class BigMapScreen extends MapActivity{
	
	MapView mapView = null;
	GeoPoint point = null ;
	String locationAddress = null ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.bigmap);
		
		Bundle bundle = getIntent().getExtras() ;
		int lat2 = bundle.getInt("lat1") ;
		int lon2 = bundle.getInt("lon1") ;
		locationAddress = bundle.getString("locAddress") ;
		
		
		mapView = (MapView) findViewById(R.id.locationBigMap);
		MapController mMapController = mapView.getController();
		point = new GeoPoint(lat2,lon2);
		mMapController.setCenter(point); // Set center point
		mMapController.setZoom(14); // Set zoom grade
		

		// 添加ItemizedOverlay
		Drawable marker = getResources().getDrawable(R.drawable.iconmarka); // 得到需要标在地图上的资源
		marker.setBounds(0, 0, marker.getIntrinsicWidth(),
				marker.getIntrinsicHeight()); // 为maker定义位置和边界
		mapView.getOverlays().add(new OverItemT(marker, this));
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
					(int) (Constaints.LONGITUDE * 1E6)), "", "我的位置"));
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
