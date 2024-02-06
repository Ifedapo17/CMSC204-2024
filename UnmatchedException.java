
public class UnmatchedException extends Exception{
	public UnmatchedException() {
		this("The passwords are uniquely different");
	}
	
	public UnmatchedException(String errorMessage) {
		super(errorMessage);
	}
}
