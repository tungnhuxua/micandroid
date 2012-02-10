package ningbq.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import ningbq.bean.Location;
import ningbq.main.R;
import ningbq.service.LocationService;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class LocationScreen extends Activity {

	private static final String TAG = "LocationScreen";

	// private SearchFirstCategoryProcess process;
	private ListView listView;
	private Button locationSliding = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.locationview);
		Bundle bundle = getIntent().getExtras();
		String secondId = bundle.getString("secondId");

		listView = (ListView) findViewById(R.id.locationList);

		SimpleAdapter adapter = new SimpleAdapter(this, fillList(secondId),
				R.layout.listlocations, new String[] { "locationId",
						"locationName" }, new int[] { R.id.txtLocationId,
						R.id.txtLocationName });

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long rowId) {
				@SuppressWarnings("unchecked")
				HashMap<String, String> map = (HashMap<String, String>) listView
						.getAdapter().getItem(position);
				String locationId = map.get("locationId");
				String locationName = map.get("locationName");
				String locationAddress = map.get("locationAddress");
				String locationTel = map.get("locationTel");
				String locationLat = map.get("locationLat");
				String locationLon = map.get("locationLon");
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putString("locationId", locationId);
				bundle.putString("locationName", locationName);
				bundle.putString("locationAddress", locationAddress);
				bundle.putString("locationTel", locationTel);
				bundle.putString("locationLat", locationLat);
				bundle.putString("locationLon", locationLon);

				intent.putExtras(bundle);
				intent.setClass(LocationScreen.this, LocationDetailScreen.class);
				startActivity(intent);
				Log.i(TAG,
						"From LocationScreen send data to LocationDetailScreen SUCCESS !");
			}
		});

		listView.setAdapter(adapter);

		locationSliding = (Button) findViewById(R.id.locationSliding);
		locationSliding.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				/*startActivity(new Intent(LocationScreen.this,
						SearchSecondCategoryScreen.class));*/
				finish() ;
			}
		});
	}

	private ArrayList<HashMap<String, String>> fillList(String secondId) {
		LocationService locationService = new LocationService();
		ArrayList<HashMap<String, String>> temp = new ArrayList<HashMap<String, String>>();
		ArrayList<Location> tempList = locationService
				.getLocationsAll(secondId);
		if (tempList == null) {
			return null;
		}
		int size = tempList.size();
		String country = Locale.getDefault().getCountry();// 获取当前系统的语言
		for (int i = 0; i < size; i++) {
			HashMap<String, String> tempMap = new HashMap<String, String>();
			Location loc = tempList.get(i);
			tempMap.put("locationId", loc.getId());
			if ("CN".equalsIgnoreCase(country)) {
				tempMap.put("locationName", loc.getName_cn());
				tempMap.put("locationAddress", loc.getAddress_cn());
			} else {
				tempMap.put("locationName", loc.getName_en());
				tempMap.put("locationAddress", loc.getAddress_en());
			}
			tempMap.put("locationTel", loc.getTelephone());
			tempMap.put("locationLon", loc.getLongitude().toString());
			tempMap.put("locationLat", loc.getLatitude().toString());

			temp.add(tempMap);
		}
		return temp;
	}

}
