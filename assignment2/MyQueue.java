 import java.util.ArrayList;

 public class MyQueue<T> implements QueueInterface<T> {
	 
	 ArrayList<T> queue;
	    int size;
	    
	/** provide two constructors 
	 * 1. takes an int as the size of the queue
	 * 2. default constructor - uses a default as the size of the queue
	 */
	
	public MyQueue(int size) {
		this.size = size;
		queue = new ArrayList<T>(size);
	}
	
	public MyQueue() {
		size = 100;
		queue = new ArrayList<T>(100);
	}
	

	/**
	 * Determines if Queue is empty
	 * @return true if Queue is empty, false if not
	 */
	public boolean isEmpty() {
		return (queue.size() == 0);
	}

	/**
	 * Determines of the Queue is Full
	 * @return true if Queue is full, false if not
	 */
	public boolean isFull() {
		return (queue.size() == size);
	}
	
	/**
	 * Deletes and returns the element at the front of the Queue
	 * @return the element at the front of the Queue
	 * @throws QueueUnderflowException if queue is empty
	 */
	public T dequeue() throws QueueUnderflowException {
		if(isEmpty()) 
			throw new QueueUnderflowException();
		T front = queue.get(0);
		queue.remove(0);
		return front;
		
	}

	/**
	 * Returns number of elements in the Queue
	 * @return the number of elements in the Queue
	 */
	public int size() {
		return queue.size();
	}
	
	/**
	 * Adds an element to the end of the Queue
	 * @param e the element to add to the end of the Queue
	 * @return true if the add was successful
	 * @throws QueueOverflowException if queue is full
	 */
	public boolean enqueue(T e) throws QueueOverflowException {
		if(isFull())
			throw new QueueOverflowException();
		queue.add(e);
		return true;
	}
	
	/**
	 * Returns the string representation of the elements in the Queue, 
	 * the beginning of the string is the front of the queue
	 * @return string representation of the Queue with elements
	 */
	public String toString() {
		String str = "";
		for (T e : queue ) {
			str += e;
		}
		return str;
		
	}
	
	/**
	 * Returns the string representation of the elements in the Queue, the beginning of the string is the front of the queue
	 * Place the delimiter between all elements of the Queue
	 * @return string representation of the Queue with elements separated with the delimiter
	 */
	public String toString(String delimiter) {
		String str = "";
		for (T e : queue) {
			str += e + delimiter;
		}
		str = str.substring(0, str.length()-1);
		return str;
	}
	
	 /**
	  * Fills the Queue with the elements of the ArrayList, First element in the ArrayList
	  * is the first element in the Queue
	  * YOU MUST MAKE A COPY OF LIST AND ADD THOSE ELEMENTS TO THE QUEUE, if you use the
	  * list reference within your Queue, you will be allowing direct access to the data of
	  * your Queue causing a possible security breech.
	  * @param list elements to be added to the Queue
	  * @throws QueueOverflowException if queue is full
	 
	  */
	public void fill(ArrayList<T> list) {
		ArrayList<T> copy = new ArrayList<T>();
		for (T e : list) {
			copy.add(e);
		}
		
		//check whether adding a certain element will make the queue overflow
		for(T t : copy) {
			try {
				enqueue(t);
			} catch (QueueOverflowException e) {
				e.printStackTrace();
			}
		}
	}
	
 

}
