package ningbq.search;

import java.util.ArrayList;

import ningbq.main.R;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.MKAddrInfo;
import com.baidu.mapapi.MKDrivingRouteResult;
import com.baidu.mapapi.MKPoiInfo;
import com.baidu.mapapi.MKPoiResult;
import com.baidu.mapapi.MKSearch;
import com.baidu.mapapi.MKSearchListener;
import com.baidu.mapapi.MKTransitRouteResult;
import com.baidu.mapapi.MKWalkingRouteResult;
import com.baidu.mapapi.MapActivity;
import com.baidu.mapapi.MapController;
import com.baidu.mapapi.MapView;
import com.baidu.mapapi.PoiOverlay;
import com.baidu.mapapi.RouteOverlay;

public class SearchResultsOnMapScreen extends MapActivity {

	private static final String TAG = "SearchResultsOnMapScreen" ;
	private static final String BAIDU_KEY = "CB5CC089E3D8BFB17CF20A81FF235B35EBBC0A9A";
	private static final String DEFAULT_TITLE = "Search All";

	private BMapManager bMapMgr = null;
	private MapView mapView;
	private TextView search_result_name;
	private GeoPoint point  ;//Test data

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.show_results_on_map);
		bMapMgr = new BMapManager(getApplication());
		bMapMgr.init(BAIDU_KEY, null);
		super.initMapActivity(bMapMgr);
		mapView = (MapView) findViewById(R.id.results_on_maps);
		search_result_name = (TextView) findViewById(R.id.search_result_name);
		mapView.setBuiltInZoomControls(true);

		MapController mMapController = mapView.getController();
		point = new GeoPoint((int) (29.873074 * 1E6),
				(int) (121.560527 * 1E6)); // center point l OR H.
		mMapController.setCenter(point); // Set center point
		mMapController.setZoom(14); // Set zoom grade
		

		String title = null;
		Bundle bundle = getIntent().getExtras();
		// Bundle bundle =
		// (List<Location>)getIntent().getSerializableExtra("testMap") ;
		if (bundle != null) {
			title = bundle.getString("headTitle");
			search_result_name.setText(title);
		} else {
			search_result_name.setText(DEFAULT_TITLE);
		}

		Typeface typeFace = Typeface.createFromAsset(getAssets(),
				"fonts/Arial_Rounded_MT_Bold.ttf");
		search_result_name.setTypeface(typeFace);
		
		//检索
		MKSearch mkSearch = new MKSearch() ;
		mkSearch.init(bMapMgr, new MySearchListener()) ;
		mkSearch.poiSearchNearBy("宾馆", point, 5000) ;
		
		//驾车
		//MKPlanNode start = new MKPlanNode() ;
		//start.pt = point ;
		//MKPlanNode end = new MKPlanNode() ;
		//end.pt = new GeoPoint((int)(29.8733365 * 1E6), (int)(121.5443175 * 1E6)) ;
		//mkSearch.setDrivingPolicy(MKSearch.EBUS_TIME_FIRST) ;
		//mkSearch.drivingSearch(null, start, null, end) ;
		
	}

	@Override
	protected void onDestroy() {
		if (bMapMgr != null) {
			bMapMgr.destroy();
			bMapMgr = null;
		}
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		if (bMapMgr != null) {
			bMapMgr.stop();
		}
		super.onPause();
	}

	@Override
	protected void onResume() {
		if (bMapMgr != null) {
			bMapMgr.start();
		}
		super.onResume();
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	class MySearchListener implements MKSearchListener{

		@Override
		public void onGetAddrResult(MKAddrInfo arg0, int arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onGetDrivingRouteResult(MKDrivingRouteResult result, int iError) {
			if(result == null){
				return ;
			}
			RouteOverlay routeOverlay = new RouteOverlay(SearchResultsOnMapScreen.this,mapView) ;
			routeOverlay.setData(result.getPlan(0).getRoute(0)) ;
			mapView.getOverlays().add(routeOverlay) ;
			mapView.invalidate() ;
		}

		@Override
		public void onGetPoiResult(MKPoiResult result, int type, int iError) {
			if(result == null){
				return ;
			}
			PoiOverlay poiOverlay = new PoiOverlay(SearchResultsOnMapScreen.this,mapView) ;
			poiOverlay.setData(result.getAllPoi()) ;
			
			ArrayList<MKPoiInfo> info = result.getAllPoi() ;
			//set some test
			MKPoiInfo temp = info.get(0) ;
			Log.i(TAG, Integer.toString(temp.pt.getLatitudeE6())) ;
			Log.i(TAG, Integer.toString(temp.pt.getLongitudeE6())) ;
			
			
			mapView.getOverlays().add(poiOverlay) ;
			mapView.invalidate() ;
		}

		@Override
		public void onGetTransitRouteResult(MKTransitRouteResult arg0, int arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onGetWalkingRouteResult(MKWalkingRouteResult arg0, int arg1) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
