
public class NoSpecialCharacterException extends Exception{
	public NoSpecialCharacterException() {
		//prints message stating user's error
		super("The password must contain at least one special character");
	}
}
