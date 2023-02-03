
public class NoDigitException extends Exception{
	public NoDigitException() {
		//prints message stating user's error
		super("The password must contain at least one digit");
	}
}