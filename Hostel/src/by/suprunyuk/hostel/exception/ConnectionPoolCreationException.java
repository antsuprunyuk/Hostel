package by.suprunyuk.hostel.exception;

/**
 * Class inheriting Exception that may be thrown when creation of connection pool goes wrong
 * 
 * @author Anton Suprunyuk
 */
public class ConnectionPoolCreationException extends Exception {

	private static final long serialVersionUID = -2486878935495624569L;

	public ConnectionPoolCreationException() {
		super();
	}
	
	public ConnectionPoolCreationException(String message) {
		super(message);
	}

	public ConnectionPoolCreationException(Exception cause) {
		super(cause);
	}
	
	public ConnectionPoolCreationException(String message, Exception cause) {
		super(message, cause);
	}
	
}