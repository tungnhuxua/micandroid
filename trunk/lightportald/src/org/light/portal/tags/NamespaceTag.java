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

import javax.portlet.RenderResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * This tag produces a unique value for the current portlet.
 * <p/>
 * <p/>
 * Supporting class for the <CODE>namespace</CODE> tag. writes a unique value
 * for the current portlet <BR>This tag has no attributes
 * 
 * @author Jianmin Liu
 **/
public class NamespaceTag extends TagSupport {

    /* (non-Javadoc)
     * @see javax.servlet.jsp.tagext.Tag#doStartTag()
     */
    public int doStartTag() throws JspException {
        RenderResponse renderResponse = (RenderResponse) pageContext.getRequest()
            .getAttribute("javax.portlet.response");
        String namespace = renderResponse.getNamespace();
        JspWriter writer = pageContext.getOut();
        try {
            writer.print(namespace);
        } catch (IOException ioe) {
            throw new JspException(
                "Unable to write namespace", ioe
            );
        }
        return SKIP_BODY;
    }
}
