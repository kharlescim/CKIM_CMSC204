
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This is the test file for the CourseDBManager which is implemented from the
 * CourseDBManagerInterface
 * 
 */
public class CourseDBStructure_Student_Test {
	CourseDBStructure cds, testStructure;

	@Before
	public void setUp() throws Exception {
		cds = new CourseDBStructure(30);
		testStructure = new CourseDBStructure("Testing", 30);
	}

	@After
	public void tearDown() throws Exception {
		cds = testStructure = null;
	}

	/**
	 * Test the tableSize for CourseDBStructures constructed with both constructors
	 */
	@Test
	public void testGetTableSize() {
		assertEquals(23, cds.getTableSize());
		assertEquals(30, testStructure.getTableSize());
	}

	@Test
	public void testHashTable() {

		//Create a course 
		CourseDBElement cde1 = new CourseDBElement("CMSC204", 50000, 4, "HT301", "ME");
		
		cds.add(cde1);  //add to data structure
		cds.add(cde1);  // add it again. add method  should  ignore it
	 
		ArrayList<String> courseList = cds.showAll(); 
		assertTrue(courseList.size()==1);  
		
		//Create another course
		CourseDBElement cde2 = new CourseDBElement("CMSC203", 3268, 4, "HT302", "HIM");
	 
 		try {
			assertEquals(50000, cds.get(cde1.getCRN()).getCRN());  
			cds.get(cde2.getCRN()).getCRN(); // should throw exception
		} catch (IOException e) {

			assertTrue("threw Exception successfuly for the course not found", true);
		}
		
 		cds.add(cde2);
 		courseList = cds.showAll(); 
		assertTrue(courseList.size()==2);  
		
		try {
			assertEquals(3268, cds.get(cde2.getCRN()).getCRN());
		} catch (IOException e) {
			 
			fail("Should not throw exception");
		}  
		CourseDBElement cde1Update = new CourseDBElement("CMSC204-updated", 50000, 4, "SC100", "updated");
		cds.add(cde1Update);  //Same CRN updated information
 		courseList = cds.showAll(); 
		assertTrue(courseList.size()==2);  
		
		try {
			assertEquals(50000, cds.get(cde1Update.getCRN()).getCRN());
			assertEquals("CMSC204-updated", cds.get(cde1Update.getCRN()).getID());
		} catch (IOException e) {
			 
			fail("Should not throw exception");
		}  
		testStructure.add(cde1); 
		courseList = testStructure.showAll(); 
		assertTrue(courseList.size()==1); 
	}
}
