 
import java.util.ArrayList;

/** Interface for a generic Stack data structure
 * 
 * @param <T> data type
 */
public class MyStack<T> implements StackInterface<T> {
	
	private ArrayList<T> stack;
	private int size;
	/**
	 * Provide two constructors
	 * 1. takes in an int as the size of the stack
	 * 2. default constructor - uses default as the size of the stack
	 */
	
	public MyStack(int size) {
		this.size = size;
		stack = new ArrayList<T>(size);
	}
	
	public MyStack() {
		size = 100;
		stack = new ArrayList<T>(100);
	}
	
	/**
	 * Determines if Stack is empty
	 * @return true if Stack is empty, false if not
	 */
	public boolean isEmpty() {
		return (stack.size() == 0);
	}
	/**
	 * Determines if Stack is full
	 * @return true if Stack is full, false if not
	 */
	public boolean isFull() {
		return (stack.size() == size);
	}

	/**
	 * Deletes and returns the element at the top of the Stack
	 * @return the element at the top of the Stack
	 * @throws StackUnderflowException if stack is empty
	 */
	public T pop() throws StackUnderflowException {
		if (isEmpty())
			throw new StackUnderflowException();
		T top = stack.get(stack.size() - 1);
		stack.remove(stack.size() - 1);
		return top;
	}
	
	/**
	 * Returns the element at the top of the Stack, does not pop it off the Stack
	 * @return the element at the top of the Stack
	 * @throws StackUnderflowException if stack is empty
	 */
	public T top() throws StackUnderflowException {
		if (isEmpty())
			throw new StackUnderflowException();
		return stack.get(stack.size() - 1);
	}

	/**
	 * Number of elements in the Stack
	 * @return the number of elements in the Stack
	 */
	public int size() {
		return stack.size();
	}
	
	/**
	 * Adds an element to the top of the Stack
	 * @param e the element to add to the top of the Stack
	 * @return true if the add was successful, false if not
	 * @throws StackOverflowException if stack is full
	 */
	public boolean push(T e) throws StackOverflowException {
		if(stack.size() == size)
			throw new StackOverflowException();
		stack.add(e);
		return true;
	}
	
	
	/**
	 * Returns the elements of the Stack in a string from bottom to top, the beginning 
	 * of the String is the bottom of the stack
	 * @return an string which represent the Objects in the Stack from bottom to top
	 */
	public String toString() {
		String str = "";
		for (T e : stack) {
			str += e;
		}
		return str;
	}
	
	/**
	 * Returns the string representation of the elements in the Stack, the beginning of the 
	 * string is the bottom of the stack
	 * Place the delimiter between all elements of the Stack
	 * @return string representation of the Stack from bottom to top with elements 
	 * separated with the delimiter
	 */
	public String toString(String delimiter) {
		String str = "";
		for (T e : stack) {
			str += e + delimiter;
		}
		//remove delimiter at end of string
		str = str.substring(0, str.length()-1);
		return str;
	}
	
	 /**
	  * Fills the Stack with the elements of the ArrayList, First element in the ArrayList
	  * is the first bottom element of the Stack
	  * YOU MUST MAKE A COPY OF LIST AND ADD THOSE ELEMENTS TO THE STACK, if you use the
	  * list reference within your Stack, you will be allowing direct access to the data of
	  * your Stack causing a possible security breech.
	  * @param list elements to be added to the Stack from bottom to top
	  * @throws StackOverflowException if stack gets full
	  */
	public void fill(ArrayList<T> list) {
		ArrayList<T> copy = new ArrayList<T>();
		for (T e : list) {
			copy.add(e);
		}
		
		//check whether adding a certain element will make the stack overflow
		for(T t : copy ) {
			try {
				push(t);
				
			} catch (StackOverflowException e) {
				e.printStackTrace();
			}
		}
	}
}
