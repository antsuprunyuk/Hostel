package by.suprunyuk.hostel.exception;

/**
 * Class inheriting Exception that may be thrown when connection pool works improperly or crash
 * 
 * @author Anton Suprunyuk
 */
public class ConnectionPoolWorkException extends Exception {

	private static final long serialVersionUID = -6679984811663936788L;

	public ConnectionPoolWorkException() {
		super();
	}
	
	public ConnectionPoolWorkException(String message) {
		super(message);
	}
	
	public ConnectionPoolWorkException(Exception cause) {
		super(cause);
	}
	
	public ConnectionPoolWorkException(String string, Exception cause) {
		super(string, cause);
	}	
}
