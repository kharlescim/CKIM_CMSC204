
public class UnmatchedException extends Exception{
	public UnmatchedException() {
		//prints message stating user's error
		super("Passwords do not match");
	}
}