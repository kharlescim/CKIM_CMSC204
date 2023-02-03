
public class InvalidSequenceException extends Exception{
	public InvalidSequenceException() {
		//prints message stating user's error
		super("The password cannot contain more than two of the same character in sequence");
	}
}
