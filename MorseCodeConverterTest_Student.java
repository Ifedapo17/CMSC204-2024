import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MorseCodeConverterTest_Student {

	@Test
	public void testConvertToEnglishString() {	
		String converter1 = MorseCodeConverter.convertToEnglish(".. / .- -- / -... --- .-. -. / - --- / -.. --- -- .. -. .- - . ");
		assertEquals("i am born to dominate",converter1);
	}
	
	/**
	 * Testing for correct conversion of all characters using key phrase to hit all letters
	 */
	
	@Test
	public void testConvertMorseStringToEnglishString() {	
		
		String converter1 = MorseCodeConverter.convertToEnglish("-- -.-- / .-.. .. ..-. . / .- ... / .- / -.-. --- -- .--. ..- - . .-. / ... -.-. .. . -. -.-. . / ... - ..- -.. . -. -");
		assertEquals("my life as a computer science student", converter1);

	}
	@Test
	public void testConvertMorseFileToEnglishString() {	
		
		/*Make sure howDoILoveThee.txt is in the src directory for this 
		  test to pass
		*/
		File file = new File("Test"); 
		try {
			assertEquals("i am a computer science student who is learning to like computer science", MorseCodeConverter.convertToEnglish(file));
		} catch (FileNotFoundException e) {
			assertTrue("An unwanted exception was caught", false);
		}
	}

}
