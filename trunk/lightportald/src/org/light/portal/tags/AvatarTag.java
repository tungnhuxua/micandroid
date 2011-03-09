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

import javax.servlet.http.HttpServletRequest;

import org.light.portal.util.OrganizationThreadLocal;
import org.light.portal.util.PropUtil;

/**
 * Creates member url
 * with the supplied parameters.
 *
 * @author Jianmin Liu
 **/
public class AvatarTag extends HtmlTag
{
    private String url;
    private String pictureUrl;
    private String name;
    private String width;
    private String height;
    private int type;
        
    protected String getHtml(){
    	StringBuilder builder = new StringBuilder();
    	builder.append("<span class='portlet-item' style='padding:0;'>");
    	if(OrganizationThreadLocal.getOrg().getUserId() != this.getEntityId()){
	    	if(PropUtil.getInt("portal.url.format") == 1)
	    		builder.append("<a href='http://")
	    			   .append(url)
	    			   .append(".")
	    			   .append(getHost())
	    			   .append("' >")
	    			   .append(getPicture())
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
				   	   .append(getPicture())
				   	   .append("</a>");
	    	}
    	}else{
    		builder.append(getPicture())				  
				   ;
    	}
    	builder.append("</span>");
    	return builder.toString();
    }
    private String getPicture(){
    	HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();		
    	StringBuilder builder = new StringBuilder();    	
    	if(pictureUrl == null){
    		builder.append("<div style='position:relative;'>")
    			   .append("<ul style='background: transparent url(")
    			   .append(request.getContextPath())
    			   ;
    		if(type == _OBJECT_TYPE_GROUP)
    			builder.append(OrganizationThreadLocal.getOrg().getDefaultGroupPortrait());
    		else
    			builder.append(OrganizationThreadLocal.getOrg().getDefaultMalePortrait());
    		builder.append(") no-repeat scroll 0 0; list-style-type: none; width:")
    			   .append(width)
    			   .append("px; height:")
    			   .append(height)
    			   .append("px;margin:0 0 0 10px;padding:0;-moz-border-radius:8px;'>")
    			   .append("<li>")	
    			   .append("</li>")
    			   .append("</ul>")
    			   .append("</div>");
    	}else{
    		builder.append("<div style='position:relative;'>")
			   .append("<ul style='background: transparent url(")
			   ;
    		if(!pictureUrl.toLowerCase().startsWith("http"))
    			builder.append(request.getContextPath());
    		builder.append(pictureUrl)
			   .append(") no-repeat scroll 0 0; list-style-type: none; width:")
			   .append(width)
			   .append("px; height:")
			   .append(height)
			   .append("px;margin:0 0 0 10px;padding:0;-moz-border-radius:8px;'>")
			   .append("<li>")	
			   .append("</li>")
			   .append("</ul>")
			   .append("</div>");
    	}
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

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
