package org.javaside.cms.core;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.javaside.cms.entity.MemberInfo;
import org.javaside.cms.service.MemberInfoManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 实现用户的个性域名. 根据用户的个性域名配置,修改 request 的 servletPath及queryString,转发到用户的 blog首页
 * 
 * @author zhouxh
 */
public class CmsRequestWrapper extends HttpServletRequestWrapper {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	public CmsRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	public String getServletPath() {
		String tact = super.getServletPath();
		for (String act : CmsActionSet.set) {
			if (tact == null || "".equals(tact) || tact.matches(act)) {
				return super.getServletPath();
			}
		}

		return "/blog/blog-home.action";

	}

	public Map getParameterMap() {
		logger.debug("getParameterMap: " + super.getParameterMap());
		String tact = super.getServletPath();
		for (String act : CmsActionSet.set) {
			if (tact == null || "".equals(tact) || tact.matches(act)) {
				return super.getParameterMap();
			}
		}
		MemberInfoManager memberInfoManager = (MemberInfoManager) this.getContext(
				super.getSession().getServletContext()).getBean("memberInfoManager");
		MemberInfo info = memberInfoManager.getMemberInfoDomain(tact.replace("/", ""));
		if (info == null)
			return super.getParameterMap();
		Map map = new HashMap();
		String[] values = { String.valueOf(info.getMember().getId()) };
		map.put("tomember.id", values);
		return map;
	}

	ApplicationContext getContext(ServletContext servletContext) {
		return WebApplicationContextUtils.getWebApplicationContext(servletContext);
	}
}
