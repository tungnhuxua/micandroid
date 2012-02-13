package ningbq.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.AccessControlException;
import java.util.Properties;

public class Configuration {
	private static Properties defaultProperty;

	static {
		init();
	}

	@SuppressWarnings("unused")
	static void init() {
		defaultProperty = new Properties();
		// defaultProperty.setProperty("searchningbo.debug", "false");
		defaultProperty.setProperty("searchningbo.debug", "true");
		defaultProperty.setProperty("searchningbo.source", "searchningbo");
		// defaultProperty.setProperty("searchningbo.clientVersion","");
		defaultProperty.setProperty("searchningbo.clientURL",
				"http://api.searchningbo.com/searchningbo.xml");
		defaultProperty.setProperty("searchningbo.http.userAgent",
				"searchningbo 1.0");
		// defaultProperty.setProperty("searchningbo.user","");
		// defaultProperty.setProperty("searchningbo.password","");
		defaultProperty.setProperty("searchningbo.http.useSSL", "false");
		// defaultProperty.setProperty("searchningbo.http.proxyHost","");
		defaultProperty.setProperty("searchningbo.http.proxyHost.fallback",
				"http.proxyHost");
		// defaultProperty.setProperty("searchningbo.http.proxyUser","");
		// defaultProperty.setProperty("searchningbo.http.proxyPassword","");
		// defaultProperty.setProperty("searchningbo.http.proxyPort","");
		defaultProperty.setProperty("searchningbo.http.proxyPort.fallback",
				"http.proxyPort");
		defaultProperty.setProperty("searchningbo.http.connectionTimeout",
				"20000");
		defaultProperty.setProperty("searchningbo.http.readTimeout", "120000");
		defaultProperty.setProperty("searchningbo.http.retryCount", "3");
		defaultProperty
				.setProperty("searchningbo.http.retryIntervalSecs", "10");
		// defaultProperty.setProperty("searchningbo.oauth.consumerKey","");
		// defaultProperty.setProperty("searchningbo.oauth.consumerSecret","");
		defaultProperty.setProperty("searchningbo.async.numThreads", "1");
		defaultProperty.setProperty("searchningbo.clientVersion", "1.0");
		try {
			// Android platform should have dalvik.system.VMRuntime in the
			// classpath.
			// @see
			// http://developer.android.com/reference/dalvik/system/VMRuntime.html
			Class.forName("dalvik.system.VMRuntime");
			defaultProperty.setProperty("searchningbo.dalvik", "true");
		} catch (ClassNotFoundException cnfe) {
			defaultProperty.setProperty("searchningbo.dalvik", "false");
		}
		DALVIK = getBoolean("searchningbo.dalvik");
		String t4jProps = "searchningbo.properties";
		boolean loaded = loadProperties(defaultProperty, "."
				+ File.separatorChar + t4jProps)
				|| loadProperties(
						defaultProperty,
						Configuration.class.getResourceAsStream("/WEB-INF/"
								+ t4jProps))
				|| loadProperties(defaultProperty,
						Configuration.class.getResourceAsStream("/" + t4jProps));
	}

	private static boolean loadProperties(Properties props, String path) {
		try {
			File file = new File(path);
			if (file.exists() && file.isFile()) {
				props.load(new FileInputStream(file));
				return true;
			}
		} catch (Exception ignore) {
		}
		return false;
	}

	private static boolean loadProperties(Properties props, InputStream is) {
		try {
			props.load(is);
			return true;
		} catch (Exception ignore) {
		}
		return false;
	}

	private static boolean DALVIK;

	public static boolean isDalvik() {
		return DALVIK;
	}

	public static boolean useSSL() {
		return getBoolean("searchningbo.http.useSSL");
	}

	public static String getScheme() {
		return useSSL() ? "https://" : "http://";
	}

	public static String getCilentVersion() {
		return getProperty("searchningbo.clientVersion");
	}

	public static String getCilentVersion(String clientVersion) {
		return getProperty("searchningbo.clientVersion", clientVersion);
	}

	public static String getSource() {
		return getProperty("searchningbo.source");
	}

	public static String getSource(String source) {
		return getProperty("searchningbo.source", source);
	}

	public static String getProxyHost() {
		return getProperty("searchningbo.http.proxyHost");
	}

	public static String getProxyHost(String proxyHost) {
		return getProperty("searchningbo.http.proxyHost", proxyHost);
	}

	public static String getProxyUser() {
		return getProperty("searchningbo.http.proxyUser");
	}

	public static String getProxyUser(String user) {
		return getProperty("searchningbo.http.proxyUser", user);
	}

	public static String getClientURL() {
		return getProperty("searchningbo.clientURL");
	}

