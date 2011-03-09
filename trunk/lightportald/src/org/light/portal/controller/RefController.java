package org.light.portal.controller;

import static org.light.portal.util.Constants._MOBILE;
import static org.light.portal.util.Constants._MAIN_INDEX;
import static org.light.portal.util.Constants._MOBILE_INDEX;
import static org.light.portal.util.Constants._REF_URL_PREFIX;
import static org.light.portal.util.Constants._REQUEST_SUFFIX;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.light.portal.model.Organization;
import org.light.portal.util.DomainUtil;
import org.light.portal.util.OrganizationThreadLocal;
import org.light.portal.util.ValidationUtil;

public class RefController extends GenericController {

	public void execute(HttpServletRequest request,
			HttpServletResponse response, ControllerChain chain)
			throws ServletException, IOException {
		String uri = request.getRequestURI();			
		if(uri.indexOf(_REF_URL_PREFIX) >= 0 && (uri.indexOf(_REQUEST_SUFFIX) < 0)){
			String path = uri.substring(uri.indexOf(_REF_URL_PREFIX)+_REF_URL_PREFIX.length());
			if(path.indexOf("/") < 0)
				doRef(request, response, path);			
		}
		
		chain.execute(request,response);
	}
	
	private void doRef(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {		
		Organization org = OrganizationThreadLocal.getOrg();
		String fullDomain = DomainUtil.getFullDomain(request);
		String browserInfo = request.getHeader("User-Agent");
		String clientUrl = request.getParameter("clientUrl");
		boolean mobile = ValidationUtil.isSmallMobile(browserInfo);
		 
		String index = (mobile || request.getAttribute(_MOBILE) != null || (clientUrl != null && clientUrl.indexOf(_MOBILE_INDEX) > 0)) ? _MOBILE_INDEX : _MAIN_INDEX;
		if(fullDomain.equalsIgnoreCase(org.getWebId())
				|| fullDomain.equalsIgnoreCase(org.getVirtualHost())){
			response.sendRedirect("http://"+request.getHeader("Host")+request.getContextPath()+index);
		}else{
			response.sendRedirect("http://"+DomainUtil.getRootDomain(request)+request.getContextPath()+index);
		}
	}
}
