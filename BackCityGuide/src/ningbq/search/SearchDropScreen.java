package ningbq.search;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import ningbq.bean.FirstCategory;
import ningbq.util.RestClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ExpandableListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class SearchDropScreen extends ExpandableListActivity {

	private static final String TAG = "SearchDropScreen";
	private ExpandableListAdapter expandableListAdapter;

	private ArrayList<String> firstCategoryList = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		expandableListAdapter = new MyExpandableListAdapter();
		setListAdapter(expandableListAdapter);
		registerForContextMenu(getExpandableListView());
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {

	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		return false;

	}

	class MyExpandableListAdapter extends BaseExpandableListAdapter {

		@Override
		public Object getChild(int groupPosition, int childPosition) {
			return null;
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			return 0;
		}

		@Override
		public View getChildView(int groupPosition, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {
			return null;
		}

		@Override
		public int getChildrenCount(int groupPosition) {
			return 0;
		}

		@Override
		public Object getGroup(int groupPosition) {
			return null;
		}

		@Override
		public int getGroupCount() {
			return 0;
		}

		@Override
		public long getGroupId(int groupPosition) {
			return 0;
		}

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			return null;
		}

		@Override
		public boolean hasStableIds() {
			return false;
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			return false;
		}

	}

	class SearchProcess extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			try {
				Map<String, String> allFirstCategory = new HashMap<String, String>();
				JSONObject jsonObject = RestClient
						.get("http://192.168.1.100:8080/category/first/showAll");

				JSONArray jsonArray = jsonObject.getJSONArray("firstCategory");
				Gson gson = new Gson();
				Type type = new TypeToken<List<FirstCategory>>() {
				}.getType();
				ArrayList<FirstCategory> listAll = gson.fromJson(
						jsonArray.toString(), type);
				for (FirstCategory temp : listAll) {
					String country = Locale.getDefault().getCountry();
					if ("CN".equalsIgnoreCase(country)) {
						allFirstCategory.put(temp.getFirstId(), temp.getName_cn());

						Log.i(TAG, "[name_cn] : " + temp.getName_cn());
					} else {
						allFirstCategory.put(temp.getFirstId(), temp.getName_en());
						Log.i(TAG, "[name_en] : " + temp.getName_en());
					}
				}

				// 处理二级栏目信息

				Iterator<Entry<String, String>> it = allFirstCategory
						.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry<String, String> entry = it.next();
					String key = entry.getKey();
					String value = entry.getValue();
					firstCategoryList.add(value) ;//一级栏目
					StringBuffer buffer = new StringBuffer();
					JSONObject secondJson = RestClient
							.get(buffer
									.append("http://192.168.1.100:8080/category/second/show/")
									.append(key).toString());
					
					
					
				}

			} catch (JSONException e) {
			}
			return null;
		}

	}

}
