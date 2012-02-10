package ningbq.search;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ningbq.bean.FirstCategory;
import ningbq.main.R;
import ningbq.util.RestClient;
import ningbq.util.SettingFont;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class SearchScreen extends Activity implements OnItemSelectedListener,
		OnClickListener {
	
	private static final String TAG = "SearchScreen" ;

	private Spinner Spinner_Category = null;
	private Spinner Spinner_subCategory = null;
	private String[] DialogItems = { "Shopping ", "Hotal", "Parks" };
	private String[] subCat1 = { "Shopping1", "Shopping2", "Shopping3" };
	private String[] subCat2 = { "5 Star", "3 Start", "2 Star" };
	private String[] subCat3 = { "Parks 1", "Parks 2", "Parks 3" };
	private ArrayAdapter<String> adapter = null;
	private ArrayAdapter<String> adaptersub = null;
	private ArrayList<String> listFirstCategory = new ArrayList<String>();
	private SearchProcess searchProcess ;

	private TextView txt_Heading;
	private TextView but_Hometop;
	private TextView but_Go;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// for removing the title
														// bar
		setContentView(R.layout.searchmain);

		createLayout();
	}

	private void createLayout() {
		Spinner_Category = (Spinner) findViewById(R.id.sortCat);
		Spinner_subCategory = (Spinner) findViewById(R.id.sortbysubCat);
		txt_Heading = (TextView) findViewById(R.id.txtHeading);
		but_Hometop = (Button) findViewById(R.id.ButHomeTop);
		but_Go = (Button) findViewById(R.id.ButGo);
		Button but_Home = (Button) findViewById(R.id.Buthome);
		Button but_Search = (Button) findViewById(R.id.ButSerarch);
		Button but_Fevriotes = (Button) findViewById(R.id.ButFev);
		Button but_Friend = (Button) findViewById(R.id.ButPeople);
		Button but_Setting = (Button) findViewById(R.id.ButSetting);

		// txt_Heading.setTypeface(Constaints.fontBold);
		// but_Hometop.setTypeface(Constaints.fontBold);

		but_Home.setOnClickListener(this);
		but_Fevriotes.setOnClickListener(this);
		but_Friend.setOnClickListener(this);
		but_Setting.setOnClickListener(this);
		but_Search.setOnClickListener(this);
		but_Hometop.setOnClickListener(this);
		but_Go.setOnClickListener(this);

		// setting the background
		but_Search.setBackgroundDrawable(SettingFont.SettingBackgroundImage(
				R.drawable.search_on, this));
		but_Home.setBackgroundDrawable(SettingFont.SettingBackgroundImage(
				R.drawable.home_off, this));
		searchProcess = new SearchProcess() ;
		searchProcess.execute() ; 

		// setting the typeface
		Typeface face = Typeface.createFromAsset(getAssets(),
				"fonts/Arial_Rounded_MT_Bold.ttf");
		txt_Heading.setTypeface(face);
		but_Hometop.setTypeface(face);
		but_Go.setTypeface(face);

		//   list.toArray(new String[0])
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item,DialogItems);
		adapter.setDropDownViewResource(android.R.layout.simple_list_item_checked);
		Spinner_Category.setAdapter(adapter);
		Spinner_Category.setOnItemSelectedListener(this);
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		if (arg2 == 0) {
			adaptersub = new ArrayAdapter<String>(this,
					android.R.layout.simple_spinner_item, this.subCat1);
			adaptersub
					.setDropDownViewResource(android.R.layout.simple_list_item_checked);
		} else if (arg2 == 1) {
			adaptersub = new ArrayAdapter<String>(this,
					android.R.layout.simple_spinner_item, this.subCat2);
			adaptersub
					.setDropDownViewResource(android.R.layout.simple_list_item_checked);
		} else {
			adaptersub = new ArrayAdapter<String>(this,
					android.R.layout.simple_spinner_item, this.subCat3);
			adaptersub
					.setDropDownViewResource(android.R.layout.simple_list_item_checked);
		}
		Spinner_subCategory.setAdapter(adaptersub);
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {

	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.ButHomeTop) {
			finish();
		}
		if (v.getId() == R.id.Buthome) {
			finish();
		}
		if (v.getId() == R.id.ButFev) {
			Toast.makeText(this, "Under Costruction", Toast.LENGTH_SHORT)
					.show();

		}
		if (v.getId() == R.id.ButPeople) {
			Toast.makeText(this, "Under Costruction", Toast.LENGTH_SHORT)
					.show();

		}
		if (v.getId() == R.id.ButSetting) {
			Toast.makeText(this, "Under Costruction", Toast.LENGTH_SHORT)
					.show();

		}

		if (v.getId() == R.id.ButGo) {
			Intent intent = new Intent(this, SearchResultsScreen.class);
			startActivity(intent);
		}

	}
	
	class SearchProcess extends AsyncTask<Void, Void, Void>{

		@Override
		protected Void doInBackground(Void... params) {
			JSONObject jsonObject = RestClient
					.get("http://192.168.1.100:8080/category/first/showAll");
			try {
				JSONArray jsonArray = jsonObject.getJSONArray("firstCategory");
				Gson gson = new Gson() ;
				Type type = new TypeToken<List<FirstCategory>>(){}.getType() ;
				ArrayList<FirstCategory> listAll = gson.fromJson(jsonArray.toString(), type) ;
				for(FirstCategory temp : listAll){
					String country = Locale.getDefault().getCountry() ;
					if("CN".equalsIgnoreCase(country)){
						listFirstCategory.add(temp.getName_cn()) ;
						
						Log.i(TAG, "[name_cn] : " + temp.getName_cn()) ;
					}else{
						listFirstCategory.add(temp.getName_en()) ;
						Log.i(TAG, "[name_en] : " + temp.getName_en()) ;
					}
				}
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
			} 
			return null;
		}
		
		
		
	}
}
