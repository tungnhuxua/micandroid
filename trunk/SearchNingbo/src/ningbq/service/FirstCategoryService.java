package ningbq.service;

import java.util.ArrayList;

import ningbq.bean.FirstCategory;
import ningbq.http.HttpException;
import ningbq.http.ResponseException;
import ningbq.http.SearchNingboAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class FirstCategoryService {

	private static final String TAG = "FirstCategoryServices";
	private SearchNingboAPI api;
	private ArrayList<FirstCategory> firstCategoryList = new ArrayList<FirstCategory>();

	public FirstCategoryService() {
		api = new SearchNingboAPI();
	}

	public ArrayList<FirstCategory> getFirstCategoryList() {
		try {
			JSONObject jsonObject = api.getFirstCategoryAll();
			JSONArray jsonArray = jsonObject.getJSONArray("firstCategory");
			for (int i = 0, j = jsonArray.length(); i < j; i++) {
				JSONObject json = jsonArray.getJSONObject(i);
				String id = json.getString("id");
				String name_cn = json.getString("name_cn");
				String name_en = json.getString("name_en");
				FirstCategory temp = new FirstCategory();
				temp.setFirstId(id) ;
				temp.setName_cn(name_cn);
				temp.setName_en(name_en);         
				
				Log.i(TAG, "id:" + id + " name_cn:" + name_cn + " name_en:"
						+ name_en);
				firstCategoryList.add(temp);
			}
		} catch (ResponseException e) {
			e.printStackTrace();
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return firstCategoryList;
	}

}
