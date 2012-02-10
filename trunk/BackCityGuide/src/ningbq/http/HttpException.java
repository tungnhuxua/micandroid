package ningbq.http;

public class HttpException extends Exception {

	private static final long serialVersionUID = -5538273669251812425L;

	private int statusCode = -1;

	public HttpException(String msg) {
		super(msg);
	}

	public HttpException(Exception cause) {
		super(cause);
	}

	public HttpException(String msg, int statusCode) {
		super(msg);
		this.statusCode = statusCode;
	}

	public HttpException(String msg, Exception cause) {
		super(msg, cause);
	}

	public HttpException(String msg, Exception cause, int statusCode) {
		super(msg, cause);
		this.statusCode = statusCode;
	}

	public int getStatusCode() {
		return this.statusCode;
	}

}
