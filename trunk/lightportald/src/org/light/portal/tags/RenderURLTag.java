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

import javax.portlet.PortletMode;
import javax.portlet.PortletModeException;
import javax.portlet.PortletSecurityException;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;
import javax.portlet.WindowStateException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

/**
 * 
 * Function:  Supporting class for the <CODE>renderURL</CODE> tag.
 * Creates a url that points to the current Portlet and triggers an render request
 * with the supplied parameters. 
 *
 *@author Jianmin Liu
 **/
public class RenderURLTag extends BasicURLTag
{


    /* (non-Javadoc)
         * @see javax.servlet.jsp.tagext.Tag#doStartTag()
         */
    public int doStartTag() throws JspException {
        if (var != null)
        {
            pageContext.removeAttribute(var, PageContext.PAGE_SCOPE);
        }
        RenderResponse renderResponse = (RenderResponse)pageContext.getRequest().getAttribute("javax.portlet.response");

        if (renderResponse != null)
        {
            setUrl(renderResponse.createRenderURL());
            if (portletMode != null)
            {
                try
                {
                    PortletMode mode = new PortletMode(portletMode);
                    url.setPortletMode(mode);
                }
                catch (PortletModeException e)
                {
                    throw new JspException(e);
                }
            }
            if (windowState != null)
            {
                try
                {
                    WindowState state = new WindowState(windowState);
                    url.setWindowState(state);
                }
                catch (WindowStateException e)
                {
                    throw new JspException(e);
                }
            }
            if (secure != null)
            {
                try
                {
                    url.setSecure(getSecureBoolean());
                }
                catch (PortletSecurityException e)
                {
                    throw new JspException(e);
                }
            }
        }
        return EVAL_PAGE;
    }
}

