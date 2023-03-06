import java.util.*;
/*
 * Class: CMSC204 
 * Instructor: Kujit
 * Description: Password checker that checks whether passwords are valid/weak based on several requirements
 * Due: 03/04/2023
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming 
 * assignment independently. I have not copied the code 
 * from a student or any source. I have not given my code 
 * to any student.
   Print your Name here: Charles Kim
*/

public class BasicDoubleLinkedList<T> implements Iterable<T> {
    protected int size;
    protected Node front, end;

    //default constructor
    public BasicDoubleLinkedList() {
        size = 0;
        front = null;
        end = null;
        }

    	
    public void addToEnd(T data) {
    	//initialize temp node to add 
    	
    	//if end is null, set end equal to temp node, then set front node equal to end node
        if(end == null) {
        	end = new Node(data);
        	front = end;
        }
        
        //else, set end's next node to temp, then set end to temp
        else {
        	Node temp = new Node(data, end, null);
        	end.next = temp;
        	end = temp;
        }
        size++;
    }

    public void addToFront(T data) {    	
    	//if front node is null, set front to temp and set end node equal to front
        if (front == null) {
            front = new Node(data);
            end = front;
        } 
        
        //else, set front node's previous node to temp, then set front node to temp
        else {
        	Node temp = new Node(data, null, front);
            front.prev = temp;
            front = temp;
        }
        
        size++;
    }

    //returns various data from list
    public T getFirst() {
    	return front.data;
    }

    public T getLast() {
        return end.data;
    }
    
    public int getSize() {
        return size;
    }
    
    public ListIterator<T> iterator() throws UnsupportedOperationException, NoSuchElementException {
        return new DoubleLinkedListIterator();
    }

    //removes first instance of data in list
    public Node remove(T targetData, Comparator<T> comparator) {
        Node index = front;
        
        //loop until index is set to null, meaning end of list or list is empty
        while (index != null) {
        	
        	//if target data is found
            if (comparator.compare(targetData, index.data) == 0) {
            	
            	//if size = 1, index.data is already set to target data. just set front and end to null
            	if (size == 1) {
            		front = null;
            		end = null;
            	}
            	//if target data is found in front node, set front to next node, set intial front to null
            	else if (index == front) {
                    front = index.next;
                    index.next.prev = null;
                    
                } 
            	
            	//if target data is found in last data, set last to previous node, set initial last node to null
            	else if (index == end) {
                    end = index.prev;
                    index.prev.next = null;
                } 
            	
            	else {
                    index.prev.next = index.next;
                    index.next.prev = index.prev;
                }

                
                //decrement size and break out of loop
                size--;
                break;
            }
            index = index.next;
        }
        return index;
    }

    //removes and returns first element
    public T retrieveFirstElement() {
        if (front != null) {
            T first = getFirst();
            
            //if size == 1, set front and end to null
            if (size == 1) {
                front = null;
                end = null;
               
            }
            else {
            //otherwise, set front to next node, set initial front to null
            front = front.next;
            front.prev = null;
          
            }
            
            //decrement size and return first
            size --;
            return first;
        }
        
        //return null if list was empty
        return null;
    }

    //removes and returns last element
    public T retrieveLastElement() {
        if (end != null) {
            T last = getLast();
            
            //if size == 1, set front and end nodes to null
            if (size == 1) {
                front = null;
                end = null;
            }
            else {
            //else, set end to previous node, set initial end node to null
            end = end.prev;
            end.next = null;
            }
            
            size--;
            return last;
        }
        
        //return null if list was empty
        return null;
    }

    //convert linked list to array list
    public ArrayList<T> toArrayList() {
    	
    	//initializing new arraylist and iterator
        ArrayList<T> list = new ArrayList<T>();
        DoubleLinkedListIterator iterator = (DoubleLinkedListIterator) iterator();
        
        //while iterator detects next node, adds next node to list
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }

    public class Node {
        private T data;
        private Node prev, next;
        
        //default constructor
        Node() {
        	this.data = null;
        	prev = null;
        	next = null;
        }
        

        //constructor to assign node to data
        Node(T data){
            this.data = data;
            prev = null;
            next = null;
        }
        
        //constructor to insert node for add methods
        Node(T data, Node prev, Node next) {
        	this.data = data;
        	this.prev = prev;
        	this.next = next;
        }
        //various helper getters/setters for SortedDoubleLinkedList
        public T getData() {
        	return data;
        }
        
        public Node getPrev() {
        	return prev;
        }
        
        public Node getNext() {
        	return next;
        }
        
        public void setNext(Node next) {
        	this.next = next;
        }
        
        public void setPrev(Node prev) {
        	this.prev = prev;
        }
        
    }

    protected class DoubleLinkedListIterator implements ListIterator<T> {
        Node next;
        Node prev = null;

        //default constructor - sets next to head of list
        DoubleLinkedListIterator() {
            next = front;
        }
        
        //unimplemented - throws UnsupportedOperationException
        public void add(T arg0) {
            throw new UnsupportedOperationException();
        }

        //checks if next node is null or not. if so, return false. if not, return true
        public boolean hasNext() {
            return (next != null);
        }

        //checks if previous node is null or not. if so, return false. if not, return true
        public boolean hasPrevious() {
            return (prev != null);
        }

        //returns next data element
        public T next() throws NoSuchElementException{
        	
        	//if next node is null/missing, throw exception
            if(!hasNext())
                throw new NoSuchElementException();
            
            //otherwise, set previous node to next node, and set next node to next next node. then, return previous node's data
            //(now next node data) essentially moving iterator up one index
            prev = next;
            next = next.next;
            return prev.data;
        }
        
        //unimplemented - throw UnsupportedOperationException
        public int nextIndex() {
            throw new UnsupportedOperationException();
        }

        public T previous() throws NoSuchElementException {
        	//if previous node is null/missing, throw exception
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            
            //otherwise, set next node to previous node, and previous node to prev prev node. tuen, return next node's data
            //now previous node data. essentially moving iterator up one index
            next = prev;
            prev = prev.prev;
            return next.data;
        }
        
      //unimplemented - throw UnsupportedOperationException
        public int previousIndex() {
            throw new UnsupportedOperationException();
        }

      //unimplemented - throw UnsupportedOperationException
        public void remove() {
            throw new UnsupportedOperationException();
        }

      //unimplemented - throw UnsupportedOperationException
        public void set(T arg0) {
            throw new UnsupportedOperationException();
        }

    }
}