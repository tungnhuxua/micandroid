package ningbq.http;


public class ResponseException extends HttpException {

	private static final long serialVersionUID = -8805078543780272483L;

	public ResponseException(Exception cause) {
		super(cause);
	}

	public ResponseException(String msg, Exception cause, int statusCode) {
		super(msg, cause, statusCode);
	}

	public ResponseException(String msg, Exception cause) {
		super(msg, cause);
	}

	public ResponseException(String msg, int statusCode) {
		super(msg, statusCode);
	}

	public ResponseException(String msg) {
		super(msg);
	}

}
