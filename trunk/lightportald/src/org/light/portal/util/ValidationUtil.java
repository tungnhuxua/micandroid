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

package org.light.portal.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Jianmin Liu
 **/

public class ValidationUtil {

	public static boolean isValidEmail(String email){
		Pattern p = Pattern.compile(getEmailPattern());
	    Matcher m = p.matcher(email);
	    return m.find();
	}
 
	public static boolean isValidUrl(String uri){
		if(uri.indexOf("/") >=0) return false;
		if(uri.indexOf(".") >=0) return false;
		Pattern p = Pattern.compile(getUriPattern());
	    Matcher m = p.matcher(uri);
	    return m.find();
	}
	
	public static boolean isIPAddress(String domain){
		if(domain == null) return false;
		if(domain.indexOf(".") <= 0) return false;
	    Matcher m = _IP_ADDRESS.matcher(domain);
	    return m.find();
	}
	private static String getEmailPattern(){
		String pattern = PropUtil.getString("default.email.pattern");
		if(pattern == null) pattern = _DEFAULT_EMAIL_PATTERN;
		return pattern;
	}
	
	private static String getUriPattern(){
		String pattern = PropUtil.getString("default.uri.pattern");
		if(pattern == null) pattern = _DEFAULT_URI_PATTERN;
		return pattern;
	}

	public static boolean isRobot(String browserInfo){
		if(StringUtil.isEmpty(browserInfo) || _ROBOT_PATTERN.matcher(browserInfo).find()){
			return true;
		}else
			return false;
	}

	public static boolean isMobile(String browserInfo){
		if(!StringUtil.isEmpty(browserInfo) && _MOBILE_BROWSER_PATTERN.matcher(browserInfo).find()){
			return true;
		}else
			return false;
	}
	
	public static boolean isSmallMobile(String browserInfo){
		return isMobile(browserInfo)  && browserInfo.indexOf("iPad") < 0;
	}
	public static boolean isIPad(String browserInfo){
		return isMobile(browserInfo)  && browserInfo.indexOf("iPad") > 0;
	}
	private final static String _DEFAULT_EMAIL_PATTERN = "([a-zA-Z0-9_\\-\\.]+)@((\\[a-z]{1,3}\\.[a-z]{1,3}\\.[a-z]{1,3}\\.)|(([a-zA-Z\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)";//".+@.+\\.[a-z]+"
	private final static String _DEFAULT_URI_PATTERN = 	 "([a-zA-Z0-9]{3,})";
	private final static String _IP_ADDRESS_PATTERN = "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
	private final static Pattern _IP_ADDRESS = Pattern.compile(_IP_ADDRESS_PATTERN);
	private final static Pattern _ROBOT_PATTERN = Pattern.compile(PropUtil.getString("robots.regex"));
	private final static Pattern _MOBILE_BROWSER_PATTERN = Pattern.compile(PropUtil.getString("mobile.browser.regex"));
}