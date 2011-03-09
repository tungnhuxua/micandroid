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

import javax.portlet.PortletURL;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 
 * Function: Supporting class for the <CODE>param</CODE> tag.
 * defines a parameter that can be added to a <CODE>actionURL</CODE> or
 * a <CODE>renderURL</CODE>
 * <BR>The following attributes are mandatory
 * <UL>
 * <LI><CODE>name</CODE>
 * <LI><CODE>value</CODE>
 * </UL>
 *
 *@author Jianmin Liu
 **/

public class ParamTag extends TagSupport
{

    private String name;
    private String value;

    /**
     * Processes the <CODE>param</CODE> tag.
     * @return <CODE>SKIP_BODY</CODE>
     */
    public int doStartTag() throws JspException
    {
        BasicURLTag urlTag = (BasicURLTag)findAncestorWithClass(this, BasicURLTag.class);
        if (urlTag == null)
        {
            throw new JspException("the 'param' Tag must have actionURL or renderURL as a parent");
        }
        PortletURL url = urlTag.getUrl();

        if (getName() != null)
        {
            url.setParameter(getName(),getValue());
        }

        return SKIP_BODY;
    }

    /**
     * Returns the name.
     * @return String
     */
    public String getName()
    {
        return name;
    }

    /**
     * Returns the value.
     * @return String
     */
    public String getValue()
    {
        if (value == null)
        {
            value = "";
        }
        return value;
    }

    /**
     * Sets the name.
     * @param name The name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Sets the value.
     * @param value The value to set
     */
    public void setValue(String value)
    {
        this.value = value;
    }

}

