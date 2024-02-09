import java.util.*;
import java.util.regex.*;

public class PasswordCheckerUtility {
	/**
	 * This is to compare two password to check if they are the same, and if they are not the same
	 * will throw an exception.
	 * @param password
	 * @param passwordConfirm
	 * @throws UnmatchedException
	 */
	public static void comparePasswords​(String password, String passwordConfirm) throws UnmatchedException{
			if(!(password.equals(passwordConfirm))){
				throw new UnmatchedException();
			}
	}
	
	/**
	 * Similar with the comparePasswords method but will return a boolean instead of throwing
	 * an exception
	 * @param password
	 * @param passwordConfirm
	 * @return comparison
	 */
	public static boolean comparePasswordsWithReturn(String password, String passwordConfirm) {
		boolean compare = false;
		
		if(password.equals(passwordConfirm)) {
			compare = true;
		}
		
		return compare;
	}
	
	/**
	 * This is to test the password length is not less than 6
	 * @param password
	 * @return true if an exception is not thrown
	 * @throws LengthException
	 */
	public static boolean isValidLength(String password) throws LengthException{
		if(password.length() >= 6) {
			return true;
		}
		
		throw new LengthException("For a strong and accepted password, characters must be more than 6");
	}
	
	/**
	 * This is to test the password has an upper case letter.
	 * @param password
	 * @return true if an exception is not thrown
	 * @throws NoUpperAlphaException
	 */
	public static boolean hasUpperAlpha(String password) throws NoUpperAlphaException{
		// MUST CHECK FOR THE FIRST INSTANCE OF AN UPPERCASE LETTER
		
		// AS LONG AS THERE IS 1 UPPERCASE LETTER, THE REQUIREMENT IS MET
		// SO RETURN TRUE IF A CHARACTER IS UPPERCASE
		for(int i = 0; i < password.length(); i++) {
			if(Character.isUpperCase(password.charAt(i))) {
				return true;
			}
		}
		
		// ONLY THROW AN EXCEPTION IF THERE ARE NO UPPERCASE LETTERS
		// (SHOULD BE THE LAST THING)
		throw new NoUpperAlphaException("A strong password must contain an Upper case letter");
	}
	
	/**
	 * This is to test the password has a lower case letter
	 * @param password
	 * @return true if an exception is not thrown
	 * @throws NoLowerAlphaException
	 */
	public static boolean hasLowerAlpha(String password) throws NoLowerAlphaException{
		
		// SAME THING AS THE UPPERCASE LETTER REQUIREMENT
		
		for(int i = 0; i < password.length(); i++) {
			if(Character.isLowerCase(password.charAt(i))) {
				return true;
			}
		}
		
		throw new NoLowerAlphaException("The password doesn't have lower case letters");
	}
	
	/**
	 * This is to test the password has a digit
	 * @param password
	 * @return true if an exception is not thrown
	 * @throws NoDigitException
	 */
	public static boolean hasDigit(String password) throws NoDigitException{
		// SAME THING AS THE UPPERCASE LETTER REQUIREMENT
		
		for(int i = 0; i < password.length(); i++) {
			if(Character.isDigit(password.charAt(i))) {
				return true;
			}
		}
		
		throw new NoDigitException("A strong password must contain a number");
	}
	
	/**
	 * This is to test the password has special characters
	 * @param password
	 * @return true if an exception is not thrown
	 * @throws NoSpecialCharacterException
	 */
	public static boolean hasSpecialChar(String password) throws NoSpecialCharacterException{
		// SAME THING AS UPPERCASE LETTER REQUIREMENT
		
		String[] splitter = password.split("");
		
		for(String s:splitter) {
			if(!s.matches("[a-zA-Z0-9]*")) { // CHANGED THE REGEX PATTERN TO EXCLUDE ANY NON-ALPHANUMERIC CHARS
				return true;
			}
		}
		
		throw new NoSpecialCharacterException("A strong password must contain at least one special character");
	}
	
