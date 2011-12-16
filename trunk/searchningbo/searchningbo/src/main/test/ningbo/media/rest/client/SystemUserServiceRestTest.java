package ningbo.media.rest.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import ningbo.media.bean.FirstCategory;
import ningbo.media.bean.SystemUser;
import ningbo.media.service.SystemUserService;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

public class SystemUserServiceRestTest {

	@Resource
	private SystemUserService systemUserService;
	
	@Test
	public void testJsonObjectJ(){
		JsonObject json = getJ("http://192.168.1.100:8080/category/first/showAll") ;
		System.out.println(json.toString()) ;
	}
	
	
	
	
	public static JsonObject getJ(String url) {

		HttpClient httpClient = new DefaultHttpClient();
		// Prepare a request object
		HttpGet httpGet = new HttpGet(url);
		// Execute the request
		HttpResponse response = null;
		JsonObject json = null;
		try {

			// 向服务器发送GET请求并获取服务器返回的结果
			response = httpClient.execute(httpGet);
			// Get hold of the response entity
			HttpEntity entityResp = response.getEntity();

			if (entityResp != null) {
				String result = EntityUtils.toString(entityResp);
				json = new JsonObject() ;
				json.getAsJsonObject(result) ;
			}

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	public void testGson(){
		JSONObject jsonObject = get("http://192.168.1.100:8080/category/first/showAll") ;
		Gson gson = new Gson() ;
		JSONArray array = null ;
		try {
			array = jsonObject.getJSONArray("firstCategory") ;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		Type collectionType = new TypeToken<List<FirstCategory>>(){}.getType() ;
		ArrayList<FirstCategory> listAll = gson.fromJson(array.toString(), collectionType) ;
		System.out.println(array.toString()) ;
		for(FirstCategory temp : listAll){
			System.out.println(temp.getName_cn()) ;
		}
		
	}

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
				json = new JSONObject(result) ;
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

	public void testAddUser() {

		// String input =
		// "{\"username\":\"zoopnin\",\"password\":\"12345678\",\"id\":1,\"email\":\"leyxan.nb@qq.com\",\"name_cn\":\"宁烛坪\",\"photo_path\":\"head.png\",\"isManager\":\"false\",\"date_time\":1322809084753}";

		SystemUser u = new SystemUser();
		u.setUsername("James");
		u.setPassword("123456");
		u.setEmail("leyxan@live.cn");
		u.setName_cn("吉姆");
		u.setDate_time(new Date());

		String jsonU = u.toJson();

		HttpClient httpClient = new DefaultHttpClient();
		HttpPost request;
		String url = "http://localhost:8080/user/show/1";
		try {
			request = new HttpPost(new URI(url));
			HttpResponse response = httpClient.execute(request);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					String out = EntityUtils.toString(entity, "UTF-8");
					JSONArray jsonArray = new JSONArray(out);
					for (int i = 0, j = jsonArray.length(); i < j; i++) {
						JSONObject jsonObject = (JSONObject) jsonArray.get(i);
						String a1 = jsonObject.getString("email");
						System.out.println(a1);
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void testPutRequest() {
		List<BasicNameValuePair> data = new ArrayList<BasicNameValuePair>();
		data.add(new BasicNameValuePair("email", "leyxan@live.cn"));
		data.add(new BasicNameValuePair("password", "123456"));

		Map<String, String> temp = new HashMap<String, String>();
		temp.put("email", "leyxan@live.cn");
		temp.put("password", "123456");

		// HttpEntity entity = null ;

		// entity = new UrlEncodedFormEntity(temp, "UTF-8") ;
		// JSONObject json =
		// post("http://localhost:8080/user/verification",entity) ;
		JSONObject json = get("http://localhost:8080/user/verification", temp);
		System.out.println(json);

	}

	public void testAddSystemUser() {
		String url = "http://localhost:8080/user/register";
		HttpEntity entity = null;

		List<BasicNameValuePair> data = new ArrayList<BasicNameValuePair>();
		data.add(new BasicNameValuePair("email", "tnyx2005@163.com"));
		data.add(new BasicNameValuePair("password", "12345678"));
		data.add(new BasicNameValuePair("username", "zoopnin"));

		try {
			entity = new UrlEncodedFormEntity(data, HTTP.UTF_8);
			System.out.println(post(url, entity));

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public void testListObject() {
		String url = "http://localhost:8080/category/first/showAll";
	}

	private static JSONObject put(String url, HttpEntity entityReq) {

		HttpClient httpClient = new DefaultHttpClient();
		// Prepare a request object
		HttpPut httpPut = new HttpPut(url);
		// Execute the request
		HttpResponse response = null;
		JSONObject json = null;
		try {
			httpPut.setEntity(entityReq);
			// httpPost.setHeader(name, value);

			// 向服务器发送PUT请求并获取服务器返回的结果
			response = httpClient.execute(httpPut);
			// Get hold of the response entity
			HttpEntity entityResp = response.getEntity();

			if (entityResp != null) {
				// A Simple JSON Response Read
				InputStream instream = entityResp.getContent();
				String result = convertStreamToString(instream);
				// A Simple JSONObject Creation
				json = new JSONObject(result);
				// Closing the input stream will trigger connection release
				instream.close();
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

	private static String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuffer sb = new StringBuffer();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return sb.toString();
	}

	public static JSONObject post(String url, HttpEntity entityReq) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		HttpResponse response = null;
		JSONObject json = null;

		try {
			httpPost.setEntity(entityReq);
			response = httpClient.execute(httpPost);
			HttpEntity entityResp = response.getEntity();
			if (entityResp != null) {
				InputStream instream = entityResp.getContent();
				String result = convertStreamToString(instream);
				json = new JSONObject(result);
				instream.close();
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

	public static JSONObject get(String url, Map<String, String> params) {

		HttpClient httpClient = new DefaultHttpClient();
		// Prepare a request object
		if (params != null) {
			int i = 0;
			for (Map.Entry<String, String> param : params.entrySet()) {
				if (i == 0) {
					url += "?";
				} else {
					url += "&";
				}

				try {
					url += param.getKey() + "="
							+ URLEncoder.encode(param.getValue(), "UTF-8");
				} catch (UnsupportedEncodingException e) {
				}
				i++;
			}
		}

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
				// A Simple JSON Response Read
				InputStream instream = entityResp.getContent();
				String result = convertStreamToString(instream);
				// A Simple JSONObject Creation
				json = new JSONObject(result);
				// Closing the input stream will trigger connection release
				instream.close();
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

}
