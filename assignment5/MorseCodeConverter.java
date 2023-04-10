/*
 * Class: CMSC204 
 * Instructor: Kujit
 * Description: morse code converter using trees
 * Due: 04/09/2023
 * Platform/compiler:
 * I pledge that I have completed the programming 
 * assignment independently. I have not copied the code 
 * from a student or any source. I have not given my code 
 * to any student.
   Print your Name here: Charles Kim
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author ckim137
 */
public class MorseCodeConverter {
	
	private static MorseCodeTree tree = new MorseCodeTree();;
	
	//default constructor
	public MorseCodeConverter() {
	}
	
	/**
	 * converts morse code file to english
	 * @param codeFile
	 * @return
	 * @throws FileNotFoundException
	 */
	public static String convertToEnglish(File codeFile) throws FileNotFoundException{
		
		//variables - scanner sc to read file, String str to convert scanned line to english
		Scanner sc = new Scanner(codeFile);
		String str = convertToEnglish(sc.nextLine());
		
		sc.close(); //close scanner
		
		return str;
	}
	
	/**
	 * converts morse code string to english
	 * @param code
	 * @return
	 */
	public static String convertToEnglish(String code) {
		
		//variables - String array temp to hold substrings of code split up, string str to hold final result
		String[] temp = code.split(" "); 
		String str = "";
		
		//for each string s in temp, if s = /, add space delimiter. otherwise, add corresponding data from tree
		for(String s : temp) {
			
			if(s.equals("/")) 
				str += " ";
			
			else
				str += tree.fetch(s);
		}
		return str;
	}
	
	/**
	 * prints tree in LNR order using LNRoutputTraversal method
	 * @return
	 */
	public static String printTree() {
		
		//variables - String list temp to hold arraylist form of temp, string str to hold final result
		ArrayList<String> temp = tree.toArrayList();
		String str = "";
		
		//for each string s in temp, add s to str with " " delimiter
		for(String s : temp) {
			str += s + " ";
		}
		
		str = str.substring(0, str.length()-1); //remove last space from str, then return
		return str;
	}
}
