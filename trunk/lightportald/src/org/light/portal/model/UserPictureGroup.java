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

package org.light.portal.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Jianmin Liu
 **/
public class UserPictureGroup implements Comparable{
	String index;
	String name;
	List<UserPicture> pictues = new ArrayList<UserPicture>();
	
	public UserPictureGroup(String index, String name, UserPicture pic){
		this.index = index;
		this.name = name;
		this.pictues.add(pic);		
	}
	
	public int compareTo(Object obj){
	   if (this == obj)
           return 0;
       if (obj == null)
           return 1;
       if (getClass() != obj.getClass())
           return -1;
       final UserPictureGroup other = (UserPictureGroup) obj;
       return index.compareTo(other.index);          
	}
	public void addPicture(UserPicture pic){
		this.pictues.add(pic);		
	}
	
	public int getCount(){
		return this.pictues.size();
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<UserPicture> getPictues() {
		return pictues;
	}

	public void setPictues(List<UserPicture> pictues) {
		this.pictues = pictues;
	}

}
