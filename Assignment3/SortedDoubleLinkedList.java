import java.util.*;

public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> {
    private Comparator<T> comparator;

    public SortedDoubleLinkedList(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public void add(T data) {
    	
    	//if size = 0 or if front data >= data, add data to front
    	if(size == 0 || comparator.compare(front.getData(), data) >= 0) {
    		super.addToFront(data);
    		return;
    	}
    	
    	//if last data <= data, add data to end
    	if(comparator.compare(end.getData(), data)<= 0) {
    		super.addToEnd(data);
    		return;
    	}
    	
    	Node indexNode = front;
    	Node temp;
    	
    	//otherwise, loop until indexNode reaches last node (tail)
    	while(indexNode != end) {
    		//if index.data >= data, insert node there and rearrange nodes
    		if(comparator.compare(indexNode.getData(), data) >= 0) {
    			temp = new Node(data, indexNode.getPrev(), indexNode);
    			indexNode.getPrev().setNext(temp);
    			indexNode.setPrev(temp);
    			size++;
    			break;
    		}
    		if (indexNode == end) {
    			super.addToEnd(data);
    		}
    		indexNode.getNext();
    	}
    }

    public void addToEnd(T data) {
    	throw new UnsupportedOperationException("Invalid operation for sorted list");
	}

	public void addToFront(T data) {
		throw new UnsupportedOperationException();
	}

	public ListIterator<T> iterator() {
		return super.iterator();
	}


	public Node remove(T data, Comparator<T> comparator) {
		return (Node) (super.remove(data, comparator));
	}
}