import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyQueue <T> implements QueueInterface<T>{
	//Data fields
	private T[] queue;
	private int frontIndex;
	private int backIndex;
	private int count;
	private boolean integrityOK;
	private static final int DEFAULT_CAPACITY = 50;
	private static final int MAX_CAPACITY = 10000;
	
	//Constructors
	
	/*
	 * Thic constructor is if there is no parameter. It sets the array size of the queue to 50
	 */
	public MyQueue() {
		this(DEFAULT_CAPACITY);
	}
	
	/*
	 * This constructor is if there is a parameter set. It
	 */
	public MyQueue(int initialCapacity) {
		integrityOK = false;
		checkCapacity(initialCapacity);
		
		@SuppressWarnings("unchecked")
		T[] tempQueue = (T[]) new Object[initialCapacity + 1];
		queue = tempQueue;
		frontIndex = 0;
		backIndex = initialCapacity;
		count = 0;
		integrityOK = true;
	}
	
	/**
	 * Determines if Queue is empty
	 * @return true if Queue is empty, false if not
	 */
	@Override
	public boolean isEmpty() {
		checkIntegrity();
		if(frontIndex == ((backIndex + 1) % queue.length)) {
			return true;
		}
		
		return false;
	}

	/**
	 * Determines if the Queue is full
	 * @return true if Queue is full, false if not
	 */
	@Override
	public boolean isFull() {
		checkIntegrity();
		if(frontIndex == ((backIndex + 2) % queue.length)) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Deletes and returns the element at the front of the Queue
	 * @return the element at the front of the Queue
	 * @throws QueueUnderflowException - if queue is empty
	 */
	@Override
	public T dequeue() throws QueueUnderflowException {
		checkIntegrity();
		
		if(isEmpty()) {
			throw new QueueUnderflowException();
		}else {
			T front = queue[frontIndex];
			queue[frontIndex] = null;
			count--;
			frontIndex = (frontIndex + 1) % queue.length;
			return front;
		}
	}

	/**
	 * Returns number of elements in the Queue
	 * @return the number of elements in the Queue
	 */
	@Override
	public int size() {
//		if(isFull()) {
//			return queue.length - 1;
//		}else if(isEmpty()) {
//			return 0;
//		}
		
		return count;
	}

	/**
	 * Adds an element to the end of the Queue
	 * @param newEntry - the element to add to the end of the Queue
	 * @return true if the add was successful
	 * @throws QueueOverflowException - if queue is full
	 */
	@Override
	public boolean enqueue(T newEntry) throws QueueOverflowException {
		checkIntegrity();
		if(isFull()) {
			throw new QueueOverflowException();
		}
		
		backIndex = (backIndex + 1) % queue.length;
		queue[backIndex] = newEntry;
		count++;
		return true;
	}
	
	/**
	 * Returns the String representation of the elements in the Queue, the beginning of the String is the front of the queue
	 * @return String representation of the Queue with elements
	 */
	@Override
	public String toString() {
		String str = "";
		int i = -1;
		for(i = frontIndex; i != backIndex; i = ((i + 1) % queue.length)) {
			str += queue[i].toString();
		}
		
		if(i == backIndex) {
			str += queue[i].toString();
		}
		
		return str;
	}

	/**
	 * Returns the String representation of the elements in the Queue, the beginning of the string is the front of the queue
	 * Place the delimiter between all elements of the Queue
	 * @return String representation of the Queue with elements separated with the delimiter
	 */
	@Override
	public String toString(String delimiter) {
		String str = "";
		int i = -1;
		for(i = frontIndex; i != backIndex; i = ((i + 1) % queue.length)) {
			str += queue[i].toString() + delimiter;
		}
		
		if(i == backIndex) {
			str += queue[i].toString();
		}
		
		return str;
	}

	/**
	 * Fills the Queue with the elements of the ArrayList, First element in the ArrayList is the first element in the Queue YOU MUST MAKE A COPY OF LIST AND ADD THOSE ELEMENTS TO THE QUEUE, if you use the list reference within your Queue, you will be allowing direct access to the data of your Queue causing a possible security breech.
	 * @param list - elements to be added to the Queue
	 * @throws QueueOverflowException - if queue is full
	 */
	@Override
	public void fill(ArrayList<T> list) throws QueueOverflowException {
		ArrayList<T> copyList = new ArrayList<T>(List.copyOf(list));
		
		for(int i = 0; i < copyList.size(); i++) {
			enqueue(copyList.get(i));
		}
	}
	
	private void checkCapacity(int initialCapacity) {
		if(initialCapacity > MAX_CAPACITY) {
			throw new IllegalStateException("Attempted to create a stack whose size is greater than + max capacity");
		}
	}
	
	private void checkIntegrity() {
		if(!integrityOK) throw new SecurityException("Queue object is corrupt.");
	}
}
