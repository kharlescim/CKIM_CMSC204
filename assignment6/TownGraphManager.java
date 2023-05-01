/*
 * Class: CMSC204 
 * Instructor: Kujit
 * Description: Creates and manages a network of towns and roads 
 * Due: 04/30/2023
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
import java.util.Collections;
import java.util.Scanner;

public class TownGraphManager implements TownGraphManagerInterface {

	Graph graph = new Graph();
	
	@Override
	/**
	 * adds a road to the graph
	 */
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		return (graph.addEdge(new Town(town1), new Town(town2), weight, roadName) != null);			
	}

	@Override
	/**
	 * checks whether the graph contains a specified edge, returns it if so
	 */
	public String getRoad(String town1, String town2) {
		return (graph.getEdge(new Town(town1), new Town(town2)).getName());
	}

	@Override
	/**
	 * adds a town to the grpah
	 */
	public boolean addTown(String v) {
		return graph.addVertex(new Town(v));
	}
	
	@Override
	/**
	 * checks whether the graph contains a specified town, returns it if so
	 */
	public Town getTown(String name) {
		if(graph.containsVertex(new Town(name))) 
			return new Town(name);
		else 
			return null;
	}

	@Override
	/**
	 * checks whether the graph contains a specified town
	 */
	public boolean containsTown(String v) {
		return graph.containsVertex(new Town(v));
	}

	@Override
	/**
	 * checks whether the graph contains a specified edge
	 */
	public boolean containsRoadConnection(String town1, String town2) {
		return graph.containsEdge(new Town(town1), new Town(town2));
	}

	@Override
	/**
	 * returns an arraylist of all roads in the graph
	 */
	public ArrayList<String> allRoads() {
		ArrayList<Road> roads = new ArrayList<Road>(graph.edgeSet());
		ArrayList<String> roadNames = new ArrayList<String>();
		for(Road r : roads) {
			roadNames.add(r.getName());
		}
		Collections.sort(roadNames);
		return roadNames;
	}

	@Override
	/**
	 * deletes a road form the graph
	 */
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		return (graph.removeEdge(new Town(town1), new Town(town2), -1, road) != null);
	}

	@Override
	/**
	 * deletes a town from the graph
	 */
	public boolean deleteTown(String v) {
		return graph.removeVertex(new Town(v));
	}

	@Override
	/**
	 * gets arraylist of all towns in the graph
	 */
	public ArrayList<String> allTowns() {
		ArrayList<Town> towns = new ArrayList<Town>(graph.vertexSet());
		ArrayList<String> output = new ArrayList<String>();
		for(Town t: towns) {
			output.add(t.getName());
		}
		Collections.sort(output);
		return output;
	}

	@Override
	/**
	 * gets shortest path from one town to another
	 */
	public ArrayList<String> getPath(String town1, String town2) {
		return graph.shortestPath(new Town(town1), new Town(town2));
	}

	/**
	 * reads in data from a file, and adds the corresponding edge/vertex to the graph
	 * first it splits the line by its delimiters, into the road, weight of the road, and source/destination vertices
	 * then it constructs the corresponding roads and towns and adds them to the graph
	 * @param file to be read from
	 * @throws FileNotFoundException
	 */
	public void populateTownGraph(File file) throws FileNotFoundException {
		Scanner sc = new Scanner(file);
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			String[] split = line.split(",;|");
			String road = split[0];
			int weight = Integer.parseInt(split[1]);
			String source = split[2];
			String destination = split[3];

			Town town1 = new Town(source);
			Town town2 = new Town(destination);
			graph.addVertex(town1);
			graph.addVertex(town2);
			graph.addEdge(town1, town2, weight, road);
		}
		sc.close();
	}

}