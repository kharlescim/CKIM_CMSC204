/*
 * Class: CMSC204 
 * Instructor: Kujit
 * Description: Creates and manages a databases of courses using hashing
 * Due: 03/26/2023
 * Platform/compiler:
 * I pledge that I have completed the programming 
 * assignment independently. I have not copied the code 
 * from a student or any source. I have not given my code 
 * to any student.
   Print your Name here: Charles Kim
*/
import java.io.*;
import java.util.*;

public class CourseDBManager implements CourseDBManagerInterface {

	
	private CourseDBStructure CDS;
	
	/** 
	 * default constructor for CourseDBManager
	 */
	CourseDBManager() {
		CDS = new CourseDBStructure(20);
	}
	
	@Override
	/**
	 * Adds a course (CourseDBElement) with the given information
	 * to CourseDBStructure.
	 * @param id course id 
	 * @param crn course crn
	 * @param credits number of credits
	 * @param roomNum course room number
	 * @param instructor name of the instructor
	 */
	public void add(String id, int crn, int credits, String roomNum, String instructor) {
		CDS.add(new CourseDBElement(id, crn, credits, roomNum, instructor));
	}
	
	@Override
	/**
	 * finds  CourseDBElement based on the crn key
	 * @param crn course crn (key)
	 * @return a CourseDBElement object
	 * 
	 */
	public CourseDBElement get(int crn) {
		//try to find matching CDS. if match can't be found, throw exception and return null
		try {
			return CDS.get(crn);
		}catch (IOException E) {
			return null;
		}
	}
	
	@Override
	/**
	 * Reads the information of courses from a test file and adds them
	 * to the CourseDBStructure data structure
	 * @param input input file 
	 * @throws FileNotFoundException if file does not exists
	 */
	public void readFile(File input) throws FileNotFoundException {
		
		Scanner sc = new Scanner(input);
		
		while(sc.hasNextLine()) {
			String[] temp = sc.nextLine().split("\\s");
			
			for(int i = 4; i < temp.length; i++) {
				String instructor = "" + temp[i] + " ";
				add(temp[0], Integer.valueOf(temp[1]), Integer.valueOf(temp[2]), temp[3], 
						instructor.substring(0, instructor.length() -1));
			}
			
		}
		sc.close(); //close
	}
	
	@Override
	/**
	 * @return an array list of string representation of each course in 
	 * the data structure separated by a new line. 
	 * Refer to the following example:
	 * Course:CMSC500 CRN:39999 Credits:4 Instructor:Nobody InParticular Room:SC100
	 * Course:CMSC600 CRN:4000 Credits:4 Instructor:Somebody Room:SC200
	 */
	public ArrayList<String> showAll() {
		return CDS.showAll();
	}

}