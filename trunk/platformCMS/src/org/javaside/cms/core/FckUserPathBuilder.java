package org.javaside.cms.core;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import net.fckeditor.handlers.PropertiesLoader;
import net.fckeditor.requestcycle.UserPathBuilder;

import org.javaside.cms.entity.Member;
import org.javaside.cms.service.MemberManager;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 实现FCK<code>UserPathBuilder</code> ，用以定义用户的图片存放路径
 * 
 * @author zhouxh
 */
public class FckUserPathBuilder implements UserPathBuilder {

	public String getUserFilesPath(HttpServletRequest request) {
		MemberManager memberManager = (MemberManager) getContext(request.getSession().getServletContext()).getBean(
				"memberManager");
		Member member = memberManager.getMemberByLoginName(SpringSecurityUtils.getCurrentUserName());
		String userPath = PropertiesLoader.getProperty("connector.userBaseFilePath") + member.getId();
		return userPath;
	}

	ApplicationContext getContext(ServletContext servletContext) {
		return WebApplicationContextUtils.getWebApplicationContext(servletContext);
	}
}
