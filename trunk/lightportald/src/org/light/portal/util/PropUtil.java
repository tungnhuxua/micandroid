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

import com.germinus.easyconf.EasyConf;

/**
 * 
 * @author Jianmin Liu
 **/
public class PropUtil {
	private final static String _BASE = "portal";
	
	public static String getString(String name){		
		return getString(name, getOrgWebId());
	}
	
	public static String getString(String name,String orgWebId){
		String value = null;
		try{
			if(orgWebId != null)
				value = EasyConf.getConfiguration(orgWebId,_BASE).getProperties().getString(name);
			else
				value = EasyConf.getConfiguration(_BASE).getProperties().getString(name);
		}catch(Exception e){}
		return value;
	}
	
	public static String getString(String name,String orgWebId,String defaulted){
		String value = getString(name,orgWebId);
		return StringUtil.isEmpty(value) ? defaulted : value;
	}
	
	public static boolean getBoolean(String name){
		return getBoolean(name, getOrgWebId());
	}
	
	public static boolean getBoolean(String name,String orgWebId){
		boolean value = false;
		try{
			if(orgWebId != null)
				value = EasyConf.getConfiguration(orgWebId,_BASE).getProperties().getBoolean(name);
			else
				value = EasyConf.getConfiguration(_BASE).getProperties().getBoolean(name);
		}catch(Exception e){}
		return value;
	}
	
	public static int getInt(String name){
		return getInt(name, getOrgWebId());
	}
	public static int getInt(String name, int defaulted){
		int value = getInt(name, getOrgWebId());
		return (value != Integer.MIN_VALUE) ? value : defaulted;
	}
	public static int getInt(String name,String orgWebId){
		int value = Integer.MIN_VALUE;
		try{
			if(orgWebId != null)
				value = EasyConf.getConfiguration(orgWebId,_BASE).getProperties().getInt(name);
			else
				value = EasyConf.getConfiguration(_BASE).getProperties().getInt(name);
		}catch(Exception e){}
		return value;
	}
	public static int getInt(String name,String orgWebId, int defaulted){
		int value = getInt(name, orgWebId);
		return (value != Integer.MIN_VALUE) ? value : defaulted;
	}
	public static long getLong(String name){		
		return getLong(name, getOrgWebId());
	}
	public static long getLong(String name, long defaulted){
		long value = getLong(name, getOrgWebId());
		return (value != Long.MIN_VALUE) ? value : defaulted;
	}
	public static long getLong(String name,String orgWebId){
		long value = Long.MIN_VALUE;
		try{
			if(orgWebId != null)
				value = EasyConf.getConfiguration(orgWebId,_BASE).getProperties().getLong(name);
			else
				value = EasyConf.getConfiguration(_BASE).getProperties().getLong(name);
		}catch(Exception e){}
		return value;
	}
	public static long getLong(String name,String orgWebId, long defaulted){
		long value = getLong(name, orgWebId);
		return (value != Long.MIN_VALUE) ? value : defaulted;
	}
	
	public static Class getClass(String name){
		return getClass(name, getOrgWebId());
	}
	
	public static Class getClass(String name,String orgWebId){
		Class value = null;
		try{
			if(orgWebId != null)
				value = EasyConf.getConfiguration(orgWebId,_BASE).getProperties().getClass(name);
			else
				value = EasyConf.getConfiguration(_BASE).getProperties().getClass(name);
		}catch(Exception e){}
		return value;
	}
	
	public static Class[] getClassArray(String name){
		return getClassArray(name, getOrgWebId());
	}
	
	public static Class[] getClassArray(String name,String orgWebId){
		Class[] value = null;
		try{
			if(orgWebId != null)
				value = EasyConf.getConfiguration(orgWebId,_BASE).getProperties().getClassArray(name);
			else
				value = EasyConf.getConfiguration(_BASE).getProperties().getClassArray(name);
		}catch(Exception e){}
		return value;
	}
	
	public static String[] getStringArray(String name){
		return getStringArray(name, getOrgWebId());
	}
	
	public static String[] getStringArray(String name,String orgWebId){
		String[] value = null;
		try{
			if(orgWebId != null)
				value = EasyConf.getConfiguration(orgWebId,_BASE).getProperties().getStringArray(name);
			else
				value = EasyConf.getConfiguration(_BASE).getProperties().getStringArray(name);
		}catch(Exception e){}
		return value;
	}
	
	public static String getOrgWebId(){
		return OrganizationThreadLocal.getWebId();
	}
}
