package ningbq.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import ningbq.bean.FirstCategory;
import ningbq.main.BaseActivity;
import ningbq.main.R;
import ningbq.service.FirstCategoryService;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class SearchFirstCategoryScreen extends BaseActivity {

	private static final String TAG = "SearchFirstCategoryScreen";
	private SearchFirstCategoryProcess process = null;
	private ListView listView = null;
	private ProgressDialog progressDialog = null;
	private ArrayList<HashMap<String, String>> listData = new ArrayList<HashMap<String, String>>();

	@Override
	public boolean _onCreate(Bundle savedInstanceState) {
		if (super._onCreate(savedInstanceState)) {
			listView = (ListView) appView.findViewById(R.id.firstCategoryList);
			SimpleAdapter adapter = new SimpleAdapter(this, fillList(),
					R.layout.listfirstcategory, new String[] { "categoryId",
							"categoryName" },
					new int[] { R.id.txtFirstCategoryId,
							R.id.txtFirstCategoryName });
			listView.setAdapter(adapter);
			listView.setDivider(getResources().getDrawable(R.drawable.divider_new)) ;//
			listView.setDividerHeight(2) ;
			listView.setSelector(R.drawable.onbar_new) ;
			listView.setCacheColorHint(0) ;
			
			ImageView firstCategory_Sliding = (ImageView) viewGroup
					.findViewById(R.id.firstCategorySliding);
			firstCategory_Sliding
					.setOnClickListener(new ClickListenerForScrolling(
							scrollView, menuView));
			
			scrollView.initViews(children, 1, new SizeCallbackForMenu(
					firstCategory_Sliding));
			
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
			return true;
		} else {
			return false;
		}

	}

	class SearchFirstCategoryProcess extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... params) {
			FirstCategoryService fcServices = new FirstCategoryService();
			ArrayList<FirstCategory> tempList = fcServices
					.getFirstCategoryList();
			if (tempList == null) {
				return null;
			}
			int size = tempList.size();
			String country = Locale.getDefault().getCountry();// 获取当前系统的语言
			for (int i = 0; i < size; i++) {
				HashMap<String, String> tempMap = new HashMap<String, String>();
				FirstCategory fc = tempList.get(i);
				tempMap.put("categoryId", fc.getFirstId());
				if ("CN".equalsIgnoreCase(country)) {
					tempMap.put("categoryName", fc.getName_cn());
				} else {
					tempMap.put("categoryName", fc.getName_en());
				}
				listData.add(tempMap);
			}
			return null;
		}

	}

	private ArrayList<HashMap<String, String>> fillList() {
		FirstCategoryService fcServices = new FirstCategoryService();
		ArrayList<HashMap<String, String>> temp = new ArrayList<HashMap<String, String>>();
		ArrayList<FirstCategory> tempList = fcServices.getFirstCategoryList();
		if (tempList == null) {
			return null;
		}
		int size = tempList.size();
		String country = Locale.getDefault().getCountry();// 获取当前系统的语言
		for (int i = 0; i < size; i++) {
			HashMap<String, String> tempMap = new HashMap<String, String>();
			FirstCategory fc = tempList.get(i);
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

}
