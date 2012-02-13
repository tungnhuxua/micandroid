package ningbq.http;

import ningbq.util.Configuration;

public class SearchNingboSupport {

	protected HttpClient http = null;
	protected String source = Configuration.getSource();
	protected final boolean USE_SSL;
	public static final String CONSUMER_KEY = Configuration
			.getOAuthConsumerKey();
	public static final String CONSUMER_SECRET = Configuration
			.getOAuthConsumerSecret();

	SearchNingboSupport() {
		USE_SSL = Configuration.useSSL();
		http = new HttpClient() ;
	}

	SearchNingboSupport(String userId, String password) {
		USE_SSL = Configuration.useSSL();
		http = new HttpClient(userId, password);
	}

	/**
	 * Returns authenticating userid
	 * 
	 * @return userid
	 */
	public String getUserId() {
		return http.getUserId();
	}

	/**
	 * Returns authenticating password
	 * 
	 * @return password
	 */
	public String getPassword() {
		return http.getPassword();
	}

	// Low-level interface
	public HttpClient getHttpClient() {
		return http;
	}

}
