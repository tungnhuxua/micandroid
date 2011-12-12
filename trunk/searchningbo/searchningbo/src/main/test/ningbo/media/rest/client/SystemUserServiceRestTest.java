package ningbo.media.rest.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import ningbo.media.BaseTest;
import ningbo.media.bean.SystemUser;
import ningbo.media.service.SystemUserService;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;

public class SystemUserServiceRestTest extends BaseTest{
	
	@Resource
	private SystemUserService systemUserService ;
	
	public void testAddUser(){
		
	

			//String input = "{\"username\":\"zoopnin\",\"password\":\"12345678\",\"id\":1,\"email\":\"leyxan.nb@qq.com\",\"name_cn\":\"宁烛坪\",\"photo_path\":\"head.png\",\"isManager\":\"false\",\"date_time\":1322809084753}";

			SystemUser u = new SystemUser() ;
			u.setUsername("James") ;
			u.setPassword("123456") ;
			u.setEmail("leyxan@live.cn") ;
			u.setName_cn("吉姆") ;
			u.setDate_time(new Date()) ;
			
			String jsonU = u.toJson() ;
			
			HttpClient httpClient = new DefaultHttpClient() ;
			HttpPost request ;
			String url = "http://localhost:8080/user/show/1" ;
			try{
				request = new HttpPost(new URI(url)) ;
				HttpResponse response = httpClient.execute(request) ;
				if(response.getStatusLine().getStatusCode() == 200){
					HttpEntity entity = response.getEntity() ;
					if(entity != null){
						String out = EntityUtils.toString(entity,"UTF-8") ;
						JSONArray jsonArray = new JSONArray(out) ;
						for(int i=0,j=jsonArray.length();i<j;i++){
							JSONObject jsonObject = (JSONObject)jsonArray.get(i) ;
							String a1 = jsonObject.getString("email") ;
							System.out.println(a1) ;
						}
					}
				}
			}catch(Exception ex){
				ex.printStackTrace() ;
			}
	}
	
	@Test
	public void testPutRequest(){
		List<BasicNameValuePair> data = new ArrayList<BasicNameValuePair>() ;
		data.add(new BasicNameValuePair("email","leyxan@live.cn")) ;
		data.add(new BasicNameValuePair("password","123456")) ;
		HttpEntity entity = null ;
		try {
			entity = new UrlEncodedFormEntity(data, "UTF-8") ;
			JSONObject json = put("http://localhost:8080/user/verification",entity) ;
			System.out.println(json) ;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		
	}


