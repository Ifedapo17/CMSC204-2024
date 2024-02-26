
public class StackOverflowException extends Exception{
	public StackOverflowException() {
		System.out.println("Stack is full");
	}
	
	public StackOverflowException(String msg) {
		super(msg);
	}
}
