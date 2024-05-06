import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

class GraphTest_STUDENT {
    private Graph graph;

    @BeforeEach
    void setUp() throws Exception {
        graph = new Graph();
        populateGraph();
    }

    @AfterEach
    void tearDown() throws Exception {
        graph = null;
    }

    private void populateGraph() {
        Town town = new Town("Abeokuta");
        Town town1 = new Town("Abuja");
        Town town2 = new Town("Calabar");
        Town town3 = new Town("Ibadan");
        Town town4 = new Town("Ilorin");
        Town town5 = new Town("Imo");
        Town town6 = new Town("Jos");
        Town town7 = new Town("Lagos");
        Town town8 = new Town("Osun");
        Town town9 = new Town("Port-Harcourt");
        Town town10 = new Town("Warri");

        graph.addTown(town);
        graph.addTown(town1);
        graph.addTown(town2);
        graph.addTown(town3);
        graph.addTown(town4);
        graph.addTown(town5);
        graph.addTown(town6);
        graph.addTown(town7);
        graph.addTown(town8);
        graph.addTown(town9);
        graph.addTown(town10);

        graph.addRoad(town9, town5, 2, "Aba");
        graph.addRoad(town, town8, 4, "Ife");
        graph.addRoad(town7, town, 1, "Ikeja");
        graph.addRoad(town1, town6, 10, "Lafia");
        graph.addRoad(town4, town1, 7, "Minna");
        graph.addRoad(town, town3, 3, "Odeda");
        graph.addRoad(town3, town4, 5, "Ogbomoso");
        graph.addRoad(town8, town4, 5, "Osogbo");
        graph.addRoad(town10, town9, 4, "Rivers");
        graph.addRoad(town7, town10, 5, "Warri");
        graph.addRoad(town9, town2, 3, "Uyo");
    }

    @Test
    void testAddRoadTownTownIntString() {
        graph.addRoad(new Town("Benin"), new Town("Calabar"), 17, "IDK");
        assertTrue(graph.containsRoad(new Town("Benin"), new Town("Calabar")));
    }

    @Test
    void testAddRoadRoad() {
        Road road = new Road(new Town("Kano"), new Town("Sokoto"), 13, "LOL");
        graph.addRoad(road);
        assertTrue(graph.containsRoad(new Town("Kano"), new Town("Sokoto")));
    }

    @Test
    void testAddRoadWithNullSource() {
        graph.addRoad(null, new Town("Calabar"), 17, "IDK");
        assertTrue(graph.containsRoad(null, new Town("Calabar")));
    }

    @Test
    void testAddRoadWithNullDestination() {
        graph.addRoad(new Town("Benin"), null, 17, "IDK");
        assertTrue(graph.containsRoad(new Town("Benin"), null));
    }

    @Test
    void testGetRoad() {
        assertEquals(new Road(new Town("Abeokuta"), new Town("Ibadan"), 3, "Odeda"), graph.getRoad(new Town("Abeokuta"), new Town("Ibadan")));
    }

    @Test
    void testAddTown() {
        Town town = new Town("Cairo");
        assertFalse(graph.containsTown(town));
        graph.addTown(town);
        assertTrue(graph.containsTown(town));
    }

    @Test
    void testContainsRoad() {
        assertTrue(graph.containsRoad(new Town("Lagos"), new Town("Abeokuta")));
        assertFalse(graph.containsRoad(new Town("Calabar"), new Town("Jos")));
    }

    @Test
    void testContainsTown() {
        assertFalse(graph.containsTown(new Town("Cairo")));
        assertTrue(graph.containsTown(new Town("Imo")));
    }

    @Test
    void testGetTown() {
        assertNotNull(graph.getTown("Abeokuta"));
        assertNull(graph.getTown("Cairo"));
    }

    @Test
    void testGetSetOfTowns() {
        Set<Town> set = graph.getSetOfTowns();
        assertTrue(set.contains(new Town("Imo")));
        assertFalse(set.contains(new Town("Cairo")));
    }

    @Test
    void testRemoveRoad() {
        assertTrue(graph.containsRoad(new Town("Port-Harcourt"), new Town("Calabar")));
        graph.removeRoad(new Road(new Town("Port-Harcourt"), new Town("Calabar"), 3, "Uyo"));
        assertFalse(graph.containsRoad(new Town("Port-Harcourt"), new Town("Calabar")));
    }

    @Test
    void testRemoveTown() {
        assertTrue(graph.containsTown(new Town("Lagos")));
        graph.removeTown(new Town("Lagos"));
        assertFalse(graph.containsTown(new Town("Lagos")));
    }

    @Test
    void testShortestPathFromTownAToTownB() {
        ArrayList<String> shortestPath = graph.getShortestPath(new Town("Abeokuta"), new Town("Ilorin"));
        assertNotNull(shortestPath);
        assertTrue(shortestPath.size() > 0);
        assertEquals("Abeokuta via Odeda to Ibadan 3 mi", shortestPath.get(0));
        assertEquals("Ibadan via Ogbomoso to Ilorin 5 mi", shortestPath.get(1));
    }

    @Test
    void testPopulateTownGraph() {
        
    }
}