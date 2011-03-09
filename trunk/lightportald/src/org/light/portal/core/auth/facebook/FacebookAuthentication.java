 /*
 * Light Portal
 *
 * Copyright (c) 2009, Light Portal, Inc or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Light Portal, Inc.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 *
 */

package org.light.portal.core.auth.facebook;

import static org.light.portal.util.Constants._PORTAL_INDEX;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.light.portal.Context;
import org.light.portal.core.auth.Authentication;
import org.light.portal.logger.Logger;
import org.light.portal.logger.LoggerFactory;
import org.light.portal.model.User;
import org.light.portal.model.UserProfile;
import org.light.portal.util.PropUtil;
import org.light.portal.util.StringUtil;
import org.w3c.dom.Document;

import com.google.code.facebookapi.FacebookException;
import com.google.code.facebookapi.FacebookXmlRestClient;
import com.google.code.facebookapi.ProfileField;

/**
 * 
 * @author Jianmin Liu
 **/
public class FacebookAuthentication extends Authentication{

	private static FacebookAuthentication _instance = new FacebookAuthentication();
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private static final String ID_PREFIX = "FB_";
	
	public static FacebookAuthentication getInstance(){
		return _instance;
	}
	private FacebookAuthentication(){
		
	}
	
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String apiKey = PropUtil.getString("facebook.apiKey");
		response.sendRedirect(getURL(request,apiKey,"%2FopenId%2Ffacebook.jsp"));	
	}
	public void request(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {
		String apiKey = PropUtil.getString("facebook.apiKey");
		response.sendRedirect(getURL(request,apiKey,"%2FopenId%2FfacebookSync.jsp"));	
	}
	public void verifyResponse(HttpServletRequest request, HttpServletResponse response)
				throws IOException, ServletException {
		 String apiKey = PropUtil.getString("facebook.apiKey");
		 String secretKey = PropUtil.getString("facebook.secretKey");

		 FacebookXmlRestClient frc = null;
		 String queryString = request.getQueryString();		 
		 try {
			 queryString = URLDecoder.decode(queryString, "UTF-8");
			 if(queryString.indexOf("=") >= 0) queryString = queryString.substring(queryString.indexOf("=")+1);
			 JSONObject data = new JSONObject(queryString);
			 String sessionKey = data.getString("session_key");
			 if (sessionKey != null && sessionKey.length() > 0) {
				 frc = new FacebookXmlRestClient(apiKey, secretKey, sessionKey);
				 this.authenticate(request, response, frc);
			 }
			 else {
				 response.sendRedirect(getURL(request,apiKey,"%2FopenId%2Ffacebook.jsp"));	
			 }
		 } catch (FacebookException fe) {
			 log.error(fe.getMessage());
		 } catch (IOException ioe) {
			 log.error(ioe.getMessage());
		 }catch (Exception e) {
			 log.error(e.getMessage());
		 }
	}
	
	public void syncResponse(HttpServletRequest request, HttpServletResponse response)
				throws IOException, ServletException {
		String apiKey = PropUtil.getString("facebook.apiKey");
		String secretKey = PropUtil.getString("facebook.secretKey");
		
		FacebookXmlRestClient frc = null;
		String queryString = request.getQueryString();		 
		try {
		 queryString = URLDecoder.decode(queryString, "UTF-8");
		 if(queryString.indexOf("=") >= 0) queryString = queryString.substring(queryString.indexOf("=")+1);
		 JSONObject data = new JSONObject(queryString);
		 String sessionKey = data.getString("session_key");
		 if (sessionKey != null && sessionKey.length() > 0) {
			 frc = new FacebookXmlRestClient(apiKey, secretKey, sessionKey);
			 this.syncData(request, response, frc);
		 }
		 else {
			 response.sendRedirect(getURL(request,apiKey,"%2FopenId%2Ffacebook.jsp"));	
		 }
		} catch (FacebookException fe) {
		 log.error(fe.getMessage());
		} catch (IOException ioe) {
		 log.error(ioe.getMessage());
		}catch (Exception e) {
		 log.error(e.getMessage());
		}
	}
	
	private String getURL(HttpServletRequest request, String apiKey, String callback){
		StringBuilder buffer = new StringBuilder();
		String host = request.getHeader("Host")+request.getContextPath();
		buffer.append("http://www.facebook.com/login.php?api_key=")
			  .append(apiKey)
			  .append("&display=popup&extern=1&fbconnect=1&req_perms=publish_stream&return_session=1&v=1.0&next=http%3A%2F%2F")
			  .append(host)
			  .append(callback)
			  .append("&fb_connect=1&cancel_url=http%3A%2F%2F")
			  .append(host)
			  ;
		return buffer.toString();
	}
	private void authenticate(HttpServletRequest request, HttpServletResponse response,FacebookXmlRestClient  frc) throws FacebookException, IOException{
		long uid = frc.users_getLoggedInUser();
        ArrayList<Long> uids = new ArrayList<Long>(1);
        uids.add(uid);
        //init field parameter - we choose all profile infos.
        List<ProfileField> fields = Arrays.asList(ProfileField.values());
        //EnumSet<ProfileField> fields = EnumSet.of(ProfileField.NAME);
        //get the document containing the infos
        Document  userInfo = frc.users_getInfo(uids, fields);
        for (ProfileField pfield : fields){
            System.out.println(pfield.fieldName()+": "+userInfo.getElementsByTagName(pfield.fieldName()).item(0).getTextContent());
        }
        String name = userInfo.getElementsByTagName("name").item(0).getTextContent();
        setupUser(request, ID_PREFIX+String.valueOf(uid),name);
        copyData(request,userInfo);
        String contextPath = request.getContextPath();
		if(contextPath.indexOf(PropUtil.getString("portal.openid.url.prefix"))>=0)
			 contextPath=contextPath.substring(0,contextPath.indexOf(PropUtil.getString("portal.openid.url.prefix")));			
		response.sendRedirect(contextPath+_PORTAL_INDEX);
	 }
	
	private void syncData(HttpServletRequest request, HttpServletResponse response,FacebookXmlRestClient  frc) throws FacebookException, IOException{
		long uid = frc.users_getLoggedInUser();
        ArrayList<Long> uids = new ArrayList<Long>(1);
        uids.add(uid);
        //init field parameter - we choose all profile infos.
        List<ProfileField> fields = Arrays.asList(ProfileField.values());
        //get the document containing the infos
        Document userInfo = frc.users_getInfo(uids, fields);
        copyData(request,userInfo);
        String contextPath = request.getContextPath();
		if(contextPath.indexOf(PropUtil.getString("portal.openid.url.prefix"))>=0)
			 contextPath=contextPath.substring(0,contextPath.indexOf(PropUtil.getString("portal.openid.url.prefix")));			
		response.sendRedirect(contextPath+_PORTAL_INDEX);
	 }
	
	private void copyData(HttpServletRequest request,Document userInfo){
		String pictureUrl = userInfo.getElementsByTagName(ProfileField.PIC.toString()).item(0).getTextContent();        
		int width = PropUtil.getInt("default.user.portrait.width");		
		int height= PropUtil.getInt("default.user.portrait.height");
		try{
			URL url = new URL(pictureUrl);
			BufferedImage image=javax.imageio.ImageIO.read(url);
			if(image != null){
				width = image.getWidth();
				height= image.getHeight();
			}
		}catch(Exception e){}
        User user = Context.getInstance().getUser(request);
        user = this.getUserService(request).getUserById(user.getId());
        user.setPhotoUrl(pictureUrl);
        user.setPhotoWidth(width);
        user.setPhotoHeight(height);   
        user.setDisplayName(userInfo.getElementsByTagName(ProfileField.NAME.toString()).item(0).getTextContent());
        this.getUserService(request).save(user);
        Context.getInstance().setUser(request,user);
        UserProfile userProfile = this.getUserService(request).getUserProfileById(user.getId());
        if(userProfile == null){
        	userProfile = new UserProfile(user.getId());
        }
        String firstName = userInfo.getElementsByTagName(ProfileField.FIRST_NAME.toString()).item(0).getTextContent();
        if(!StringUtil.isEmpty(firstName)) userProfile.setFirstName(firstName);
        String lastName = userInfo.getElementsByTagName(ProfileField.LAST_NAME.toString()).item(0).getTextContent();
        if(!StringUtil.isEmpty(lastName)) userProfile.setLastName(lastName);
        String aboutMe = userInfo.getElementsByTagName(ProfileField.ABOUT_ME.toString()).item(0).getTextContent();
        if(!StringUtil.isEmpty(aboutMe)) userProfile.setAboutMe(aboutMe);
        this.getUserService(request).save(userProfile);
	}
	
}
