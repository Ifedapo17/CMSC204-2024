import java.util.*;

public class Town implements Comparable<Town>{
	protected int shortestPath;
	protected Town previousNode;
	protected Road previousRoad;
	protected String name;
	protected Set<Town> neighbors;
	
	/**
	 * Parameterized constructor
	 * @param name
	 */
	public Town(String name) {
		this.name = name;
		
	}
	
	/**
	 * Adds the given town to the set of adjacent towns of the current town
	 * @param adjTown - the adjacent town to be added
	 */
	public void addAdjTown(Town adjTown) {
		neighbors.add(adjTown);
	}
	
	/**
	 * Gets the name of a town
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Retrieves the set of adjacent towns
	 * @return set of adjacent towns
	 */
	public Set<Town> getAdjTowns(){
		return neighbors;
	}
	
	/**
	 * Gets the shortest distance from town, this attribute is used for Dijkstra's algorithm
	 * @return the shortestPath
	 */
	public int getShortestDistance() {
		return shortestPath;
	}

	/**
	 * Sets the shortest distance from town, this attribute is used for Dijkstra's algorithm
	 * @param shortestPath - the shortestPath to set
	 */
	public void setShortestDistance(int shortestPath) {
		this.shortestPath = shortestPath;
	}

	/**
	 * Gets the predecessor town used in the shortest distance path of Dijkstra's algorithm
	 * @return the previousNode
	 */
	public Town getPreviousNode() {
		return previousNode;
	}

	/**
	 * Sets the predecessor town used in the shortest distance path of Dijkstra's algorithm
	 * @param previousNode - the previousNode to set
	 */
	public void setPreviousNode(Town previousNode) {
		this.previousNode = previousNode;
	}
	
	/**
	 * String representation of a Town instance
	 * @return name of the town
	 */
	@Override
	public String toString() {
		return name;
	}
	
	/**
	 * Checks the current town with the given one for equality. Two towns are equal if they have the same name
	 * @return true if the current town is equal to the given town, false otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		Town x = (Town) obj;
		if(this.name.equals(x.getName())) {
			return true;
		}
		
		return false;
	}

	@Override
	public int compareTo(Town o) {
		// TODO Auto-generated method stub
		return name.compareTo(o.getName());
	}

}
