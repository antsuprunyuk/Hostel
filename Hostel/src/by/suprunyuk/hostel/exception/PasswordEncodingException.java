package by.suprunyuk.hostel.exception;

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
