package login.login.exception;



public class UserNotFound extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserNotFound(String message) {
		super(message);
	}


}
