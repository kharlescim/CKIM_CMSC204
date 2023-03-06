import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BasicDoubleLinkedList_STUDENT_Test {
	BasicDoubleLinkedList<Integer> linkedInteger;
	IntegerComparator comparator;
    public ArrayList<Integer> fill = new ArrayList<Integer>();

    @Before
	public void setUp() throws Exception {
		
		linkedInteger = new BasicDoubleLinkedList<Integer>();
		linkedInteger.addToEnd(1);
		linkedInteger.addToEnd(2);
		linkedInteger.addToEnd(3);
		comparator = new IntegerComparator();
	}

    @After
	public void tearDown() throws Exception {
        linkedInteger = null;
		comparator = null;
	}
    
    @Test
	public void testGetSize() {
		assertEquals(3,linkedInteger.getSize());
	}

    @Test
	public void testAddToEnd() {
		assertEquals(3, linkedInteger.getLast(), 1);
        linkedInteger.addToEnd(0);
        assertEquals(0, linkedInteger.getLast(), 1);
	}
    
    @Test
	public void testAddToFront() {
		assertEquals(1, linkedInteger.getFirst(), 1);
		linkedInteger.addToFront(8);
		assertEquals(8, linkedInteger.getFirst(), 1);
	}

    @Test
	public void testGetFirst() {
		assertEquals(1, linkedInteger.getFirst(), 1);
		linkedInteger.addToFront(8);
		assertEquals(8, linkedInteger.getFirst(),1);
	}

    @Test
	public void testGetLast() {
		assertEquals(3, linkedInteger.getLast(),1);
		linkedInteger.addToEnd(0);
		assertEquals(0, linkedInteger.getLast(),1);
	}

    @Test
	public void testToArrayList()
	{
		ArrayList<Integer> list;
		linkedInteger.addToFront(8);
		linkedInteger.addToEnd(0);
		list = linkedInteger.toArrayList();
		assertEquals(8,list.get(0),1);
		assertEquals(1,list.get(1),1);
        assertEquals(2,list.get(2),1);
        assertEquals(3, list.get(3), 1);
	}

    @Test
	public void testIteratorSuccessfulNext() {
		linkedInteger.addToFront(8);
		linkedInteger.addToEnd(0);
		ListIterator<Integer> iterator = linkedInteger.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(8, iterator.next(), 1);
		assertEquals(1, iterator.next(),1);
		assertEquals(2, iterator.next(),1);
		assertEquals(true, iterator.hasNext());
		assertEquals(3, iterator.next(),1);
	}

@Test
	public void testIteratorSuccessfulPrevious() {
		linkedInteger.addToFront(8);
		linkedInteger.addToEnd(0);
		ListIterator<Integer> iteratorInt = linkedInteger.iterator();
		assertEquals(true, iteratorInt.hasNext());
		assertEquals(8, iteratorInt.next(),1);
        assertEquals(1, iteratorInt.next(), 1);
		assertEquals(2, iteratorInt.next(),1);
		assertEquals(3, iteratorInt.next(),1);
		assertEquals(true, iteratorInt.hasPrevious());
		assertEquals(3, iteratorInt.previous(),1);
		assertEquals(2, iteratorInt.previous(),1);
		assertEquals(1, iteratorInt.previous(),1);
		assertEquals(8, iteratorInt.previous(),1);
	}
    
    @Test
	public void testIteratorNoSuchElementExceptionNext() {
		linkedInteger.addToFront(8);
		ListIterator<Integer> iteratorInt = linkedInteger.iterator();		
		assertEquals(true, iteratorInt.hasNext());
		assertEquals(8, iteratorInt.next(),1);
		assertEquals(1, iteratorInt.next(),1);
		assertEquals(2, iteratorInt.next(),1);
		assertEquals(true, iteratorInt.hasNext());
		assertEquals(3, iteratorInt.next(),1);
		
		try{
			//no more elements in list
			iteratorInt.next();
			assertTrue("Did not throw a NoSuchElementException",false);
		}
		catch (NoSuchElementException e)
		{
			assertTrue("Successfully threw a NoSuchElementException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}
		
	}
    @Test
	public void testIteratorNoSuchElementExceptionPrevious() {
		linkedInteger.addToFront(8);
		linkedInteger.addToEnd(0);
		ListIterator<Integer> iteratorInt = linkedInteger.iterator();		
		assertEquals(true, iteratorInt.hasNext());
		assertEquals(8, iteratorInt.next(),1);
		assertEquals(1, iteratorInt.next(),1);
		assertEquals(2, iteratorInt.next(),1);
		assertEquals(3, iteratorInt.next(),1);
		assertEquals(true, iteratorInt.hasPrevious());
		assertEquals(3, iteratorInt.previous(),1);
		assertEquals(2, iteratorInt.previous(),1);
		assertEquals(1, iteratorInt.previous(),1);
		assertEquals(8, iteratorInt.previous(),1);
		
		try{
			//no more elements in list
			iteratorInt.previous();
			assertTrue("Did not throw a NoSuchElementException",false);
		}
		catch (NoSuchElementException e)
		{
			assertTrue("Successfully threw a NoSuchElementException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}
		
	}
	
	@Test
	public void testIteratorUnsupportedOperationException() {
		linkedInteger.addToFront(8);
		linkedInteger.addToEnd(0);
		ListIterator<Integer> iteratorInt = linkedInteger.iterator();		
		assertEquals(true, iteratorInt.hasNext());
		assertEquals(8, iteratorInt.next(),1);
		assertEquals(1, iteratorInt.next(),1);
		assertEquals(2, iteratorInt.next(),1);
		assertEquals(true, iteratorInt.hasNext());
		assertEquals(3, iteratorInt.next(),1);
		
		try{
			//remove is not supported for the iterator
			iteratorInt.remove();
			assertTrue("Did not throw a UnsupportedOperationException",false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw a UnsupportedOperationException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
		
	}
	
	@Test
	public void testRemove() {
		assertEquals(1, linkedInteger.getFirst(),1);
		assertEquals(3, linkedInteger.getLast(),1);
		linkedInteger.addToFront(8);
		assertEquals(8, linkedInteger.getFirst(),1);
		linkedInteger.remove(8, comparator);
		assertEquals(1, linkedInteger.getFirst(), 1);
		linkedInteger.addToEnd(0);
		assertEquals(0, linkedInteger.getLast(),1);
		linkedInteger.remove(0, comparator);
		assertEquals(3, linkedInteger.getLast(),1);
		linkedInteger.addToFront(8);
		assertEquals(8, linkedInteger.getFirst(),1);
		assertEquals(3, linkedInteger.getLast(),1);
		linkedInteger.remove(2, comparator);
		assertEquals(8, linkedInteger.getFirst(),1);
		assertEquals(3, linkedInteger.getLast(),1);
		
	}

	@Test
	public void testRetrieveFirstElement() {
		assertEquals(1, linkedInteger.getFirst(),1);
		linkedInteger.addToFront(8);
		assertEquals(8, linkedInteger.getFirst(),1);
		assertEquals(8, linkedInteger.retrieveFirstElement(),1);
		assertEquals(1,linkedInteger.getFirst(),1);
		assertEquals(1, linkedInteger.retrieveFirstElement(),1);
		assertEquals(2,linkedInteger.getFirst(),1);
	}

	@Test
	public void testRetrieveLastElement() {
		assertEquals(3, linkedInteger.getLast(),1);
		linkedInteger.addToEnd(0);
		assertEquals(0, linkedInteger.getLast(),1);
		assertEquals(0, linkedInteger.retrieveLastElement(),1);
		assertEquals(3,linkedInteger.getLast(),1);
	}

    private class IntegerComparator implements Comparator<Integer>{
        @Override
        public int compare(Integer arg0, Integer arg1){
            return arg0.compareTo(arg1);
        }
    }
}