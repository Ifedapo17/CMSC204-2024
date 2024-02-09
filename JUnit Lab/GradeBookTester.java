import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GradeBookTester {
	// Create two objects of java file GradeBook.
	GradeBook t1,
			t2;
	
	@BeforeEach
	void setUp() throws Exception {
		//Create an instance of the objects created using class GradeBook.
		t1 = new GradeBook(5);
		t2 = new GradeBook(5);
		
		//Using the addScore method to add values to the two array object.
		t1.addScore(84);
		t1.addScore(92);
		t1.addScore(83);
		t1.addScore(40);
		t1.addScore(38);
		
		t2.addScore(63);
		t2.addScore(74); 
		t2.addScore(95);
	}

	@AfterEach
	void tearDown() throws Exception {
		t1 = null;
		t2 = null;
	}

	@Test
	void testAddScore() {
		/*
		 * Compare the contents of what is in the scores array vs. what is expected to be in the
		 * scores array and compare the scoreSize to the expected number of scores entered
		 */
		t2.addScore(6.0);
		assertEquals(t1.getScoreSize(),5);
		assertEquals(t2.getScoreSize(),4); 
		}

	@Test
	void testSum() {
		// Compare what is returned by sum() to the expected sum of the scores entered
		assertEquals(337.0, t1.sum(), 0.0001);
		assertEquals(232.0, t2.sum(), 0.0001);
	}

	@Test
	void testMinimum() {
		// Compare what is returned by minimum() to the expected minimum of the scores entered
		assertEquals(38.0, t1.minimum(), 0.0001);
		assertEquals(63.0, t2.minimum(), 0.0001);
	}

	@Test
	void testFinalScore() {
		assertEquals(299.0, t1.finalScore(), 0.0001);
		assertEquals(169.0, t2.finalScore(), 0.0001);
	}

	@Test
	void testGetScoreSize() {
		assertEquals(t1.getScoreSize(), 5);
		assertEquals(t1.getScoreSize(), 5);
	}

	@Test
	void testToString() {
		assertEquals(t1.toString(),"84.0 92.0 83.0 40.0 38.0 ");
		assertEquals(t2.toString(),"63.0 74.0 95.0 ");	
	
	}

}
