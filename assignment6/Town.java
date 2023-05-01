import java.util.ArrayList;

/**
 * @author ckim137
 */
public class Town implements Comparable <Town>{

	private String name;
	private ArrayList<Town> adjacentTowns;
	
	/**
	 * constructor - constructs town with given name, empty list of adjacent towns
	 * @param name - name of town
	 */
	public Town(String name) {
		this.name=name;
		adjacentTowns=new ArrayList<Town>();
		
	}
	/**
	 * copy constructor
	 * @param templateTown - town that should be copied
	 */
	public Town(Town templateTown) {
		this(templateTown.getName());
		setAdjacentTowns(templateTown.getAdjacentTowns());
	}
	
	//methods relating to fields
	/**
	 * sets town name
	 * @param name 
	 */
	public void setName(String name) {
		this.name=name;
	}
	/**
	 * gets town name
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * sets list of adjacent towns 
	 * @param adjacentTowns
	 */
	public void setAdjacentTowns(ArrayList<Town> adjacentTowns) {
		this.adjacentTowns = new ArrayList<Town>();
		for(int i = 0;i < adjacentTowns.size(); i++) {
			this.adjacentTowns.add(adjacentTowns.get(i));
		}
	}
	/**
	 * gets list of adjacent towns
	 * @return adjacentTowns
	 */
	public ArrayList<Town> getAdjacentTowns(){
		return adjacentTowns;
	}
	
	//required methods
	/**
	 * compares 2 towns. 
	 * @return 0 if equal, otherwise returns 1 or -1
	 */
	@Override
	public int compareTo(Town town) {
		return name.compareTo(town.getName());
	}
	
	/**
	 * converts obj to town, then checks whether they are equal based on name
	 * @return true if names are equal, false otherwise
	 */
	public boolean equals(Object obj) {
		Town town = (Town)obj;
		return name.equals(town.getName()); 
	}
	
	/**
	 * gets hash code
	 * @return hash code of town name
	 */
	public int hashCode() {
		return name.hashCode();
	}
	
	/**
	 * returns string representation of town, in this case, just name of town
	 * @return name of town
	 */
	public String toString() {
		return String.format("Name: %s", name);
	}

}