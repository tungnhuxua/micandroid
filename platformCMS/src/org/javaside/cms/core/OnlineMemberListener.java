package org.javaside.cms.core;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.javaside.cms.entity.Member;
import org.javaside.cms.service.MemberManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.security.context.HttpSessionContextIntegrationFilter;
import org.springframework.security.context.SecurityContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 当 session 销毁时，对应的登录用户设置为离线状态
 * 
 * @author zhouxh
 */
public class OnlineMemberListener implements HttpSessionListener {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	public void sessionCreated(HttpSessionEvent event) {
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		SecurityContext securityContext = this.readSecurityContextFromSession(event.getSession());
		if (securityContext != null) {
			MemberManager memberManager = (MemberManager) this.getContext(event.getSession().getServletContext())
					.getBean("memberManager");
			String username = securityContext.getAuthentication().getName();
			Member member = memberManager.getMemberByLoginName(username);
			if (member != null) {
				member.setOnline(0);
				memberManager.save(member);
			}
		}
	}

	ApplicationContext getContext(ServletContext servletContext) {
		return WebApplicationContextUtils.getWebApplicationContext(servletContext);
	}

	/**
	 * Gets the security context from the session (if available) and returns it.
	 * <p/>
	 * If the session is null, the context object is null or the context object
	 * stored in the session is not an instance of SecurityContext it will
	 * return null.
	 * <p/>
	 * If <tt>cloneFromHttpSession</tt> is set to true, it will attempt to clone
	 * the context object and return the cloned instance.
	 * 
	 * @param httpSession
	 *            the session obtained from the request.
	 */
	private SecurityContext readSecurityContextFromSession(HttpSession httpSession) {
		if (httpSession == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("No HttpSession currently exists");
			}

			return null;
		}

		// Session exists, so try to obtain a context from it.

		Object contextFromSessionObject = httpSession
				.getAttribute(HttpSessionContextIntegrationFilter.SPRING_SECURITY_CONTEXT_KEY);

		if (contextFromSessionObject == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("HttpSession returned null object for SPRING_SECURITY_CONTEXT");
			}

			return null;
		}

		if (!(contextFromSessionObject instanceof SecurityContext)) {
			if (logger.isWarnEnabled()) {
				logger.warn("SPRING_SECURITY_CONTEXT did not contain a SecurityContext but contained: '"
						+ contextFromSessionObject + "'; are you improperly modifying the HttpSession directly "
						+ "(you should always use SecurityContextHolder) or using the HttpSession attribute "
						+ "reserved for this class?");
			}

			return null;
		}

		// Everything OK. The only non-null return from this method.

		return (SecurityContext) contextFromSessionObject;
	}
}
