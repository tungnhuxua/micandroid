package ningbq.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import ningbq.bean.SecondCategory;
import ningbq.main.R;
import ningbq.service.SecondCategoryService;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class SearchSecondCategoryScreen extends Activity {

	private static final String TAG = "SearchSecondCategoryScreen";

	// private SearchFirstCategoryProcess process;
	private ListView listView;
	private Button secondCategorySliding = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.secondcagetoryview);
		Bundle bundle = getIntent().getExtras();
		String firstId = bundle.getString("firstId");

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
				/*startActivity(new Intent().setClass(
						SearchSecondCategoryScreen.this,
						SearchFirstCategoryScreen.class));*/
				finish() ;
			}
		});
	}

	private ArrayList<HashMap<String, String>> fillList(String firstId) {
		SecondCategoryService fcServices = new SecondCategoryService();
		ArrayList<HashMap<String, String>> temp = new ArrayList<HashMap<String, String>>();
		ArrayList<SecondCategory> tempList = fcServices
				.getSecondCategoryAll(firstId);
		if (tempList == null) {
			return null;
		}
		int size = tempList.size();
		String country = Locale.getDefault().getCountry();// 获取当前系统的语言
		for (int i = 0; i < size; i++) {
			HashMap<String, String> tempMap = new HashMap<String, String>();
			SecondCategory fc = tempList.get(i);
			tempMap.put("categoryId", fc.getId());
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
