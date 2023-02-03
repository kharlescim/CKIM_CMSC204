
public class NoLowerAlphaException extends Exception{
	public NoLowerAlphaException() {
		//prints message stating user's error
		super("The password must contain at least one lowercase alphabetic character");
	}
}