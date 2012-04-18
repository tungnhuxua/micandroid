package ningbo.media.core.security;

import javax.servlet.http.HttpSessionEvent;

import org.springframework.security.web.session.HttpSessionEventPublisher;

public class OnlineSystemUserEventPublisher extends HttpSessionEventPublisher {

	public static final String LOGIN_USER_NAME = "LOGIN_USERNAME" ;
	
	
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		super.sessionCreated(event);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		super.sessionDestroyed(event);
	}

	
}
