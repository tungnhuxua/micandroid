package ningbo.media.rest.client;

import java.net.URI;
import java.util.Date;

import javax.annotation.Resource;

import ningbo.media.BaseTest;
import ningbo.media.bean.SystemUser;
import ningbo.media.service.SystemUserService;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;

public class SystemUserServiceRestTest extends BaseTest{
	
	@Resource
	private SystemUserService systemUserService ;
	
	@Test
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
		
	}


