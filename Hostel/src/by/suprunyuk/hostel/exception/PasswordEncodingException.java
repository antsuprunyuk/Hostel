package by.suprunyuk.hostel.exception;

/**
 * Class inheriting Exception that may be thrown when encoding process goes wrong
 * 
 * @author Anton Suprunyuk
 */
public class PasswordEncodingException extends Exception {

	private static final long serialVersionUID = -8290931614790564469L;

	public PasswordEncodingException() {
		super();
	}
	
	public PasswordEncodingException(String s) {
		super(s);
	}
	
	public PasswordEncodingException(Throwable t) {
		super(t);
	}
	
	public PasswordEncodingException(String s, Throwable t) {
		super(s, t);
	}
	
}
