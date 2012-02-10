package ningbq.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;


/**
 * Description:Encapsulation HttpResponse operation.
 * 
 * @author Devon.Ning
 * @2011-12-14
 * 
 */
public class Response {

	private final HttpResponse httpResponse;

	public Response(HttpResponse httpResponse) {
		this.httpResponse = httpResponse;
	}

	/**
	 * Description:
	 * 
	 * 
	 * 2011-12-14
	 * 
	 * @return InputStream
	 * @throws ResponseException
	 */
	public InputStream asStream() throws ResponseException {
		try {
			final HttpEntity entity = httpResponse.getEntity();
			if (entity != null) {
				return entity.getContent();
			}
		} catch (IllegalStateException e) {
			throw new ResponseException(e.getMessage(), e);
		} catch (IOException e) {
			throw new ResponseException(e.getMessage(), e);
		}
		return null;
	}

	
	/**
	 * Description:
	 * 
	 *
	 * 2011-12-14
	 * @return
	 * @throws ResponseException
	 */
	public String asString() throws ResponseException {
		try {
			final HttpEntity tempEntity = httpResponse.getEntity() ;
			//return entityToString(tempEntity);
			return EntityUtils.toString(tempEntity,HTTP.UTF_8) ;
		} catch (IOException e) {
			throw new ResponseException(e.getMessage(), e);
		}

	}

	/**
	 * Description:
	 * 
	 *
	 * 2011-12-14
	 * @return
	 * @throws ResponseException
	 */
	public JSONObject asJSONObject() throws ResponseException {
		try {
			final String tempString = asString() ;
			return new JSONObject(tempString);
		} catch (JSONException e) {
			throw new ResponseException(e.getMessage() + ":" + asString(), e);
		}

	}

	/**
	 * Description:
	 * 
	 *
	 * 2011-12-14
	 * @return
	 * @throws ResponseException
	 */
	public JSONArray asJSONArray() throws ResponseException {
		try {
			return new JSONArray(asString());
		} catch (Exception jsone) {
			throw new ResponseException(jsone.getMessage(), jsone);
		}
	}
	

	private String entityToString(HttpEntity entity) throws IOException {
		if (entity == null) {
			throw new IllegalArgumentException("HttpEntity can not be null!");
		}
		InputStream inputStream = entity.getContent();

		if (inputStream == null) {
			return "";
		}
		if (entity.getContentLength() > Integer.MAX_VALUE) {
			throw new IllegalArgumentException(
					"HttpEntity too large to be buffered in memory.");
		}

		int i = (int) entity.getContentLength();
		if (i < 0) {
			i = 4096;
		}

		Reader reader = new BufferedReader(new InputStreamReader(inputStream,
				"UTF-8"));
		CharArrayBuffer buffer = new CharArrayBuffer(i);
		try {
			char[] temp = new char[1024];
			int j;
			if ((j = reader.read(temp)) != -1) {
				buffer.append(temp, 0, j);
			}
		} finally {
			reader.close();
		}

		return buffer.toString();
	}
	
	private static Pattern escaped = Pattern.compile("&#([0-9]{3,5});");

	/**
	 * Unescape UTF-8 escaped characters to string.
	 * 
	 * 
	 * @param original
	 *            The string to be unescaped.
	 * @return The unescaped string
	 */
	public static String unescape(String original) {
		Matcher mm = escaped.matcher(original);
		StringBuffer unescaped = new StringBuffer();
		while (mm.find()) {
			mm.appendReplacement(unescaped, Character.toString((char) Integer
					.parseInt(mm.group(1), 10)));
		}
		mm.appendTail(unescaped);
		return unescaped.toString();
	}
}
