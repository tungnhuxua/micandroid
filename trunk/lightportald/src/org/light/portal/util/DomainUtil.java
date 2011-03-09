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

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author Jianmin Liu
 **/
public class DomainUtil {
		
	public static String getHost(HttpServletRequest request) {
		String host = request.getHeader("Host");
		if (host != null) {
			host = DomainUtil.getDomain(host.toLowerCase());
			if(host.toLowerCase().startsWith("www.")){
				host = host.substring(4);
			}else{				
				//remove subdomain
				String domainName = DomainUtil.getDomainName(DomainUtil.getDomain(host.toLowerCase()));
				if(!ValidationUtil.isIPAddress(domainName) && domainName.lastIndexOf(".") > 0)			
					host = host.substring(domainName.lastIndexOf(".")+1);
			}
		}
		return host;
	}
			
	public static String getFullHost(HttpServletRequest request) {
		String host = request.getHeader("Host");
		if (host != null) {		
			if(host.startsWith("www") || host.startsWith("WWW")) host = host.substring(4);
			String domain = DomainUtil.getDomain(host.toLowerCase());			
			//remove subdomain
			String domainName = DomainUtil.getDomainName(domain);
			if(!ValidationUtil.isIPAddress(domainName) && domainName.lastIndexOf(".") > 0)			
				host = host.substring(domainName.lastIndexOf(".")+1);			
			host+= request.getContextPath();
		}		
		return host;
	}
	
	public static String getCookieDomain(HttpServletRequest request){
        String cookieDomain = request.getServerName();
        if("localhost".equalsIgnoreCase(cookieDomain) || ValidationUtil.isIPAddress(cookieDomain)) return cookieDomain;
        String domainName = getDomainName(cookieDomain);
        String[] parts = domainName.split("\\.");
        if (parts.length >= 2)
            cookieDomain = cookieDomain.substring(cookieDomain.indexOf(parts[parts.length - 1]));
        return "." + cookieDomain;
    }
	
	public static String getFullDomain(HttpServletRequest request) {
		String host = request.getHeader("Host");
		if (host != null) {
			host = DomainUtil.getDomain(host.toLowerCase());
			if(host.toLowerCase().startsWith("www.")){
				host = host.substring(4);
			}
			int pos = host.indexOf(':');
			if (pos >= 0) {
				host = host.substring(0, pos);
			}
		}
		return host;
	}
	
	public static String getRootDomain(HttpServletRequest request){
        String rootDomain = request.getServerName();
        if("localhost".equalsIgnoreCase(rootDomain) || ValidationUtil.isIPAddress(rootDomain)) return request.getHeader("Host");
        String domainName = getDomainName(rootDomain);
        String[] parts = domainName.split("\\.");
        if (parts.length >= 2)
        	rootDomain = rootDomain.substring(rootDomain.indexOf(parts[parts.length - 1]));
        return rootDomain;
    }	
	
	public static String getDomain(String host){
		String domain = host.trim().toLowerCase();
		int pos = domain.indexOf(':');
		if (pos >= 0) {
			domain = domain.substring(0, pos);
		}
		return domain;
	}
	
	public static String getDomainName(String host){
		for(String tld : tlds){
			if(host.endsWith(tld)){
				return host.substring(0,host.length() - tld.length());
			}
		}
		return host;
	}
	
	public static boolean isSubDomain(HttpServletRequest request) {
		String host = request.getHeader("Host");
		boolean value = false;
		if (host != null) {
			host = DomainUtil.getDomain(host.toLowerCase());
			if(host.toLowerCase().startsWith("www.")){
				value = false;
			}else{ 		
				String domainName = DomainUtil.getDomainName(host);
				if(!ValidationUtil.isIPAddress(domainName) && domainName.indexOf(".") > 0){
					value = true;
				}
			}
		}
			
		return value;
	}
	
	private static String[] tlds;
	private static Map<String, Integer> domains = new HashMap<String, Integer>();
	static{
		tlds = PropUtil.getStringArray("top.level.domain.list");
		for(String tld : tlds){
			domains.put(tld,tld.length());
		}		
	}
}
