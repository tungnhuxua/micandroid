package ningbq.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;


import android.util.Log;

public class RestClient {
	private static final String TAG = "RestClient";

	private static String convertStreamToString(InputStream is)
			throws UnsupportedEncodingException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is,
				"UTF-8"));
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
			Log.i("POST status code :", response.getStatusLine().toString());
			HttpEntity entityResp = response.getEntity();
			System.out.println(response.getStatusLine().getStatusCode());
			if (response.getStatusLine().getStatusCode() == 200) {
				if (entityResp != null) {
					InputStream instream = entityResp.getContent();
					String result = convertStreamToString(instream);
					json = new JSONObject(result);

					instream.close();
				}
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

	public static JSONObject put(String url, HttpEntity entityReq) {

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
			Log.i("PUT status code:", response.getStatusLine().toString());
			// Get hold of the response entity
			HttpEntity entityResp = response.getEntity();

			if (entityResp != null) {
				// A Simple JSON Response Read
				InputStream instream = entityResp.getContent();
				String result = convertStreamToString(instream);
				// A Simple JSONObject Creation
				json = new JSONObject(result);
				// JSONArray array = new JSONArray() ;
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
					Log.e(TAG, e.getMessage());
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
			Log.i("GET status code:", response.getStatusLine().toString());
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

	public static JSONObject get(String url) {

		HttpClient httpClient = new DefaultHttpClient();

		HttpGet httpGet = new HttpGet(url);
		// Execute the request
		HttpResponse response = null;
		JSONObject json = null;
		//String json = null ;
		try {

			// 向服务器发送GET请求并获取服务器返回的结果
			response = httpClient.execute(httpGet);
			Log.i("GET status code:", response.getStatusLine().toString());
			// Get hold of the response entity
			HttpEntity entityResp = response.getEntity();

			if (entityResp != null) {
				// A Simple JSON Response Read
				InputStream instream = entityResp.getContent();
				String result = convertStreamToString(instream);
				// A Simple JSONObject Creation
				
				json = new JSONObject(result);
				//json = EntityUtils.toString(entityResp) ;
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
