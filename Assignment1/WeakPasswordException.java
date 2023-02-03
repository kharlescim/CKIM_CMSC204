
public class WeakPasswordException extends Exception{
	public WeakPasswordException() {
		//prints message stating user's error
		super("The password is OK but weak - it contains fewer than 10 characters");
	}
}
