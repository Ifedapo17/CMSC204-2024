
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author 
 *
 */
public class PasswordCheckerTest_STUDENT {
	ArrayList<String> password_ArrayList;
	String first_Password, second_Password;
	
	@Before
	public void setUp() throws Exception {
		String[] p = {"T~YAF,r251", "H^Fk.z!C;8", "m47vq/XXX3", "H:`@g)8'94", "tfeh{)4<56", "t>-gm6B:J7", 
				"Gd9`", "bwrpEat'da", "U6nTMtFhz0", "LWE_@Ma6g"};
		password_ArrayList = new ArrayList<String>(); //Declaration
		password_ArrayList.addAll(Arrays.asList(p)); //Add all the string to the list
	}

	@After
	public void tearDown() throws Exception {
		password_ArrayList = null;
	}

	/**
	 * Test if the password is less than 6 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort()
	{
		try{
			assertTrue(PasswordCheckerUtility.isValidPassword("Ct:39"));
			assertTrue("Did not throw lengthException",false);
		}
		catch(LengthException e)
		{
			assertTrue("Successfully threw a lengthExcepetion",true);
		}
		 
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides lengthException",false);
		}
	}
	
	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha()
	{
		try{
			assertTrue(PasswordCheckerUtility.isValidPassword("p5!.m9zn["));
			 
			assertTrue("Did not throw NoUpperAlphaException",false);
		}
		catch(NoUpperAlphaException e)
		{
			assertTrue("Successfully threw a NoUpperAlphaExcepetion",true);
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides NoUpperAlphaException",false);
		}
	}
	
	/**
	 * Test if the password has at least one lowercdse alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha()
	{
		try{
			assertTrue(PasswordCheckerUtility.isValidPassword("T3Q6>K]'H"));
 
			assertTrue("Did not throw NoLowerAlphaException",false);
		}
		catch(NoLowerAlphaException e)
		{
			assertTrue("Successfully threw a NoLowerAlphaExcepetion",true);
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides NoLowerAlphaException",false);
		}
	}
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsWeakPassword()
	{
		try{
			 
			boolean weakPwd = PasswordCheckerUtility.isWeakPassword("G9J}PFcRX");
			assertTrue("Did not throw WeakPassword Exception",false);
		}
		catch(WeakPasswordException e)
		{
			assertTrue("Successfully threw a WeakPasswordException",true);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			assertTrue("Threw some incorrect exception",false);
		}
	}
	
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence()
	{
		try{
			assertEquals(true,PasswordCheckerUtility.isValidPassword("123@4xxxZZZ"));
		 	assertTrue("Did not throw an InvalidSequenceException",false);
		}
		catch(InvalidSequenceException e)
		{
			assertTrue("Successfully threw an InvalidSequenceExcepetion",true);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			assertTrue("Threw some other exception besides an InvalidSequenceException",false);
		}
	}
	
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit()
	{
		try{
			assertEquals(true,PasswordCheckerUtility.isValidPassword("pGSXaZ)wM"));
		 	assertTrue("Did not throw a NoDigitException",false);
		}
		catch(NoDigitException e)
		{
			assertTrue("Successfully threw a NoDigitException",true);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			assertTrue("Threw some other exception besides a NoDigitException",false);
		}
	}
	
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful()
	{
		try {
		      assertEquals(true, PasswordCheckerUtility.isValidPassword("a9B&g!n:W-;R"));
		      assertEquals(true, PasswordCheckerUtility.isValidPassword("a9B&g!n:W-;R"));
		      assertEquals(true, PasswordCheckerUtility.isValidPassword("R9tr2M\"z;:Df"));
		      assertEquals(true, PasswordCheckerUtility.isValidPassword("eu25,WRc[tpZ"));
		    } catch (Exception e) {
		      System.out.println(e.getMessage());
		      assertTrue("Threw some incorrect exception", false);
		    }
	}
	
	/**
	 * Test the invalidPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 */
	@Test
	public void testInvalidPasswords() {
		ArrayList<String> results;
		results = PasswordCheckerUtility.getInvalidPasswords(password_ArrayList);
		
		for (String r: results)
			System.out.println("[" + r +"]");
		
		
		// Scanner scan = new Scanner(results.get(2));
		Scanner scan = new Scanner(results.get(0));
		assertEquals(scan.next(), "m47vq/XXX3");
		String nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("sequence"));
		 
		// scan = new Scanner(results.get(4));
		scan = new Scanner(results.get(1));
		assertEquals(scan.next(), "tfeh{)4<56");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("uppercase"));
		
		// scan = new Scanner(results.get(6));
		scan = new Scanner(results.get(2));
		assertEquals(scan.next(), "Gd9`");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("long"));
		
		// scan = new Scanner(results.get(7));
		scan = new Scanner(results.get(3));
		assertEquals(scan.next(), "bwrpEat'da");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("digit") );
		
		//scan = new Scanner(results.get(8));
		scan = new Scanner(results.get(4));
		assertEquals(scan.next(), "U6nTMtFhz0");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("special") );
		
	}
	
}
