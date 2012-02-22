package ningbq.search;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ningbq.main.MainScrrenWithRecentView;
import ningbq.main.R;
import ningbq.staic.SearchResultData;
import ningbq.util.SettingFont;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

/**
 * description:Search information results Screen.
 * 
 * @author Devon.Ning
 * 
 */
public class SearchResultsScreen extends ListActivity implements
		OnClickListener {

	private static final String SEARCH_RESULT = "SearchResultsScreen";

	private Button but_back_search;
	private Button but_home;
	private Button but_search;
	private Button but_back_map ;
	private TextView txt_heading ;

	private List<Map<String, Object>> mData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_results);
		initUI();
		mData = getData();
		// SearchResultsAdapter simple = new SearchResultsAdapter(this);

		SimpleAdapter simple = new SimpleAdapter(this, mData, R.layout.result,
				new String[] { "location", "distance", "localImage" },
				new int[] { R.id.location, R.id.distance, R.id.localImage });

		setListAdapter(simple);
	}

	private void initUI() {
		but_back_search = (Button) findViewById(R.id.ButBackSearch);
		but_home        = (Button) findViewById(R.id.Buthome);
		but_search      = (Button) findViewById(R.id.ButSerarch);
		but_back_map    = (Button)findViewById(R.id.ButBackMap) ;
		txt_heading     = (TextView)findViewById(R.id.txtHeading) ;

		but_back_search.setOnClickListener(this);
		but_home.setOnClickListener(this);
		but_back_map.setOnClickListener(this) ;

		but_search.setBackgroundDrawable(SettingFont.SettingBackgroundImage(
				R.drawable.search_on, this));
		but_home.setBackgroundDrawable(SettingFont.SettingBackgroundImage(
				R.drawable.home_off, this));
		
		Typeface typeFace = Typeface.createFromAsset(getAssets(),
				"fonts/Arial_Rounded_MT_Bold.ttf");
		but_back_search.setTypeface(typeFace) ;
		but_back_map.setTypeface(typeFace) ;
		txt_heading.setTypeface(typeFace) ;
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.ButBackSearch) {
			Intent it = new Intent(this, SearchScreen.class);
			startActivity(it);
		}

		if (v.getId() == R.id.Buthome) {
			Intent it = new Intent(this, MainScrrenWithRecentView.class);
			startActivity(it);
		}
		
		if(v.getId() == R.id.ButBackMap){
			Intent it = new Intent(this,SearchResultsOnMapScreen.class) ;
			//Bundle params = new Bundle() ;
			//params.putString("headTitle", "Hotels") ;
			//params.putCharSequenceArrayList("allResult", SearchResultData.getResultData()); 
			//params.putParcelableArrayList(key, value)
			
			//it.putExtras(params) ;
			it.putExtra("testMap", (Serializable)SearchResultData.getResultData()) ;
			startActivity(it) ;
		}
		
		
	}

	/** 模拟的数据 */
	private List<Map<String, Object>> getData() {
		ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("location", "Yao Hua Hotel");
		map.put("distance", "4.12km");
		map.put("localImage", R.drawable.silver_pin);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("location", "Jiu Feng Hotel");
		map.put("distance", "2.03km");
		map.put("localImage", R.drawable.silver_pin);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("location", "Wan'an Hotel");
		map.put("distance", "6.98km");
		map.put("localImage", R.drawable.silver_pin);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("location", "Honglian Hotel");
		map.put("distance", "10.98km");
		map.put("localImage", R.drawable.silver_pin);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("location", "Jangdong Hotel");
		map.put("distance", "0.26km");
		map.put("localImage", R.drawable.silver_pin);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("location", "Nanyuan Hotel");
		map.put("distance", "6.18km");
		map.put("localImage", R.drawable.silver_pin);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("location", "Kaiyuan Hotel");
		map.put("distance", "2.18km");
		map.put("localImage", R.drawable.silver_pin);
		list.add(map);

		return list;

	}

	public final class ViewHolder {
		public ImageView localImage;
		public TextView location;
		public TextView distance;
		// public Button btnMap ;
	}

	/** 自定义列表的适配器 */
	class SearchResultsAdapter extends BaseAdapter {

		private LayoutInflater mInflater;

		public SearchResultsAdapter(Context context) {
			this.mInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			return mData.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = mInflater.inflate(R.layout.result, null);
				holder.localImage = (ImageView) convertView
						.findViewById(R.id.localImage);
				holder.location = (TextView) convertView
						.findViewById(R.id.location);
				holder.distance = (TextView) convertView
						.findViewById(R.id.distance);
				// holder.btnMap = (Button)convertView.findViewById(R.id.btnMap)
				// ;

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			Log.v(SEARCH_RESULT, "获取localImage:" + mData.get(position));
			Map<String, Object> tempMap = mData.get(position);
			Log.v(SEARCH_RESULT, "localImage:" + tempMap.get("localImage"));
			Log.v(SEARCH_RESULT, "location:" + tempMap.get("location"));
			Log.v(SEARCH_RESULT, "distance:" + tempMap.get("distance"));

			holder.localImage.setBackgroundResource((Integer) tempMap
					.get("localImage"));
			holder.location.setText((String) tempMap.get("location"));
			holder.distance.setText((String) tempMap.get("distance"));
			/*
			 * holder.btnMap.setOnClickListener(new View.OnClickListener() {
			 * 
			 * @Override public void onClick(View v) {
			 * 
			 * } }) ;
			 */
			return convertView;
		}

	}

}
