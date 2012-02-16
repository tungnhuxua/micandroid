package org.phox.where.view;

import java.text.SimpleDateFormat;
import java.util.*;

import org.phox.where.R;
import org.phox.where.controller.BaiduMapApiApplication;
import org.phox.where.model.HistoryItem;
import org.phox.where.util.DateTimeUtil;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.ItemizedOverlay;
import com.baidu.mapapi.MapActivity;
import com.baidu.mapapi.MapController;
import com.baidu.mapapi.MapView;
import com.baidu.mapapi.OverlayItem;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.TextView;

public class HistoryMapView extends MapActivity {
	
	private MapView mapView;
	private MapController mapController;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_map);
        
    	BaiduMapApiApplication app = (BaiduMapApiApplication) this
				.getApplication();
		if (app.mBMapMgr == null) {
			app.mBMapMgr = new BMapManager(this.getApplicationContext());
			app.mBMapMgr.init(BaiduMapApiApplication.BAIDU_KEY,
					new BaiduMapApiApplication.MyGeneralListener());
		}
		app.mBMapMgr.start() ;
		initMapActivity(app.mBMapMgr) ;
		//
		// Set up map view
		//
		
		this.mapView = (MapView)findViewById(R.id.mapHistoryView);
		this.mapController = this.mapView.getController();
		
		
		//
		// Overlay user location and item location
		//
		
		Intent i = getIntent();
        HistoryItem item = i.getParcelableExtra("history_item");
        
        if (item != null)
        {
        	GeoPoint loc = new GeoPoint((int)(item.latitude * 1e6), (int)(item.longitude * 1e6));
        	
        	ItemOverlay itemOverlay = new ItemOverlay(getResources().getDrawable(R.drawable.map_marker), this);
        	itemOverlay.addOverlay(new OverlayItem(loc, "" + item.date, ""));
        	this.mapView.getOverlays().add(itemOverlay);
        	
        	String dateString = DateTimeUtil.getFuzzyTimeDelta(item.date, System.currentTimeMillis(), 1000*60*60*24*3);
			String timeString = new SimpleDateFormat("h:mm a").format(new Date(item.date)).toLowerCase();
			String addressString = item.getAddressString(this);
			String elapsedString = "For " + DateTimeUtil.getFuzzyTime(item.elapsed_time);
			
			setTextForResource(R.id.dateTextView, dateString);
			setTextForResource(R.id.timeTextView, timeString);
			setTextForResource(R.id.addressTextView, addressString);
			setTextForResource(R.id.elapsedTextView, elapsedString);
			
			this.mapController.setCenter(loc);
			this.mapController.setZoom(14) ;
			//this.mapController.zoomToSpan(100000, 100000);
		}
    }
    
    private void setTextForResource(int resId, String text)
	{
		TextView tv = (TextView)(findViewById(resId));
		tv.setText(text);
	}
    
    @Override
	protected boolean isRouteDisplayed() {
		return false;
	}
    
    private class ItemOverlay extends ItemizedOverlay<OverlayItem>
	{
		private ArrayList<OverlayItem> items = new ArrayList<OverlayItem>();
		private Context context;
		
		public ItemOverlay(Drawable defaultMarker, Context context) 
		{
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
	}
    
}