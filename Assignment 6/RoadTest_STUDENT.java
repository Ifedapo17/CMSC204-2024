import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RoadTest_STUDENT {
	private Town town, town1, town2, town3, town4;
	private Road road, road1, road2, road3;
	private Set<Road> set = new HashSet<>();
	
	@BeforeEach
	void setUp() throws Exception {
		town = new Town("Abeokuta");
		town1 = new Town("Lagos");
		town2 = new Town("Warri");
		town3 = new Town("Ibadan");
		town4 = new Town("Osun");
		
		road = new Road(town1, town, 1, "Ikeja");
		road1 = new Road(town1, town2, 3, "Sapele");
		road2 = new Road(town, town4, 5, "Osogbo");
		
		set.add(road);
		set.add(road1);
		set.add(road2);
	}

	@AfterEach
	void tearDown() throws Exception {
		town = town1 = town2 = town3 = town4 = null;
		road = road1 = road2 = road3 = null;
	}

	@Test
	void testGetName() {
		assertEquals("Ikeja", road.getName());
	}

	@Test
	void testGetDestination() {
		assertEquals(town2, road1.getDestination());
	}

	@Test
	void testGetSource() {
		assertEquals(town, road2.getSource());
	}

	@Test
	void testGetDistance() {
		assertEquals(3, road1.getDistance());
	}

	@Test
	void testContains() {
		assertTrue(road.contains(town));
	}

	@Test
	void testEqualsRoads() {
		assertTrue(road2.equals(road2));
	}

}
