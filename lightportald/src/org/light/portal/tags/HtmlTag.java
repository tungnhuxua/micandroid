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

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.light.portal.util.DomainUtil;

/**
 * rendering HTML segment UI abstract tag
 * with the supplied parameters.
 *
 * @author Jianmin Liu
 **/
public abstract class HtmlTag extends BaseTag
{
	private long entityId;
        
    /**
     *
     * @return int
     */
    public int doEndTag() throws JspException {
    	try
	    {
	        JspWriter writer = pageContext.getOut();
	        writer.print(getHtml()); 
	        //writer.flush();
	    }
	    catch (IOException ioe)
	    {
	        throw new JspException("Member URL Tag Exception: cannot write to the output writer.");
	    }
	    return EVAL_PAGE;
    }
    
    protected abstract String getHtml();
    
    protected String getHost(){
    	HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();		
		return DomainUtil.getFullHost(request);
    }

	public long getEntityId() {
		return entityId;
	}

	public void setEntityId(long entityId) {
		this.entityId = entityId;
	}

}