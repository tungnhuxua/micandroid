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

import static org.light.portal.util.Constants._DEFAULT_RESOURCE_BUNDLE;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * 
 * @author Jianmin Liu
 **/
public class PortalContext {
	private Locale currentLocale;
	private ResourceBundle resourceBundle;
	private Map<String,String> valueKeyMap = new HashMap<String,String>();
	private ResourceBundle enResourceBundle = ResourceBundle.getBundle(_DEFAULT_RESOURCE_BUNDLE, new Locale("en"));
	public String getMessageByKey(String key){		
		String value = null;
		try{
			if(resourceBundle == null)
				this.setResourceBundle(enResourceBundle);
			value = resourceBundle.getString(key);
		}catch(MissingResourceException e){
			value = key;
		}
		
		return value;			
	}
	
	public String getActionName(String value){		
		String action = this.getKeyByValue(value);
		try{
			action = enResourceBundle.getString(action);
		}catch(MissingResourceException e){			
		}
		return action;			
	}
	
	public String getKeyByValue(String value){		
		String key = null;
		try{
			key = valueKeyMap.get(value);
		}catch(MissingResourceException e){
			key = value;
		}
		
		return key;			
	}
	
	public Locale getCurrentLocale() {
		return currentLocale;
	}
	
	public void setCurrentLocale(Locale currentLocale) {
		this.currentLocale = currentLocale;
	}

	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}
	
	public void setResourceBundle(ResourceBundle resourceBundle) {
		this.resourceBundle = resourceBundle;
		Enumeration keys =  this.resourceBundle.getKeys();
		while(keys.hasMoreElements()){
			String key = (String)keys.nextElement();
			String value = this.resourceBundle.getString(key);
			this.valueKeyMap.put(value,key);
		}		
	}	
}
