/** 
 * CourseDBElement class that implements Comparable
 * @author ckim
 */
public class CourseDBElement implements Comparable<CourseDBElement>{
	private String id;
	private int crn;
	private int credits;
	private String roomNum;
	private String instructor;
	
	/**
	 * Constructors
	 * Default constructor sets all fields to 0/null
	 */
	public CourseDBElement() {
		id = null;
		crn = 0;
		credits = 0;;
		roomNum = null;
		instructor = null;
	}
	
	/**
	 * Constructor that sets fields to given values
	 * @param id (Course ID)
	 * @param crn (Course Number)
	 * @param credits (# of credits)
	 * @param roomNum (Course's room number)
	 * @param instructor (Course Instructor)
	 */
	public CourseDBElement(String id, int crn, int credits, String roomNum, String instructor) {
		this.id = id;
		this.crn = crn;
		this.credits = credits;
		this.roomNum = roomNum;
		this.instructor = instructor;
	}
	
	@Override
	/**
	 * Compares CDEs. 
	 * returns 1 if crns are equal. otherwise, returns 0
	 * @param CDE (CourseDBElement)
	 */
	public int compareTo(CourseDBElement CDE) {
		if(CDE.crn == crn) 
			return 1;
		return 0;
	}
	/**
	 * gets course id
	 * @return
	 */
	public String getID() {
		return id;
	}

	/**
	 * gets crn
	 * @return
	 */
	public int getCRN() {
		return crn;
	}
	
	/**
	 * gets credits
	 * @return
	 */
	public int getCredits() {
		return credits;
	}
	
	/**
	 * Gets room number
	 * @return
	 */
	public String getRoomNum() {
		return roomNum;
	}
	
	/**
	 * gets instructor name
	 * @return
	 */
	public String getInstructor() {
		return instructor;
	}
	
	//setters
	/**
	 * sets id
	 * @param id
	 */
	public void setID(String id) {
		this.id = id;
	}
	
	/**
	 * sets crn
	 * @param crn
	 */
	public void setCRN(int crn) {
		this.crn = crn;
	}
	
	/**
	 * sets credits
	 * @param credits
	 */
	public void setCredits(int credits) {
		this.credits = credits;
	}
	
	/**
	 * sets roomNum
	 * @param roomNum
	 */
	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}
	
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	
	/**
	 * toString method to be used in showAll - converts all fields in CDE to string format
	 */
	public String toString() {
		String CDEString="\nCourse:"+id+" CRN:"+crn+" Credits:"+credits+" Instructor:"+instructor+" Room:"+roomNum;
		return CDEString;
	}

	
	
	
	
}
