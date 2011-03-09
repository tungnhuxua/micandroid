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

import static org.light.portal.util.Constants.PORTLET_CONFIG;
import static org.light.portal.util.Constants.PORTLET_REQUEST;
import static org.light.portal.util.Constants.PORTLET_RESPONSE;

import javax.portlet.PortletConfig;
import javax.portlet.PortletRequest;
import javax.portlet.RenderResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagData;
import javax.servlet.jsp.tagext.TagExtraInfo;
import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.jsp.tagext.VariableInfo;

/**
 * 
 * Function: Supporting class for the <CODE>defineObjects</CODE> tag.
 * Creates the following variables to be used in the JSP:
 * <UL>
 * <LI><CODE>renderRequest</CODE>
 * <LI><CODE>renderResponse</CODE>
 * <LI><CODE>portletConfig</CODE>
 * </UL>
 * @see   javax.portlet.PortletRequest
 * @see   javax.portlet.RenderResponse
 * @see   javax.portlet.PortletConfig
 *
 * @author Jianmin Liu
 */
public class DefineObjectsTag extends TagSupport
{

    /**
     * Processes the <CODE>defineObjects</CODE> tag.
     * @return <CODE>SKIP_BODY</CODE>
     */
    public int doStartTag() throws JspException
    {
        PortletRequest renderRequest = (PortletRequest)pageContext.getRequest().getAttribute(PORTLET_REQUEST);
        RenderResponse renderResponse = (RenderResponse)pageContext.getRequest().getAttribute(PORTLET_RESPONSE);
        PortletConfig portletConfig = (PortletConfig)pageContext.getRequest().getAttribute(PORTLET_CONFIG);

        if (pageContext.getAttribute("renderRequest") == null)   //Set attributes only once
        {
            pageContext.setAttribute("renderRequest",
                                     renderRequest,
                                     PageContext.PAGE_SCOPE);
        }

        if (pageContext.getAttribute("renderResponse") == null)
        {
            pageContext.setAttribute("renderResponse",
                                     renderResponse,
                                     PageContext.PAGE_SCOPE);
        }

        if (pageContext.getAttribute("portletConfig") == null)
        {
            pageContext.setAttribute("portletConfig",
                                     portletConfig,
                                     PageContext.PAGE_SCOPE);
        }

        return SKIP_BODY;
    }

    public static class TEI extends TagExtraInfo
    {

        public VariableInfo [] getVariableInfo (TagData tagData)
        {
            VariableInfo [] info = new VariableInfo [] {
                new VariableInfo("renderRequest",
                                 "javax.portlet.RenderRequest",
                                 true,
                                 VariableInfo.AT_BEGIN),
                new VariableInfo("renderResponse",
                                 "javax.portlet.RenderResponse",
                                 true,
                                 VariableInfo.AT_BEGIN),
                new VariableInfo("portletConfig",
                                 "javax.portlet.PortletConfig",
                                 true,
                                 VariableInfo.AT_BEGIN)
            };

            return info;
        }
    }
}
