import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CourseDBManager_Student_Test {
	private CourseDBManagerInterface dataMgr = new CourseDBManager();

	/**
	 * Create an instance of CourseDBManager
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		dataMgr = new CourseDBManager();
	}

	/**
	 * Set dataMgr reference to null
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		dataMgr = null;
	}

	/**
	 * Test for the add method
	 */
	@Test
	public void testAddToDB() {
		try {
			dataMgr.add("CMSC204",31672,4,"M204","ME");
		}
		catch(Exception e) {
			fail("This should not have caused an Exception");
		}
	}
	
	/**
	 * Test for the showAll method
	 */
	@Test
	public void testShowAll() {
		dataMgr.add("CMSC204",31672,4,"HT301","ME");
		dataMgr.add("CMSC203",31700,4,"HT302","HIM");
		dataMgr.add("CMSC205",33000,4,"HT304","HER");
		ArrayList<String> list = dataMgr.showAll();
		//Changed the order of this test because instructions were unclear on how to implement showAll method
		assertEquals(list.get(1),"\nCourse:CMSC204 CRN:31672 Credits:4 Instructor:ME Room:HT301");
	 	assertEquals(list.get(2),"\nCourse:CMSC203 CRN:31700 Credits:4 Instructor:HIM Room:HT302");
		assertEquals(list.get(0),"\nCourse:CMSC205 CRN:33000 Credits:4 Instructor:HER Room:HT304");
	}
	
	/**
	 * Test for the read method
	 */
	@Test
	public void testRead() {
		try {
			File inputFile = new File("Test1.txt");
			PrintWriter inFile = new PrintWriter(inputFile);
			inFile.println("CMSC204 31672 4 HT301 ME");
			inFile.print("CMSC203 31700 4 HT302 HIM");
			
			inFile.close();
			dataMgr.readFile(inputFile);
			assertEquals("CMSC204",dataMgr.get(31672).getID());
			assertEquals("CMSC203",dataMgr.get(31700).getID());
			assertEquals("HT301",dataMgr.get(31672).getRoomNum());
		} catch (Exception e) {
			fail("Should not have thrown an exception");
		}
	}
}
