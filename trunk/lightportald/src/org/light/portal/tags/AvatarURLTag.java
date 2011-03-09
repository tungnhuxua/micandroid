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

package org.light.portal.tags;

import static org.light.portal.util.Constants._OBJECT_TYPE_GROUP;

import org.light.portal.core.PortalContextFactory;
import org.light.portal.util.OrganizationThreadLocal;
import org.light.portal.util.PropUtil;

/**
 * Creates member url
 * with the supplied parameters.
 *
 * @author Jianmin Liu
 **/
public class AvatarURLTag extends HtmlTag
{
    private String url;
    private String name;
    private int type;
       
    protected String getHtml(){    	
    	StringBuilder builder = new StringBuilder();
    	builder.append("<span class='portlet-item' style='padding:0;'><b>");
    	if(OrganizationThreadLocal.getOrg().getUserId() != this.getEntityId()){
	    	if(PropUtil.getInt("portal.url.format") == 1)
	    		builder.append("<a href='http://")
	    			   .append(url)
	    			   .append(".")
	    			   .append(getHost())
	    			   .append("' >")
	    			   .append(name)
	    			   .append("</a>");
	    	else{
	    		builder.append("<a href='http://");
	    		if(type == _OBJECT_TYPE_GROUP)
	    			builder.append(OrganizationThreadLocal.getOrg().getGroupPrefix())
		 			   	   ;
	    		else
	    			builder.append(OrganizationThreadLocal.getOrg().getUserSpacePrefix())
	    				   ;
	    		builder.append(url)
				   	   .append("' >")
				   	   .append(name)
				   	   .append("</a>");
	    	}
    	}else{
    		builder.append("<label>")
				   .append(PortalContextFactory.getPortalContext().getMessageByKey("portlet.label.anonym"))
				   .append("</label>")
				   ;
    	}
    	builder.append("</b></span>");
    	return builder.toString();
    }
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
