
public class QueueOverflowException extends Exception{
	public QueueOverflowException() {
		System.out.println("Queue is full");
	}
	
	public QueueOverflowException(String msg) {
		super(msg);
	}
}
