import java.util.*;

/**
 * @author ckim137
 *
 */
public class Graph implements GraphInterface<Town,Road>{
	private ArrayList<Town> towns = new ArrayList<>();
	private ArrayList<Road> roads = new ArrayList<>();
	private int[] distance;
	private String[] prev;
	
	@Override
	/**
	 * returns road that connects the source vertex to destination vertex
	 * returns null if either parameters are null, if road that corresponds to target vertices doesn't exist in graph
	 * @param sourceVertex - road's source town
	 * @param destinationVertex - road's destination town
	 * @return road that connects vertices
	 */

	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		if (sourceVertex == null || destinationVertex == null)
			return null;
		Road road = new Road(sourceVertex,destinationVertex,"");
		for (Road r : roads) {
			if(r.equals(road))
				return r;
		}
		return null;
	}
	
	@Override
	/**
	 * adds an edge that connects two existing towns in graph
	 * throws exception if either of the parameters are null, or if they don't exist in the graph
	 * otherwise, constructs new road and sets its source/destination to the parameters, then returns the new road
	 * @param sourceVertex - road's source town
	 * @param destinationVertex - road's destination town
	 * @param weight - distance of road
	 * @param descroption - description of road
	 * @return road if road is successfully added, null otherwise
	 */

	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		if (sourceVertex == null || destinationVertex == null)
			throw new NullPointerException();
		
		if(!towns.contains(sourceVertex) || !towns.contains(destinationVertex))
			throw new IllegalArgumentException();
		
		Road road = new Road(sourceVertex,destinationVertex,weight,description);
		roads.add(road);
		return road;
	}
	
	@Override
	/**
	 * adds a new town to the graph, given that the town is not already in it
	 * if the vertex is null, throw exception
	 * @param v - town to be added into graph
	 * @return boolean - true if town is successfully added, false if it already exists in graph
	 */
	public boolean addVertex(Town v) {
		if(v == null)
			throw new NullPointerException();
		
		for (Town t : towns) {
			if(t.equals(v))
				return false;
		}
		towns.add(v);
		return true;
	}

	@Override
	/**
	 * checks whether graph contains targeted edge
	 * @param sourceVertex - source town that edge should be connected to
	 * @param destinationVertex - destination town that edge should be connected to (interchangeable)
	 * @return boolean - returns true if edge is found, false if not or if the vertices aren't in the grpah
	 */

	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		if(getEdge(sourceVertex, destinationVertex) != null)
			return true;
		return false;
	}

	@Override
	/**
	 * checks whether graph contains targeted vertex
	 * @param v - vertex that is being searched for
	 * @return true if graph contains vertex, false if vertex is null or doesn't exist
	 */
	public boolean containsVertex(Town v) {
		if(v == null)
			return false;
		
		for (Town t : towns) {
			if(t.equals(v))
				return true;
		}
		return false;
	}

	/**
	 * returns a set of all edges in the graph
	 *@return edge set with all edges in graph
	 */
	@Override
	public Set<Road> edgeSet() {
		Set<Road> edges = new HashSet<>();
		for (Road r : roads)
			edges.add(r);

		return edges;
	}

	@Override
	/**
	 * returns a set of all edges incident on target vertex
	 * if vertex is null, throw exception
	 * if graph doesn't contain vertex, throw exception
	 * otherwise, create a new edge set of all edges whose source or destination vertex are equal to target vertex
	 * @param vertex - vertex that all edges in set are incident on
	 * @return edge set of all edges incident on target vertex
	 */
	public Set<Road> edgesOf(Town vertex) {
		if (vertex == null)
			throw new NullPointerException();
		
		if(!towns.contains(vertex))
			throw new IllegalArgumentException();
		
		Set<Road> edges = new HashSet<>();
		for(Road r : roads)
			if(r.getSource().equals(vertex) || r.getDestination().equals(vertex))
				edges.add(r);
		
		return edges;
	}

	@Override
	/**
	 * removes target edge, IFF the corresponding edge and its incident vertices
	 * depending on whether the weight or description is null, compares different fields of each edge to make sure the matching edge is removed
	 * @param sourceVertex - one of vertices target edge is incident on
	 * @param destinationVertex - one of vertices target edge is incident on
	 * @param weight - weight of edge
	 * @param description - description of road 
	 * @return edge if edge is successfully removed, otherwise return null
	 */
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		Road road = new Road(sourceVertex,destinationVertex,weight,description);
		
		if (weight <= -1 && description == null)
			for(Road r : roads)
				if(r.equals(road)) {
					roads.remove(roads.indexOf(r));
					return road;
				}
		if (weight > -1 && description != null)
			for(Road r : roads)
				if(r.equals2(road)) {
					roads.remove(roads.indexOf(r));
					return road;
				}
		
		if (weight > -1 && description == null)
			for(Road r : roads)
				if(r.equals(road) && r.getWeight() == road.getWeight()) {
					roads.remove(roads.indexOf(r));
					return road;
				}
		
		if (weight <= -1 && description != null)
			for(Road r : roads)
				if(r.equals(road) && r.getName().equals(road.getName())) {
					roads.remove(roads.indexOf(r));
					return road;
				}
		return null;
	}

	@Override
	/**
	 * removes vertex and all edges incident on it from graph
	 * @param v - target vertex to be removed
	 * @return true if vertex is in graph, false otherwise
	 */
	public boolean removeVertex(Town v) {
		if(v == null || !towns.contains(v))
			return false;
		
		roads.removeAll(edgesOf(v));
		towns.remove(v);
		return true;
	}

	@Override
	/**
	 * returns a set of all vertices in graph
	 * @return set with all vertices in graph
	 */
	public Set<Town> vertexSet() {
		Set<Town> roads = new HashSet<Town>();
		for (Town t : towns) {
			roads.add(t);
		}
		return roads;
	}

	@Override
	/**
	 * finds shortest path using dijkstra algorithm
	 * @param sourceVertex - starting vertex
	 */
	public void dijkstraShortestPath(Town sourceVertex) {
		
		String name = sourceVertex.getName();
		ArrayList<String> towns = new ArrayList<String>();
		ArrayList<String> unvisited = new ArrayList<String>();
		for (Town t : vertexSet()) {
			towns.add(t.getName());
			unvisited.add(t.getName());
		}
		distance = new int[towns.size()];
		prev = new String[towns.size()];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[towns.indexOf(name)] = 0;

		while (!unvisited.isEmpty()) {
			HashMap<String, Road> adj = getAdjacentTowns(new Town(name));
			for (String s : adj.keySet())
				if (adj.get(s) != null && unvisited.indexOf(s) != -1) {
					int weight = adj.get(s).getWeight();
					int current = towns.indexOf(name);
					int index = towns.indexOf(s);
					
					if (distance[index] > distance[current] + weight) {
						prev[index] = name;
						distance[index] = weight + distance[current];
					}
				}
			
			unvisited.remove(unvisited.indexOf(name));
			if(unvisited.isEmpty())
				break;
			int minIndex = -1;
			int min = Integer.MAX_VALUE;
			for (String s : unvisited) {
				int index = towns.indexOf(s);
				if (min > distance[index]) {
					min = distance[index];
					minIndex = index;
				}
			}
			
			if(minIndex == -1)
				break;
			name = towns.get(minIndex);
		}
	}
	
	@Override
	/**
	 * finds the shortest path from a source vertex to a destination vertex 
	 * @param sourceVertex - source town
	 * @param destinationVertex - destination town
	 * @return array list of strings that describes shortest path
	 */
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
		dijkstraShortestPath(sourceVertex); 
		ArrayList<String> path = new ArrayList<String>();
		ArrayList<Integer> weight = new ArrayList<Integer>();
		ArrayList<String> towns = new ArrayList<>();
		for(Town t : vertexSet()) {
			towns.add(t.getName());
		}
		
		int index = towns.indexOf(destinationVertex.getName());
		path.add(destinationVertex.getName());
		while (prev[index] != null) {
			weight.add(distance[index]);
			path.add(prev[index]);
			index = towns.indexOf(prev[index]);

		}
		Collections.reverse(weight);
		Collections.reverse(path);
		ArrayList<String> result = new ArrayList<String>();
		int total = 0;
		for (int i=0;i<path.size()-1;i++) {
			result.add(path.get(i) + " via "
					+ ((getEdge(new Town(path.get(i)), new Town(path.get(i+1)))).getName()) + 
					" to " + path.get(i+1) + " " + (weight.get(i)-total) + " mi");
			total += weight.get(i)-total;
		}
		return result;
	}
	
	/**
	 * creates a map of all adjacent towns to target town
	 * @param town - target town where all towns in map should be adjacent to
	 * @return complete hashmap of adjacent towns
	 */
	public HashMap<String,Road> getAdjacentTowns(Town town){
		HashMap<String,Road> map = new HashMap<String,Road>();
		for(Road r : edgesOf(town)) {
			if (r.getSource().equals(town))
				map.put(r.getDestination().getName(), r);
			else 
				map.put(r.getSource().getName(), r);
		}
		return map;
	}
	
}
