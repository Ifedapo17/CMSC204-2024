import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TownTest_STUDENT {
	private Town town, town1, town2, town3, town4, town5, town6, town7, town8, town9, town10;
	private Set<Town> set = new HashSet<>();
	
    @BeforeEach
    void setUp() throws Exception {
    	town = new Town("Abeokuta");
    	town1 = new Town("Abuja");
    	town2 = new Town("Calabar");
    	town3 = new Town("Ibadan");
    	town4 = new Town("Ilorin");
    	town5 = new Town("Imo");
    	town6 = new Town("Jos");
    	town7 = new Town("Lagos");
    	town8 = new Town("Osun");
    	town9 = new Town("Port-Harcourt");
    	town10 = new Town("Warri");
    	
    	set.add(town);
    	set.add(town7);
    	set.add(town8);
    	set.add(town9);
    }

    @AfterEach
    void tearDown() throws Exception {
    	town = town1 = town2 = town3 = town4 = town5 = town6 = town7 = town8 = town9 = town10 = null;
    }

    @Test
    void testGetName() {
    	assertEquals("Abeokuta", town.getName());
        assertEquals("Lagos", town7.getName());
    }

    @Test
    void testAddAdjTown() {
    	assertTrue(set.add(town1));
    }

    @Test
    void testGetAdjTowns() {
    	
    }

    @Test
    void testEqualsTowns() {
    	assertEquals(true, town8.equals(town8));
    }

}
