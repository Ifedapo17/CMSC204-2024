
public class Road implements Comparable<Road>{
	
	protected Town beginning,
					end;
	protected int time;
	protected String path;
	
	/**
	 * Parameterized constructor of a Road, it initialized the attributes to the
	 * corresponding given values
	 * @param source - source town of the road
	 * @param destination - destination town of the road
	 * @param distance - the distance between source and destination towns
	 * @param name- name of the road
	 */
	public Road(Town source, Town destination, int distance, String name) {
		this.beginning = source;
		this.end = destination;
		this.time = distance;
		this.path = name;
	}
	
	/**
	 * Returns true only if the road contains the given town; either source or
	 * destination is the given town
	 * @param town - either end of a road
	 * @return true - only if the road is connected to the given town
	 */
	public boolean contains(Town town) {
		return this.beginning.equals(town) || this.end.equals(town);
	}
	
	/**
	 * Checks the current road with the given one for equality. Two roads are equal
	 * if they have the same source and destination or same destination and source,
	 * since the roads will are undirected.
	 * @return true - if current road is equal to the given road, false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj == this) {
			return true;
		}
		
		if(!(obj instanceof Road)) {
			return false;
		}
		
		Road road = (Road)obj;
		
		return ((beginning.equals(road.getSource())) &&
				(end.equals(road.getDestination())) ||
				((beginning.equals(road.getDestination())) &&
						(end.equals(road.getSource()))));
	}
	
	/**
	 * @return the path
	 */
	public String getName() {
		return path;
	}
	
	/**
	 * @return the beginning
	 */
	public Town getDestination() {
		return end;
	}

	/**
	 * @return the end
	 */
	public Town getSource() {
		return beginning;
	}

	/**
	 * @return the time
	 */
	public int getDistance() {
		return time;
	}
	
	/**
	 * Compares two roads based on their name
	 * @return 0 if the name is equal to the other name. A value less than 0 is
	 * returned if the name is less than the other name and a value greater than 0
	 * if the name is greater than the other name.
	 */
	@Override
	public int compareTo(Road o) {
		// TODO Auto-generated method stub
		return path.compareTo(o.getName());
	}

}
