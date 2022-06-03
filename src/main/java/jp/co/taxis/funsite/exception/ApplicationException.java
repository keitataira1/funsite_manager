package jp.co.taxis.funsite.exception;

/**
 * アプリケーションエラー.
 * @author tomal
 *
 */
public class ApplicationException extends RuntimeException {

	/**
	 * コンストラクタ.
	 */
	public ApplicationException() {
		super();
	}

	/**
	 * コンストラクタ.
	 */
	public ApplicationException(String message) {
		super(message);
	}

	/**
	 * コンストラクタ.
	 */
	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
	}

}
