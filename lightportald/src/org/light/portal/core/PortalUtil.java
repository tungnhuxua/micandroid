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

package org.light.portal.core;

import org.apache.commons.codec.binary.Base64;


/**
 * 
 * @author Jianmin Liu
 **/
public class PortalUtil {
	
	public static long createPersonId(){
		return System.currentTimeMillis();
	}
	
	public static String encode(String cookieValue) {
	    if (cookieValue == null || "".equals(cookieValue)) {
	        return null;
	    }	    
	    Base64 base64 = new Base64();
	    byte[] bytes = cookieValue.getBytes();
	    byte[] encodedBytes = base64.encode(bytes);
	    String result = new String(encodedBytes);
	    
	    return result;
	}
	
	public static String decode(String cookieValue) {
	    if (cookieValue == null || "".equals(cookieValue)) {
	        return null;
	    }
	    if (!cookieValue.endsWith("=")) {
	        cookieValue = padString(cookieValue);
	    }
	    Base64 base64 = new Base64();
	    byte[] encodedBytes = cookieValue.getBytes();
	    byte[] decodedBytes = base64.decode(encodedBytes);
	    String result = new String(decodedBytes);
	    
	    return result;
	}
	
	private static String padString(String value) {
	    int mod = value.length() % 4;
	    if (mod <= 0) {
	        return value;
	    }
	    int numEqs = 4 - mod;
	   
	    for (int i = 0; i < numEqs; i++) {
	        value += "=";
	    }
	    return value;
	}

}
