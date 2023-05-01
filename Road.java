/**
 * @author ckim137
 */
public class Road implements Comparable<Road>{
	
	private Town source;
	private Town destination;
	private int weight;
	private String name;
	
	/**
	 * constructor - constructs new road given source and destination towns, weight, name
	 * @param source - town where road starts
	 * @param destination - town where road finishes
	 * @param weight distance - weight of road
	 * @param name - name of road
	 */
	public Road(Town source, Town destination, int weight, String name) {
		this.source = source;
		this.destination = destination;
		this.weight = weight;
		this.name = name;
	}
	/**
	 * constructor - same as above, but weight is set to 1
	 * @param source - town where road starts
	 * @param destination - town where road finishes
	 * @param name - name of road
	 */
	public Road(Town source, Town destination, String name) {
		this.source = source;
		this.destination = destination;
		this.weight = 1;
		this.name = name;
		
	}
	//getters
	/**
	 * gets source town
	 * @return source - town where road starts
	 */
	public Town getSource() {
		return source;
	}
	
	/**
	 * gets destination town
	 * @return destination - town where road ends
	 */
	public Town getDestination() {
		return destination;
	}
	
	/**
	 * gets weight of road
	 * @return weight - road weight
	 */
	public int getWeight() {
		return weight;
	}
	
	/**
	 * gets name of town
	 * @return name - road name
	 */
	public String getName() {
		return name;
	}
	
	//required methods
	/**
	 * compares roads by comparing their names
	 * @return 0 if equal, otherwise returns 1 or -1 is greater than or less than other road
	 */
	public int compareTo(Road o) {
		return name.compareTo(o.getName());
	}
	
	/**
	 * compares roads by checking whether they have the same source/destination
	 * undirected graph, so source and destination are interchangeable
	 * @param obj to be compared to
	 * @return true if vertices are equal, false otherwise
	 */
	public boolean equals(Object obj) {
		Road road = (Road) obj;
		return(road.getSource().equals(new Town(source)) && road.getDestination().equals(new Town(destination)) 
				|| road.getSource().equals(new Town(destination)) && road.getDestination().equals(new Town(source))); 
	}
	
	/**
	 * compares roads by checking whether they have the same source/destination AND they have the same name, weight
	 * @param obj to be compared to
	 * @return true if road is equal, false otherwise
	 */
	public boolean equals2(Object obj) {
		Road road = (Road) obj;
		return (this.equals(obj) && this.name.equals(road.getName()) && this.weight == road.getWeight());
	}
	
	/**
	 * checks whether town is either source or destination of road
	 * @return true if source or destination is equal to town that is being passed in, returns false otherwise
	 */
	public boolean contains(Town town) {
		return (source.equals(town) || destination.equals(town));
	}
	
	/**
	 * converts road to string representation
	 * @return string version of road
	 */
	public String toString() {
		return String.format("Source: %s, Destination: %s, Weight: %d, Name: %s", 
				source.getName(), destination.getName(), weight, name);
	}
	
}
	