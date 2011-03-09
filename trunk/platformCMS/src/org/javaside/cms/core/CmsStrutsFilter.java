package org.javaside.cms.core;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhouxh
 */
public class CmsStrutsFilter extends StrutsPrepareAndExecuteFilter {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		//阻止Struts处理fck
		if (request.getServletPath() != null && request.getServletPath().indexOf("fckeditor") > -1) {
			chain.doFilter(req, res);
		} else {
			request = new CmsRequestWrapper(request);
			super.doFilter(request, res, chain);
		}
	}
}
