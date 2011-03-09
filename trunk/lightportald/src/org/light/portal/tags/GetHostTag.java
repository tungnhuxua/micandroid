package org.light.portal.tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.light.portal.util.DomainUtil;

public class GetHostTag extends BaseTag {

	   
	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();		
		String host = DomainUtil.getFullHost(request);
		request.setAttribute("host",host);
		return SKIP_BODY;
	}	
}
