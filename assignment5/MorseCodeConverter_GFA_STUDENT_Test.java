 
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class MorseCodeConverter_GFA_STUDENT_Test {
	
	@Test
	public void testConvertToEnglishString() {	
		String converter1 = MorseCodeConverter.convertToEnglish("--- --- --. .- / -... --- --- --. .- / --. .-. .- .... .... ....");
		assertEquals("ooga booga grahhh",converter1);
	}
	
	/**
	 * Testing for correct implementation of tree and traversal
	 */
	
	@Test
	public void testPrintTree() {	
		
		assertEquals("h s v i f u e l r a p w j  b d x n c k y t z g q m o", MorseCodeConverter.printTree());
	}
	
	/**
	 * Testing for correct conversion of all characters using key phrase to hit all letters
	 */
	
	@Test
	public void testConvertMorseStringToEnglishString() {	
		
		String converter1 = MorseCodeConverter.convertToEnglish(".. - ... / --- -. .-.. -.-- / .-.. .. -.- . / --- -. . / .... --- ..- .-. / .-.. .- - . / .--. .-.. . .- ... .");
		assertEquals("its only like one hour late please", converter1);

	}
	@Test
	public void testConvertMorseFileToEnglishString() {	
		
		/*Make sure help me ahhh.txt is in the src directory for this 
		  test to pass
		*/
		File file = new File("src/help me ahhh.txt"); 
		try {
			assertEquals("help me ahhh", MorseCodeConverter.convertToEnglish(file));
		} catch (FileNotFoundException e) {
			assertTrue("An unwanted exception was caught", false);
		}
	}
	

}
