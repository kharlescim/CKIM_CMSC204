/*
 * Class: CMSC204 
 * Instructor: Kujit
 * Description: Password checker that checks whether passwords are valid/weak based on several requirements
 * Due: 02/01/2023
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming 
 * assignment independently. I have not copied the code 
 * from a student or any source. I have not given my code 
 * to any student.
   Print your Name here: Charles Kim
*/
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class PasswordCheckerUtility {

	//checks whether password is of correct length, if not, throws LengthException
	public static boolean isValidLength(String pw) throws LengthException{
		
		if(pw.length() < 6) 
			throw new LengthException();
		
		return true;
	}
	
	//checks whether password has between 6-9 chars inclusive - will be used to determine if a password is strong (over 10 chars)
	public static boolean hasBetweenSixAndNineChars(String pw){
		//if pw is between 6-9 chars, throw exception. it's a weak password
		if(pw.length() >= 6 && pw.length() <= 9)
			return true;
		return false;
	}
	
	// checks whether password has any digits - goes through each character in pw, if one is a digit, return true.
	public static boolean hasDigit(String pw) throws NoDigitException{
		
		for(int i = 0; i < pw.length(); i++) {
			if(Character.isDigit(pw.charAt(i)))
				return true;
		}
		
		throw new NoDigitException();
		
	}
	
	// checks whether password has any uppercase characters - for each char in pw, if one is uppercase, return true
	public static boolean hasUpperAlpha(String pw) throws NoUpperAlphaException{
		for(int i = 0; i < pw.length(); i++) {
			if(Character.isUpperCase(pw.charAt(i)))
				return true;
		}
		throw new NoUpperAlphaException();
	}
	
	//checks whether password has any lowercase characters - same method as above
	public static boolean hasLowerAlpha(String pw) throws NoLowerAlphaException{
		
		for(int i = 0; i < pw.length(); i++) {
			if(Character.isLowerCase(pw.charAt(i)))
				return true;
		}
		throw new NoLowerAlphaException();
	}
	
	//checks whether password has any special characters
	public static boolean hasSpecialChar(String pw) throws NoSpecialCharacterException{
		
		//checks whether password matches this pattern - if it does, then there are no special characters in the password
		Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
		Matcher matcher = pattern.matcher(pw);

		if(matcher.matches())
			throw new NoSpecialCharacterException();
		
		return true;
	}
	
	//checks whether password has an invalid sequence - no more than two of same character in sequence
	public static boolean NoSameCharInSequence(String pw) throws InvalidSequenceException{
		
		//checks whether each char in pw is equal to two chars after it - if so, throw exception
		for(int i = 0; i < pw.length()-2; i++) { 
			if(pw.charAt(i) == pw.charAt(i+1) && pw.charAt(i) == pw.charAt(i+2))
				throw new InvalidSequenceException();
		}
		
		return true;
	}
	
	//checks whether password is valid
	public static boolean isValidPassword(String pw) throws LengthException, NoDigitException, NoUpperAlphaException,
	NoLowerAlphaException, NoSpecialCharacterException, InvalidSequenceException{
		
		if(isValidLength(pw) && hasUpperAlpha(pw) && hasLowerAlpha(pw) && hasDigit(pw) 
				&& hasSpecialChar(pw) && NoSameCharInSequence(pw))
			return true;
		return false;
	}
	
	//checks whether password is weak
	public static boolean isWeakPassword(String pw) throws WeakPasswordException{
		boolean weakLength = false, isWeak = true;
		try {
			weakLength = hasBetweenSixAndNineChars(pw);
			if(weakLength == true)
				throw new WeakPasswordException();
		}
		finally {
			if(weakLength == false)
				isWeak = false;
		}
		return isWeak;
	}
	
	//checks whether two passwords are the same - if not, throws UnmatchedException
	public static void comparePasswords(String pw, String pwConfirm) throws UnmatchedException{
		if(!pw.equals(pwConfirm))
			throw new UnmatchedException();
	}
	
	//same as above method, but instead of throwing exception if false, just returns false/true instead
	public static boolean comparePasswordsWithReturn(String pw, String pwConfirm){
		if(!pw.equals(pwConfirm))
			return false;
		return true;
	}
	
	//get an arraylist of passwords, then returns a new arraylist of invalid passwords + their error messages
	public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords){
		
		//initialize empty arraylist for invalid passwords 
		ArrayList<String> invalidPasswords = new ArrayList<String>();
		
		//check each password in list to see which ones are invalid
		for(int i = 0; i < passwords.size(); i++) {
			try {
				isValidPassword(passwords.get(i));
			}
			
			//if a password is invalid, catch the exception and add the password to the list, + their corresponding error message
			catch(Exception e) {
				invalidPasswords.add(passwords.get(i) + " " + e.getMessage());
			}
		}
		return invalidPasswords;
		
	}
}
