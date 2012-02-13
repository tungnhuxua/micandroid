package ningbq.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import ningbq.bean.SecondCategory;
import ningbq.db.SoNingboDatabase;
import ningbq.db.SoNingboTables.SecondCategoryTable;
import ningbq.db.dao.SecondCategoryDao;
import ningbq.main.R;
import ningbq.service.SecondCategoryService;
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
import android.widget.Toast;

public class SearchSecondCategoryScreen extends Activity {

	private static final String TAG = "SearchSecondCategoryScreen";

	// private SearchFirstCategoryProcess process;
	private ListView listView;
	private Button secondCategorySliding = null;
	private String firstId = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.secondcagetoryview);
		Bundle bundle = getIntent().getExtras();
		firstId = bundle.getString("firstId");

		listView = (ListView) findViewById(R.id.secondCategoryList);

		SimpleAdapter adapter = new SimpleAdapter(this, fillList(firstId),
				R.layout.listsecondcategory, new String[] { "categoryId",
						"categoryName" }, new int[] { R.id.txtSecondCategoryId,
						R.id.txtSecondCategoryName });
		listView.setDivider(getResources().getDrawable(R.drawable.divider_new));
		listView.setDividerHeight(2);
		listView.setSelector(R.drawable.onbar_new);
		listView.setCacheColorHint(0);

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long rowId) {
				@SuppressWarnings("unchecked")
				HashMap<String, String> map = (HashMap<String, String>) listView
						.getAdapter().getItem(position);
				String categoryName = map.get("categoryName");
				String categoryId = map.get("categoryId");
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putString("secondId", categoryId);
				intent.putExtras(bundle);
				intent.setClass(SearchSecondCategoryScreen.this,
						LocationScreen.class);
				startActivity(intent);
			}
		});

		listView.setAdapter(adapter);

		secondCategorySliding = (Button) findViewById(R.id.secondCategorySliding);
		secondCategorySliding.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	private ArrayList<HashMap<String, String>> fillList(String firstId) {
		SecondCategoryService fcServices = new SecondCategoryService();
		SecondCategoryDao secondCategoryDao = new SecondCategoryDao(this);
		ArrayList<HashMap<String, String>> temp = new ArrayList<HashMap<String, String>>();
		if (CheckNetwork.check(this)) {
			ArrayList<SecondCategory> rTempList = fcServices
					.getSecondCategoryAll(firstId);
			if (rTempList == null) {
				return null;
			}
			int rSize = rTempList.size();
			for (int i = 0; i < rSize; i++) {
				try {
					SecondCategory fc = rTempList.get(i);
					String secondId = fc.getSecondId();
					ContentValues values = new ContentValues();
					values.put("second_id", secondId);
					values.put("first_id", firstId);
					values.put("name_cn", fc.getName_cn());
					values.put("name_en", fc.getName_en());

					SoNingboDatabase db = SoNingboDatabase.getInstance(this);
					Cursor cursor = db.getDb(false).query(
							SecondCategoryTable.TABLE_NAME, null,
							"second_id=?", new String[] { secondId }, null,
							null, null);
					if (cursor.getCount() > 0) {
						db.getDb(true).update(SecondCategoryTable.TABLE_NAME,
								values, "second_id=?",
								new String[] { secondId });
						Log.i(TAG, "update the secondcategory.");
					} else {
						db.getDb(true).insert(SecondCategoryTable.TABLE_NAME,
								null, values);
						Toast.makeText(this, "load to sqlite success!",
								Toast.LENGTH_LONG).show();
						Log.i(TAG, "insert the secondcategory.");
					}

				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		}

		List<SecondCategory> lTempList = secondCategoryDao.getSecondCategoryByFirstId(firstId) ;
		int lSize = lTempList.size() ;
		String country = Locale.getDefault().getCountry();// 获取当前系统的语言
		for (int i = 0; i < lSize; i++) {
			HashMap<String, String> tempMap = new HashMap<String, String>();
			SecondCategory fc = lTempList.get(i);
			String secondId = fc.getSecondId();
			tempMap.put("categoryId", secondId);
			if ("CN".equalsIgnoreCase(country)) {
				tempMap.put("categoryName", fc.getName_cn());
			} else {
				tempMap.put("categoryName", fc.getName_en());
			}
			temp.add(tempMap);
		}
		return temp;
	}

}
