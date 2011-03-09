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

package org.light.portlets.connection;

/**
 * 
 * @author Jianmin Liu
 **/
public class ConnectionType {
	private int type;
	private int count;
	
	public static String getTitle(int type){
		String buddyType;
		if(type == 1)
			buddyType="Family";
		else if(type == 2)
			buddyType="Close Friend";
		else if(type == 3)
			buddyType="Classmate";
		else if(type == 4)
			buddyType="Colleague";
		else
			buddyType="Friend";
		
		return buddyType;
	}
	
	public ConnectionType(int type, int count){
		this.type = type;
		this.count = count;
	}
	
	public String getTitle(){
		String buddyType;
		if(type == 1)
			buddyType="Family";
		else if(type == 2)
			buddyType="Close Friend";
		else if(type == 3)
			buddyType="Classmate";
		else if(type == 4)
			buddyType="Colleague";
		else
			buddyType="Friend";
		
		return buddyType;
	}
	
	public void add(){
		this.count++;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
}