	/**
	 * This is to test that there is no are characters in sequence. For example, 'aa' or '**'
	 * @param password
	 * @return true if an exception is not thrown
	 * @throws InvalidSequenceException
	 */
	public static boolean NoSameCharInSequence(String password) throws InvalidSequenceException{
		int characterCount = 0;
		for (int i = 0; i < password.length(); i++) {
			if(Character.isDigit(password.charAt(i))) {
				continue;
			}
			for (int j = 0; j < password.length(); j++) {
				
				// ADDED THIS IF STATEMENT
				if(characterCount > 2) {
					throw new InvalidSequenceException("A good password cannot contain more that two of the same letter or special character in sequence");
				}
				
				if (password.charAt(i) == password.charAt(j)) {
					characterCount++;
				}
			}
			
			if(characterCount > 2) {
				throw new InvalidSequenceException("A good password cannot contain more that two of the same letter or special character in sequence");
			}else {
				characterCount = 0; // RESETS COUNTER AFTER ITERATING THE WHOLE PASSWORD AND COMPARING ONE CHAR
			}
		}
		
		return true;
	}
	
	/**
	 * This is to test the length is >= 6 and <= 9
	 * @param password
	 * @return true if the length is >= 6 and <= 9
	 * @return false if the length is <= 5 or >= 10
	 */
	private static boolean hasBetweenSixAndNineChars​(String password)
	{
		
		if(password.length() >= 6 && password.length() <= 9) {
			return true;
		}
		return false;
	}
	
	/**
	 * This is to test the password is weak that is the length >= 6 and <= 9
	 * @param password
	 * @return false if an exception is not thrown
	 * @throws WeakPasswordException
	 */
	public static boolean isWeakPassword(String password) throws WeakPasswordException{
		if((hasBetweenSixAndNineChars​(password))){
			throw new WeakPasswordException("The password is OK but weak, a strong password should contain greater than or equal 10 characters.");
		}
		return false;
	}
	
	/**
	 * This is to test pass all test: has lowercase and uppercase letter, has number, do not have
	 * characters in sequence, the length is valid, and have special character.
	 * @param password
	 * @return true if an exception is not thrown
	 * @throws LengthException
	 * @throws NoUpperAlphaException
	 * @throws NoLowerAlphaException
	 * @throws NoDigitException
	 * @throws NoSpecialCharacterException
	 * @throws InvalidSequenceException
	 */
	public static boolean isValidPassword(String password)
			throws LengthException, NoUpperAlphaException, NoLowerAlphaException, NoDigitException, NoSpecialCharacterException, InvalidSequenceException
	{
		
		try {
			isValidLength(password);
			hasUpperAlpha(password);
			hasLowerAlpha(password);
			hasDigit(password);
			hasSpecialChar(password);
			NoSameCharInSequence(password);
		}
		catch(LengthException e) {
			throw new LengthException("The password must be at least 6 characters long");
		}
		catch(NoUpperAlphaException e) {
			throw new NoUpperAlphaException("Password doesn’t contain an uppercase alpha character");
		}
		catch(NoLowerAlphaException e) {
			throw new NoLowerAlphaException("Password doesn’t contain a lowercase alpha character");
		} 
		catch(NoDigitException e) {
			throw new NoDigitException("The password must contain at least one digit");
		}
		catch(NoSpecialCharacterException e) {
			throw new NoSpecialCharacterException("The password must contain at least one special character");
		}
		catch(InvalidSequenceException e) {
			throw new InvalidSequenceException("The password cannot contain more than two of the same character in sequence.");
		}
		return true;
	}
	
	/**
	 * This is to return the Array List of all invalid passwords
	 * @param passwords
	 * @return invalidPass
	 */
	public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords){
		ArrayList<String> invalidPass =  new ArrayList<>();
		for( String password : passwords) {
			
			try {
				isValidPassword(password);
			}
			
			/*
			Exceptions name: LengthException,
            NoUpperAlphaException,
            NoLowerAlphaException,
            NoDigitException,
            NoSpecialCharacterException,
            InvalidSequenceException
			*/
			
			catch(LengthException a) {
				invalidPass.add(password + " " + a.getMessage());
			}
			catch(NoUpperAlphaException b) {
				invalidPass.add(password + " " + b.getMessage());
			}
			catch(NoLowerAlphaException c) {
				invalidPass.add(password + " " + c.getMessage());
			}
			catch(NoDigitException d) {
				invalidPass.add(password + " " + d.getMessage());
			}
			catch(NoSpecialCharacterException e) {
				invalidPass.add(password + " " + e.getMessage());
			}
			catch(InvalidSequenceException f) {
				invalidPass.add(password + " " + f.getMessage());
			}
		}
		
			return invalidPass;
	}
}
