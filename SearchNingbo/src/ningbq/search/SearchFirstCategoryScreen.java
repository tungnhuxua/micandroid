package ningbq.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import ningbq.bean.FirstCategory;
import ningbq.db.SoNingboDatabase;
import ningbq.db.SoNingboTables.FirstCategoryTable;
import ningbq.db.dao.FirstCategoryDao;
import ningbq.main.BaseActivity;
import ningbq.main.R;
import ningbq.service.FirstCategoryService;
import ningbq.util.CheckNetwork;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class SearchFirstCategoryScreen extends BaseActivity {

	private static final String TAG = "SearchFirstCategoryScreen";
	private ListView listView = null;
	private static final int LOAD_FIRST_CATEGORY = 11;
	private SimpleAdapter firstCategoryAdpate;
	//private ProgressDialog progressDialog = null;

	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case LOAD_FIRST_CATEGORY:
				listView.setAdapter(firstCategoryAdpate);
				listView.setDivider(getResources().getDrawable(
						R.drawable.divider_new));//
				listView.setDividerHeight(2);
				listView.setSelector(R.drawable.onbar_new);
				listView.setCacheColorHint(0);
				break;
			}
		}

	};


	private Thread mThreadLoadData = new Thread() {

		@Override
		public void run() {
			firstCategoryAdpate = new SimpleAdapter(
					SearchFirstCategoryScreen.this, fillList(),
					R.layout.listfirstcategory, new String[] { "categoryId",
							"categoryName" },
					new int[] { R.id.txtFirstCategoryId,
							R.id.txtFirstCategoryName });
			mHandler.sendEmptyMessage(LOAD_FIRST_CATEGORY);
			//progressDialog.dismiss();
		}

	};

	@Override
	protected void onStart() {
		super.onStart();
		//progressDialog = ProgressDialog.show(this, "", "Loading...", true);
	}
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		listView = (ListView) appView.findViewById(R.id.firstCategoryList);

		ImageView firstCategory_Sliding = (ImageView) viewGroup
				.findViewById(R.id.firstCategorySliding);
		firstCategory_Sliding
				.setOnClickListener(new ClickListenerForScrolling(
						scrollView, menuView));
		children = new View[]{menuView,appView} ;

		scrollView.initViews(children, 1, new SizeCallbackForMenu(
				firstCategory_Sliding));

		// loadView();
		mThreadLoadData.start();

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long rowId) {
				// HashMap<String, String> map = (HashMap<String, String>)
				// arg0.getItemAtPosition(arg2);
				@SuppressWarnings("unchecked")
				// Toast.makeText(SearchFirstCategoryScreen.this,
				// "Success!", Toast.LENGTH_SHORT).show() ;
				HashMap<String, String> map = (HashMap<String, String>) listView
						.getAdapter().getItem(position);
				String tempId = map.get("categoryId");
				// String tempCategoryName = map.get("categoryName");
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putString("firstId", tempId);
				intent.putExtras(bundle);
				intent.setClass(SearchFirstCategoryScreen.this,
						SearchSecondCategoryScreen.class);
				startActivity(intent);

			}
		});
	}




	void loadRemoteData() {
		FirstCategoryService fcServices = new FirstCategoryService();
		if (CheckNetwork.check(SearchFirstCategoryScreen.this)) {// 如果网络连通.
			List<FirstCategory> rTempList = fcServices.getFirstCategoryList(); // 远程数据
			int rSize = rTempList.size();

			if (rTempList != null) { // 首先从远程加载数据到本地.
				for (int i = 0; i < rSize; i++) {
					FirstCategory fc = rTempList.get(i);
					String firstId = fc.getFirstId();
					try {
						ContentValues values = new ContentValues();
						values.put("first_id", firstId);
						values.put("name_cn", fc.getName_cn());
						values.put("name_en", fc.getName_en());

						SoNingboDatabase db = SoNingboDatabase
								.getInstance(this);
						Cursor cursor = db.getDB().query(
								FirstCategoryTable.TABLE_NAME, null,
								"first_id=?", new String[] { firstId }, null,
								null, null);
						if (cursor.getCount() > 0) {
							db.getDB().update(
									FirstCategoryTable.TABLE_NAME, values,
									"first_id=?", new String[] { firstId });
							Log.i(TAG, "update firstCategory.");
						} else {
							db.getDB()
									.insert(FirstCategoryTable.TABLE_NAME,
											null, values);
							Log.i(TAG, "insert firstCategory.");
						}

					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		}

	}

	private ArrayList<HashMap<String, String>> fillList() {
		FirstCategoryDao firstCategoryDao = new FirstCategoryDao(this);
		ArrayList<HashMap<String, String>> temp = new ArrayList<HashMap<String, String>>();

		List<FirstCategory> lTempList = firstCategoryDao.getAllFirstCategory(); // 本地数据.
		int lSize = lTempList.size();
		String country = Locale.getDefault().getCountry();// 获取前系统的语言
		for (int i = 0; i < lSize; i++) {// 从本地数据库读取数据.
			HashMap<String, String> tempMap = new HashMap<String, String>();
			FirstCategory fc = lTempList.get(i);
			tempMap.put("categoryId", fc.getFirstId());
			if ("CN".equalsIgnoreCase(country)) {
				tempMap.put("categoryName", fc.getName_cn());
			} else {
				tempMap.put("categoryName", fc.getName_en());
			}
			temp.add(tempMap);
		}
		return temp;
	}

	@Override
	public int getApplicationLayoutResource() {
		return R.layout.firstcagetoryview;
	}

	@Override
	public int getViewGroupResource() {
		return R.id.firstCategoryHeadBar;
	}



	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
