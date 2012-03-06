package ningbo.media.builder.api;

import ningbo.media.model.OAuthConfig;
import ningbo.media.model.Token;
import ningbo.media.oauth.OAuthService;
import ningbo.media.services.PlaintextSignatureService;
import ningbo.media.services.SignatureService;

public class YammerApi extends DefaultApi10a {
	private static final String AUTHORIZATION_URL = "'https://www.yammer.com/oauth/authorize?oauth_token=%s'";

	@Override
	public String getRequestTokenEndpoint() {
		return "https://www.yammer.com/oauth/request_token";
	}

	@Override
	public String getAccessTokenEndpoint() {
		return "https://www.yammer.com/oauth/access_token";
	}

	public String getAuthorizationUrl(Token requestToken) {
		return String.format(AUTHORIZATION_URL, requestToken.getToken());
	}

	public SignatureService getSignatureService() {
		return new PlaintextSignatureService();
	}

	@Override
	public OAuthService createService(OAuthConfig config) {
		// TODO Auto-generated method stub
		return null;
	}

}
