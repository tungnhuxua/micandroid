package ningbq.service;

import java.util.ArrayList;

import ningbq.bean.SecondCategory;
import ningbq.http.HttpException;
import ningbq.http.ResponseException;
import ningbq.http.SearchNingboAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class SecondCategoryService {
	
	private static final String TAG = "SecondCategoryService" ;
	private SearchNingboAPI api ;
	private ArrayList<SecondCategory> secondList = new ArrayList<SecondCategory>() ;
	
	public SecondCategoryService(){
		api = new SearchNingboAPI() ;
	}
	
	public ArrayList<SecondCategory> getSecondCategoryAll(String firstId){
		try {
			JSONObject jsonObject = api.getSecondCategoryAll(firstId) ;
			JSONArray jsonArray = jsonObject.getJSONArray("secondCategory") ;
			for(int i=0,j = jsonArray.length();i < j ;i++){
				JSONObject json = jsonArray.getJSONObject(i) ;
				String id = json.getString("id") ;
				String name_cn = json.getString("name_cn") ;
				String name_en = json.getString("name_en") ;
				SecondCategory second = new SecondCategory() ;
				second.setId(id) ;
				second.setName_cn(name_cn) ;
				second.setName_en(name_en) ;
				
				Log.i(TAG, "id:" + id + " name_cn:" + name_cn + " name_en:"
						+ name_en);
				secondList.add(second) ;
			}
		} catch (ResponseException e) {
			e.printStackTrace();
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return secondList ;
	}

}
