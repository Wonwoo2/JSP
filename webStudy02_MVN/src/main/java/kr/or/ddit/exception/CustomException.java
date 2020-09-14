package kr.or.ddit.exception;

public class CustomException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CustomException() {
		super();
	}

	private CustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	private CustomException(String message, Throwable cause) {
		super(message, cause);
	}

	private CustomException(String message) {
		super(message);
	}

	private CustomException(Throwable cause) {
		super(cause);
	}
}