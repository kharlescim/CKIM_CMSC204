
public class NoUpperAlphaException extends Exception{
	public NoUpperAlphaException() {
		//prints message stating user's error
		super("The password must contain at least one uppercase alphabetic character");
	}
}
