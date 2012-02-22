package ningbq.address;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import ningbq.bean.Location;
import ningbq.db.dao.LocationAddressDao;
import ningbq.main.R;
import ningbq.util.CalculateDistance;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class LocationListScreen extends Activity {

	private static final String TAG = "LocationListScreen";
	private static final int LOCATION_LIST_SCREEN = 20;
	private ImageView but_Back = null;
	private SimpleAdapter locationSimpleAdapter;
	private ListView listView = null;
	private double currentLat = 0.0;
	private double currentLon = 0.0;
	private LoadNearbyLocation nearbyThread ;

	private Handler handle = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case LOCATION_LIST_SCREEN:
				listView.setAdapter(locationSimpleAdapter) ;
				listView.setDivider(getResources().getDrawable(
						R.drawable.divider_new));
				listView.setDividerHeight(2);
				listView.setSelector(R.drawable.onbar_new);
				listView.setCacheColorHint(0);
				break;
			default:
				break;
			}
		}
	};

	class LoadNearbyLocation extends Thread {
		@Override
		public void run() {
			locationSimpleAdapter = new SimpleAdapter(LocationListScreen.this,
					fillList(), R.layout.nearby_location, new String[] {
							"locationName", "locationAddress",
							"locationDistance" }, new int[] {
							R.id.nearybyLocationName,
							R.id.nearybyLocationAddress,
							R.id.nearybyLocationDistance });
			handle.sendEmptyMessage(LOCATION_LIST_SCREEN) ;
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.list_location_address_map);
		Bundle bundle = getIntent().getExtras();
		currentLat = bundle.getDouble("currentLat");
		currentLon = bundle.getDouble("currentLon");
		createLayout(this);
		nearbyThread = new LoadNearbyLocation() ;
		nearbyThread.start() ;
	}

	private void createLayout(Context ctx) {
		but_Back = (ImageView) findViewById(R.id.locationListBtnSlide_List);
		listView = (ListView) findViewById(R.id.addLactionViewList_List);
		but_Back.setOnClickListener(butOnClick);
	}

	OnClickListener butOnClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.locationListBtnSlide_List:
				finish();
				break;
			default:
				break;
			}
		}
	};

	private ArrayList<HashMap<String, String>> fillList() {
		LocationAddressDao dao = new LocationAddressDao(LocationListScreen.this);
		ArrayList<HashMap<String, String>> temp = new ArrayList<HashMap<String, String>>();
		List<Location> lTempList = dao.getNearByLocations(currentLat,
				currentLon, 1.0E-3);

		int lSize = lTempList.size();
		Log.i(TAG, "size:" + lSize);
		String country = Locale.getDefault().getCountry();// 获取当前系统的语言
		for (int i = 0; i < lSize; i++) {
			HashMap<String, String> tempMap = new HashMap<String, String>();
			Location fc = lTempList.get(i);

			// tempMap.put("locationId", fc.getLocationId());
			if ("CN".equalsIgnoreCase(country)) {
				tempMap.put("locationName", fc.getName_cn());
				tempMap.put("locationAddress", fc.getAddress_cn());
			} else {
				tempMap.put("locationName", fc.getName_en());
				tempMap.put("locationAddress", fc.getAddress_en());
			}

			double locationDistance = CalculateDistance.getDistance(currentLat,
					currentLon, fc.getLatitude(), fc.getLongitude());

			tempMap.put("locationDistance", String.valueOf(locationDistance));
			temp.add(tempMap);
		}
		return temp;
	}

}
