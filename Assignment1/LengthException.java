
public class LengthException extends Exception{
	public LengthException() {
		//prints message stating user's error
		super("The password must be at least 6 characters long");
	}
}
