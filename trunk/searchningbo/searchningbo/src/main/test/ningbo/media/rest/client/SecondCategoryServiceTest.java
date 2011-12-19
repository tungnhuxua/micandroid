package ningbo.media.rest.client;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import ningbo.media.bean.FirstCategory;
import ningbo.media.bean.Location;
import ningbo.media.bean.SecondCategory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class SecondCategoryServiceTest {

	public static JSONObject get(String url) {
		HttpClient httpClient = new DefaultHttpClient();
		// Prepare a request object
		HttpGet httpGet = new HttpGet(url);
		// Execute the request
		HttpResponse response = null;
		JSONObject json = null;
		try {

			// 向服务器发送GET请求并获取服务器返回的结果
			response = httpClient.execute(httpGet);
			// Get hold of the response entity
			HttpEntity entityResp = response.getEntity();

			if (entityResp != null) {
				String result = EntityUtils.toString(entityResp);
				json = new JSONObject(result);
			}

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}

	@Test
	public void testJsonObject() {
		try {
			String url = "http://localhost:8080/category/first/showAll";
			JSONObject json = get(url);
			
			System.out.println(json);
			JSONArray jsonArray = json.getJSONArray("firstCategory");
			System.out.println(jsonArray);
			Gson gson = new Gson();
			Type type = new TypeToken<List<FirstCategory>>() {
			}.getType();
			List<FirstCategory> list = gson
					.fromJson(jsonArray.toString(), type);
			System.out.println(list.get(0).getId() + ":"
					+ list.get(0).getName_cn());

			StringBuffer url1 = new StringBuffer(
					"http://localhost:8080/category/second/show/");
			url1.append(list.get(0).getId());

			JSONObject json1 = get(url1.toString());
			System.out.println(json1);
			JSONArray jsonArray1 = json1.getJSONArray("secondCategory");
			System.out.println(jsonArray1);
			Type type1 = new TypeToken<List<SecondCategory>>() {
			}.getType();
			List<SecondCategory> list1 = gson.fromJson(jsonArray1.toString(),
					type1);
			System.out.println(list1.get(0).getId() + ":"
					+ list1.get(0).getName_cn());

			
			StringBuffer url2 = new StringBuffer(
					"http://localhost:8080/location/category/3");
			//url2.append(list1.get(0).getId());
			System.out.println("id:" + list1.get(1).getId()) ;
			JSONObject json2 = get(url2.toString());
			System.out.println(json2)  ;
			int len = json2.length() ;
			
			System.out.println("length:" + len)  ;
			if(len > 1){
				JSONArray jsonArray2 = json2.getJSONArray("location") ;
				Type type2 = new TypeToken<List<Location>>(){}.getType() ;
				List<Location> list2 = gson.fromJson(jsonArray2.toString(), type2) ;
				System.out.println(list2.get(0).getName_cn()+":"+list2.get(0).getLatitude()) ;
			}else{
				JSONObject json3 = json2.getJSONObject("location") ;
			
				
				
				System.out.println(json3) ;
			}
			
		

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
