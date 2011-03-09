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

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import net.sf.acegisecurity.providers.encoding.Md5PasswordEncoder;

/**
 * 
 * @author Jianmin Liu
 **/
public class HashUtil {

	public static String SHAHashing(String input){
		String output = "";
		MessageDigest md;		
        try {
            md = MessageDigest.getInstance("SHA");
            byte[] original = input.getBytes("UTF-8");
            byte[] bytes = md.digest(original);
            for (int i = 0; i<bytes.length; i++) {
            	output += Integer.toHexString((bytes[i] & 0xff) + 0x100).substring(1);
            }            
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }catch(UnsupportedEncodingException e){
        	e.printStackTrace();
        }
        return output;
	}
	
	public static String MD5Hashing(String input){
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();  
		return encoder.encodePassword(input, null);		
	}
}
