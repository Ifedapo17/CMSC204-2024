import java.io.*;
import java.util.*;

public class Graph implements GraphInterface<Town, Road>{
	protected Town current;
	protected Set<Road> roads;
	protected Set<Town> towns;
	protected ArrayList<String> shortcut = new ArrayList<>();
	protected ArrayList<Town> unvisited = new ArrayList<>();
	protected ArrayList<Town> visited = new ArrayList<>();
	protected final int INFINITY = Integer.MAX_VALUE;
	
	public Graph() {
		this.roads = new HashSet<>();
		this.towns = new HashSet<>();
	}
	
	@Override
	public Road addRoad(Town sourceTown, Town destinationTown, int distance, String name)
			throws NullPointerException, IllegalArgumentException {
		// TODO Auto-generated method stub
		if(sourceTown == null || destinationTown == null || name == null) {
			throw new NullPointerException();
		}else if(!towns.contains(sourceTown) || !towns.contains(destinationTown)) {
			throw new IllegalArgumentException();
		}
		
		Road road = new Road(sourceTown, destinationTown, distance, name);
		roads.add(road);
		
		return road;
	}
	@Override
	public Road addRoad(Road road) {
		// TODO Auto-generated method stub
		if(!roads.contains(road)) {
			roads.add(road);
			return road;
		}
		
		return null;
	}
	@Override
	public Road getRoad(Town sourceTown, Town destinationTown) {
		// TODO Auto-generated method stub
		for(Road r :roads) {
			if(r.contains(sourceTown) && r.contains(destinationTown)) {
				return r;
			}
		}
		
		return null;
	}
	@Override
	public boolean addTown(Town town) throws NullPointerException {
		// TODO Auto-generated method stub
		if(town == null) {
			throw new NullPointerException("No input given");
		}else if (towns.contains(town)) {
			return false;
		}
		
		return towns.add(town);
	}
	@Override
	public boolean containsRoad(Town sourceTown, Town destinationTown) {
		// TODO Auto-generated method stub
		for(Road r : roads) {
			if(r.contains(sourceTown) && r.contains(destinationTown)) {
				return true;
			}
		}
		
		return false;
	}
	@Override
	public boolean containsTown(Town town) {
		// TODO Auto-generated method stub
		return towns.contains(town);
	}
	@Override
	public Set<Road> getRoads() {
		// TODO Auto-generated method stub
		return roads;
	}
	@Override
	public Set<Road> getRoadsOf(Town town) throws IllegalArgumentException, NullPointerException {
		// TODO Auto-generated method stub
		Set<Road> roadsOf = new HashSet<>();
		
		if(towns == null) {
			throw new NullPointerException();
		}
		
		for(Road r : roads) {
			if(r.contains(town)) {
				roadsOf.add(r);
			}
		}
		
		return roadsOf;
	}
	@Override
	public Road removeRoad(Road road) {
		// TODO Auto-generated method stub
		Road var = null;
		
		for(Road temp : roads) {
			if(temp.compareTo(road) == 0) {
				var = road;
			}
		}
		
		roads.remove(var);
		
		return road;
	}
	@Override
	public Road removeRoad(Town sourceVertex, Town destinationVertex, int weight, String description) {
		// TODO Auto-generated method stub
		if(sourceVertex == null || destinationVertex == null) {
			return null;
		}else if(!(towns.contains(sourceVertex) || towns.contains(destinationVertex))) {
			throw new IllegalArgumentException();
		}
		
		for(Road road : roads) {
			if(road.contains(sourceVertex) && road.contains(destinationVertex) && road.getDistance() == weight && road.getName().equals(description)) {
				roads.remove(road);
				return road;
			}
		}
		
		return null;
	}
	@Override
	public boolean removeTown(Town town) {
		// TODO Auto-generated method stub
		if(!(towns.contains(town)) || (town == null)) {
			return false;
		}
		
		return towns.remove(town);
	}
	@Override
	public Set<Town> getSetOfTowns() {
		// TODO Auto-generated method stub
		return towns;
	}
	@Override
	public Town getTown(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ArrayList<String> getShortestPath(Town sourceTown, Town destinationTown) {
		// TODO Auto-generated method stub
		dijkstraShortestPath(sourceTown);
		ArrayList<String> shortcutList = new ArrayList<>();
		this.current = destinationTown;
		
		
		while(current.previousNode != null) {
			shortcutList.add(current.getName() + " via " + current.previousRoad + " to " + current.previousNode.name + " " + current.shortestPath + " mi");
			current = current.previousNode;
		}
		
		Collections.reverse(shortcutList);
		return shortcutList;
	}
	@Override
	public void dijkstraShortestPath(Town sourceTown) {
		// TODO Auto-generated method stub
		visited = null;
		
		for(Town x : towns) {
			unvisited.add(x);
		}
		
		for(Town town : unvisited) {
			town.shortestPath = INFINITY;
			town.previousNode = null;
		}
		
		sourceTown.shortestPath = 0;
		
		while(!unvisited.isEmpty()) {
			Town smallest = unvisited.get(0);
			
			for(Town current : unvisited) {
				if(current.getShortestDistance() < smallest.getShortestDistance()) {
					smallest = current;
				}
			}
			
			for(Town x : smallest.getAdjTowns()) {
				int distance = 0;
				Road r = null;
				
				for(Road y : roads) {
					if((y.getDestination().equals(smallest) && y.getSource().equals(x)) || (y.getDestination().equals(x) && y.getSource().equals(smallest))) {
						distance += y.getDistance() + smallest.shortestPath;
						r = y;
					}
				}
				
				if(distance < x.shortestPath) {
					x.shortestPath = distance;
					x.previousNode = smallest;
					x.previousRoad = r;
				}
			}
			
			visited.add(smallest);
		}
	}
	@Override
	public ArrayList<String> getSortedListOfTowns() {
		// TODO Auto-generated method stub
		ArrayList<String> temp = new ArrayList<>();
		
		for(Town town : towns) {
			temp.add(town.getName());
		}
		
		Collections.sort(temp);
		return temp;
	}
	@Override
	public ArrayList<String> getSortedListOfRoads() {
		// TODO Auto-generated method stub
		ArrayList<String> temp = new ArrayList<>();
		
		for(Road road : roads) {
			temp.add(road.getName());
		}
		
		Collections.sort(temp);
		return temp;
	}
	
	@Override
	public void populateTownGraph(File file) throws FileNotFoundException {
		// TODO Auto-generated method stub
		String[] x;
		String string;
		
		Scanner scan = new Scanner(file);
		
		while(scan.hasNextLine()) {
			string = scan.nextLine();
			x = string.split(",|;");
			Town source = new Town(x[2].trim());
			Town destination = new Town(x[3].trim());
			int distance = Integer.parseInt(x[1].trim());
			String name_of_Road = x[0].trim();
			
			addTown(source);
			addTown(destination);
			addRoad(source, destination, distance, name_of_Road);
		}
		
		scan.close();
	}
}
