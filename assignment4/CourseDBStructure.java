import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class CourseDBStructure implements CourseDBStructureInterface{
	
	private int size;
	private LinkedList<CourseDBElement>[] hashTable;
	private final double LOADING_FACTOR = 1.5;
	
	/**Constructor that calculates size of hashTable using given num
	 * @param num
	 */
	public CourseDBStructure(int num) {
		size = (int) (num/LOADING_FACTOR);
		while(!isPrime(size) || (size % 4 != 3)) //loop until size is next possible 4k + 3 prime number
			size++;
		hashTable = new LinkedList[size];
		for (int i = 0; i < size; i++) 
			hashTable[i] = new LinkedList<CourseDBElement>();
	}
	
	/**
	 * test constructor
	 * @param testing string used for testing
	 * @param num size of hashTable
	 */
	public CourseDBStructure(String testing, int num) {
		size = num;
		hashTable = new LinkedList[size];
		for (int i = 0; i < size; i++) 
			hashTable[i] = new LinkedList<CourseDBElement>();
	}
	
	/**
	 * checks whether given number is prime or not - used to calculate next 4k + 3 prime for size
	 * @param num to check prime
	 * @return
	 */
	public boolean isPrime(int num) {
		if(num <= 1)
			return false;
		
		for(int i = 2; i < num; i++) {
			if(num % i == 0)
				return false;
		}
		return true;
	}
	
	@Override
	/** 
	* Adds a CourseDBElement object to the CourseDBStructure using the hashcode
	* of the CourseDatabaseElemen object's crn value.
	* If the CourseDatabaseElement already exists, exit quietly
	*  
	* @param element the CourseDBElement to be added to CourseDBStructure
	*/
	public void add (CourseDBElement CDE) {
		String crn = Integer.toString(CDE.getCRN()); //parse crn to string to find hashcode
		int index = crn.hashCode() % size; //do hashcode % size to get correct index in hashTable
		
		//if index is empty, can simply add CDE with no issues
		if (hashTable[index].isEmpty()) {
			hashTable[index].add(CDE);
			return;
		}
		
		//otherwise, loop through every CDE in the index to check if they are the same. if so, update that CDE
		for(int i = 0; i < hashTable[index].size(); i++) {
			if(hashTable[index].get(i).compareTo(CDE) == 1);
				hashTable[index].set(0, CDE);
		}
	}
	
	@Override
	/**
	 * Find a courseDatabaseElement based on the key (crn) of the
	 * courseDatabaseElement If the CourseDatabaseElement is found return it If not,
	 * throw an IOException
	 * 
	 * @param crn crn (key) whose associated courseDatabaseElement is to be returned
	 * @return a CourseDBElement whose crn is mapped to the key
	 * @throws IOException if key is not found
	 */
	public CourseDBElement get(int crn) throws IOException {
		String crnString = Integer.toString(crn); //parse crn to string for hashcode
		int index = crnString.hashCode() % size; //get index from hashcode of crnString, % size
		
		//check whether index is empty - if so, throw exception
		if(hashTable[index].isEmpty())
			throw new IOException();
		
		//otherwise, loop through every CDE in index to check if any match. if so, return that CDE
		for(int i = 0; i < hashTable[index].size(); i++) {
			if(hashTable[index].get(i).getCRN() == crn)
				return hashTable[index].get(i);
		}
		
		//if can't find matching CDE, throw exception
		throw new IOException();
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
		ArrayList<String> list = new ArrayList<>();
		
		//check through each index in hashTable - if not empty, add every CDE in that index to the list.
		for(int i = 0; i < size; i++) {
			if(!hashTable[i].isEmpty()) {
				for(int j = 0; j < hashTable[i].size(); j++)
					list.add(hashTable[i].get(j).toString());
			}
		}
		return list;
	}
	
	@Override
	/**
	* Returns the size of the ConcordanceDataStructure (number of indexes in the array)
	*/
	public int getTableSize() {
		return size;
	}		
}