	public static String getClientURL(String clientURL) {
		return getProperty("searchningbo.clientURL", clientURL);
	}

	public static String getProxyPassword() {
		return getProperty("searchningbo.http.proxyPassword");
	}

	public static String getProxyPassword(String password) {
		return getProperty("searchningbo.http.proxyPassword", password);
	}

	public static int getProxyPort() {
		return getIntProperty("searchningbo.http.proxyPort");
	}

	public static int getProxyPort(int port) {
		return getIntProperty("searchningbo.http.proxyPort", port);
	}

	public static int getConnectionTimeout() {
		return getIntProperty("searchningbo.http.connectionTimeout");
	}

	public static int getConnectionTimeout(int connectionTimeout) {
		return getIntProperty("searchningbo.http.connectionTimeout",
				connectionTimeout);
	}

	public static int getReadTimeout() {
		return getIntProperty("searchningbo.http.readTimeout");
	}

	public static int getReadTimeout(int readTimeout) {
		return getIntProperty("searchningbo.http.readTimeout", readTimeout);
	}

	public static int getRetryCount() {
		return getIntProperty("searchningbo.http.retryCount");
	}

	public static int getRetryCount(int retryCount) {
		return getIntProperty("searchningbo.http.retryCount", retryCount);
	}

	public static int getRetryIntervalSecs() {
		return getIntProperty("searchningbo.http.retryIntervalSecs");
	}

	public static int getRetryIntervalSecs(int retryIntervalSecs) {
		return getIntProperty("searchningbo.http.retryIntervalSecs",
				retryIntervalSecs);
	}

	public static String getUser() {
		return getProperty("searchningbo.user");
	}

	public static String getUser(String userId) {
		return getProperty("searchningbo.user", userId);
	}

	public static String getPassword() {
		return getProperty("searchningbo.password");
	}

	public static String getPassword(String password) {
		return getProperty("searchningbo.password", password);
	}

	public static String getUserAgent() {
		return getProperty("searchningbo.http.userAgent");
	}

	public static String getUserAgent(String userAgent) {
		return getProperty("searchningbo.http.userAgent", userAgent);
	}

	public static String getOAuthConsumerKey() {
		return getProperty("searchningbo.oauth.consumerKey");
	}

	public static String getOAuthConsumerKey(String consumerKey) {
		return getProperty("searchningbo.oauth.consumerKey", consumerKey);
	}

	public static String getOAuthConsumerSecret() {
		return getProperty("searchningbo.oauth.consumerSecret");
	}

	public static String getOAuthConsumerSecret(String consumerSecret) {
		return getProperty("searchningbo.oauth.consumerSecret", consumerSecret);
	}

	public static boolean getBoolean(String name) {
		String value = getProperty(name);
		return Boolean.valueOf(value);
	}

	public static int getIntProperty(String name) {
		String value = getProperty(name);
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException nfe) {
			return -1;
		}
	}

	public static int getIntProperty(String name, int fallbackValue) {
		String value = getProperty(name, String.valueOf(fallbackValue));
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException nfe) {
			return -1;
		}
	}

	public static long getLongProperty(String name) {
		String value = getProperty(name);
		try {
			return Long.parseLong(value);
		} catch (NumberFormatException nfe) {
			return -1;
		}
	}

	public static String getProperty(String name) {
		return getProperty(name, null);
	}

	public static String getProperty(String name, String fallbackValue) {
		String value;
		try {
			value = System.getProperty(name, fallbackValue);
			if (null == value) {
				value = defaultProperty.getProperty(name);
			}
			if (null == value) {
				String fallback = defaultProperty.getProperty(name
						+ ".fallback");
				if (null != fallback) {
					value = System.getProperty(fallback);
				}
			}
		} catch (AccessControlException ace) {
			// Unsigned applet cannot access System properties
			value = fallbackValue;
		}
		return replace(value);
	}

	private static String replace(String value) {
		if (null == value) {
			return value;
		}
		String newValue = value;
		int openBrace = 0;
		if (-1 != (openBrace = value.indexOf("{", openBrace))) {
			int closeBrace = value.indexOf("}", openBrace);
			if (closeBrace > (openBrace + 1)) {
				String name = value.substring(openBrace + 1, closeBrace);
				if (name.length() > 0) {
					newValue = value.substring(0, openBrace)
							+ getProperty(name)
							+ value.substring(closeBrace + 1);

				}
			}
		}
		if (newValue.equals(value)) {
			return value;
		} else {
			return replace(newValue);
		}
	}

	public static int getNumberOfAsyncThreads() {
		return getIntProperty("searchningbo.async.numThreads");
	}

	public static boolean getDebug() {
		return getBoolean("searchningbo.debug");

	}

}
