
public class StackUnderflowException extends Exception{
	public StackUnderflowException() {
		System.out.println("Stack is empty");
	}
	
	public StackUnderflowException(String msg) {
		super(msg);
	}
}
