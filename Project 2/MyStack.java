import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyStack<T> implements StackInterface<T>{
	private T[] stack;
	private int topIndex;
	private boolean integrityOK;
	private static final int DEFAULT_CAPACITY = 50;
	private static final int MAX_CAPACITY = 10000;
	
	public MyStack() {
		this(DEFAULT_CAPACITY);
	}
	
	public MyStack(int initialCapacity) {
		integrityOK = false;
		checkCapacity(initialCapacity);
		
		@SuppressWarnings("unchecked")
		T[] tempStack = (T[]) new Object[initialCapacity];
		stack = tempStack;
		topIndex = -1;
		integrityOK = true;
	}
	
	/**
	 * Determines if Stack is empty
	 * @return true if Stack is empty, false if not
	 */
	@Override
	public boolean isEmpty() {
		if(topIndex == -1) {
			return true;
		}
		
		return false;
	}

	/**
	 * Determines if Stack is full
	 * @return true if Stack is full, false if not
	 */
	@Override
	public boolean isFull() {
		if(topIndex == (stack.length - 1)) {
			return true;
		}
		return false;
	}

	/**
	 * Deletes and returns the element at the top of the Stack
	 * @return the element at the top of the Stack
	 * @throws StackUnderflowException - if stack is empty
	 */
	@Override
	public T pop() throws StackUnderflowException {
		checkIntegrity();
		
		if(isEmpty()) {
			throw new StackUnderflowException();
		}else {
			T top = stack[topIndex];
			stack[topIndex] = null;
			topIndex--;
			return top;
		}
	}

	/**
	 * Returns the element at the top of the Stack, does not pop it off the Stack
	 * @return the element at the top of the Stack
	 * @throws StackUnderflowException - if stack is empty
	 */
	@Override
	public T top() throws StackUnderflowException {
		checkIntegrity();
		
		if(isEmpty()) {
			throw new StackUnderflowException();
		}else {
			return stack[topIndex];
		}
	}

	/**
	 * Gets the number of elements in the Stack
	 * @return the number of elements in the Stack
	 */
	@Override
	public int size() {
		return topIndex + 1;
	}

	/**
	 * Adds an element to the top of the Stack
	 * @param e - the element to add to the top of the Stack
	 * @return true if the add was successful, false if not
	 * @throws StackOverflowException - if stack is full
	 */
	@Override
	public boolean push(T e) throws StackOverflowException {
		checkIntegrity();
		if(isFull()) {
			integrityOK = false;
			throw new StackOverflowException();
		}else {
			integrityOK = true;
			stack[topIndex + 1] = e;
			topIndex++;
		}
		return integrityOK;
	}
	
	/**
	 * Returns the elements of the Stack in a String from bottom to top, the beginning of the String is the bottom of the stack
	 * @return a String which represent the objects in the Stack from bottom to top
	 */
	@Override
	public String toString() {
		String str = "";
		
		for(int i = 0; i <= topIndex; i++) {
			str += stack[i].toString();
		}
		
		return str;
	}

	/**
	 * Returns the String representation of the elements in the Stack, the beginning of the string is the bottom of the stack
	 * Place the delimiter between all elements of the Stack
	 * @return String representation of the Stack from bottom to top with elements separated with the delimiter
	 */
	@Override
	public String toString(String delimiter) {
		String str = "";
		
		for(int i = 0; i < (topIndex + 1); i++) {
			if(i == topIndex) {
				str += stack[i].toString();
			}else {
				str += stack[i].toString() + delimiter;
			}
		}
		
		return str;
	}

	/**
	 * Fills the Stack with the elements of the ArrayList, First element in the ArrayList is the first bottom element of the Stack YOU MUST MAKE A COPY OF LIST AND ADD THOSE ELEMENTS TO THE STACK, if you use the list reference within your Stack, you will be allowing direct access to the data of your Stack causing a possible security breech.
	 * @param list - elements to be added to the Stack from bottom to top
	 * @throws StackOverflowException - if stack gets full
	 */
	@Override
	public void fill(ArrayList<T> list) throws StackOverflowException {
		ArrayList<T> copyList = new ArrayList<T>(List.copyOf(list));
		
		for(int i = 0; i < copyList.size(); i++) {
			push(copyList.get(i));
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
