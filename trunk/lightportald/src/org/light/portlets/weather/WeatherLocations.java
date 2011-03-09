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

package org.light.portlets.weather;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Node;

/**
 * 
 * @author Jianmin Liu
 **/
public class WeatherLocations {
	private Document document;
	private List<WeatherLocation> locations = new ArrayList<WeatherLocation>();
	private String error;
	private boolean errorFound;
	private long lastRefreshTime;
	
	public WeatherLocations(Document document){
		this.setDocument(document);		
	}
	
	public void setDocument(Document document) {
		this.lastRefreshTime = System.currentTimeMillis();
		this.document = document;
		List list = document.selectNodes( "//search/loc" );
		if(list != null){
			if(list.size() > 0){
				for (Iterator iter = list.iterator(); iter.hasNext(); ) {
		            Node node = (Node) iter.next();
		            String name = node.getText();
		            String id = node.valueOf( "@id" );
		            this.locations.add(new WeatherLocation(id,name));
		        }
			}else{
				this.errorFound = true;
				this.error = "The location which you just input is not avaliable in The Weather Channel, please check the locaiton.";
			}
		}else{
        	this.errorFound = true;
        	Node node1 = document.selectSingleNode( "//error/err" );
		    this.error = node1.getText();	
        }
	}
	
	public Document getDocument() {
		return document;
	}
	
	public List<WeatherLocation> getLocations() {
		return locations;
	}

	public String getError() {
		return error;
	}

	public boolean isErrorFound() {
		return errorFound;
	}

	public long getLastRefreshTime() {
		return lastRefreshTime;
	}
	
}
