package ningbq.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import ningbq.bean.Location;
import ningbq.db.SoNingboDatabase;
import ningbq.db.SoNingboTables.LocationTable;
import ningbq.db.dao.LocationAddressDao;
import ningbq.main.R;
import ningbq.service.LocationService;
import ningbq.util.CheckNetwork;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
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
	private String secondId = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.locationview);
		Bundle bundle = getIntent().getExtras();
		secondId = bundle.getString("secondId");

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
				finish();
			}
		});
	}

	private ArrayList<HashMap<String, String>> fillList(String secondId) {
		LocationService locationService = new LocationService();
		LocationAddressDao locationAddressDao = new LocationAddressDao(this) ;
		ArrayList<HashMap<String, String>> temp = new ArrayList<HashMap<String, String>>();

		if (CheckNetwork.check(this)) {// 网络连通.
			ArrayList<Location> rTempList = locationService
					.getLocationsAll(secondId);
			if (rTempList == null) {
				return null;
			}
			int rSize = rTempList.size();
			for (int i = 0; i < rSize; i++) {
				Location loc = rTempList.get(i);
				String locatioinId = loc.getLocationId();
				try {
					ContentValues values = new ContentValues();
					values.put("location_id", locatioinId);
					values.put("second_id", secondId);
					values.put("name_cn", loc.getName_cn());
					values.put("address_cn", loc.getAddress_cn());
					values.put("name_en", loc.getName_en());
					values.put("address_en", loc.getAddress_en());
					values.put("telephone", loc.getTelephone());
					values.put("longitude", loc.getLongitude());
					values.put("latitude", loc.getLatitude());

					SoNingboDatabase db = SoNingboDatabase.getInstance(this);
					Cursor cursor = db.getDb(false).query(
							LocationTable.TABLE_NAME, null, "location_id=?",
							new String[] { locatioinId }, null, null, null);
					if (cursor.getCount() > 0) {
						db.getDb(true).update(LocationTable.TABLE_NAME, values,
								"location_id=?", new String[] { locatioinId });
						Log.i(TAG, "update firstCategory.");
					} else {
						db.getDb(true).insert(LocationTable.TABLE_NAME, null,
								values);
						Log.i(TAG, "insert firstCategory.");
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		}

		//locationAddressDao
		List<Location> lTempList = locationAddressDao.getLocationsBySecondId(secondId) ;
		int lSize = lTempList.size() ;
		String country = Locale.getDefault().getCountry();// 获取当前系统的语言
		for (int i = 0; i < lSize; i++) {
			HashMap<String, String> tempMap = new HashMap<String, String>();
			Location loc = lTempList.get(i);
			String locatioinId = loc.getLocationId();
			tempMap.put("locationId", locatioinId);
			if ("CN".equalsIgnoreCase(country)) {
				tempMap.put("locationName", loc.getName_cn());
				tempMap.put("locationAddress", loc.getAddress_cn());
			} else {
				tempMap.put("locationName", loc.getName_en());
				tempMap.put("locationAddress", loc.getAddress_en());
			}
			tempMap.put("locationTel", loc.getTelephone());
			tempMap.put("locationLon", String.valueOf(loc.getLongitude()));
			tempMap.put("locationLat", String.valueOf(loc.getLatitude()));

			temp.add(tempMap);

		}
		return temp;
	}

}
