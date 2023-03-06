import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SortedDoubleLinkedList_STUDENT_Test {
	SortedDoubleLinkedList<Integer> sortedLinkedInt;
    IntegerComparator comparator;

    @Before
    public void setUp() throws Exception{
        comparator = new IntegerComparator();
        sortedLinkedInt = new SortedDoubleLinkedList<Integer>(comparator);
    }

    @After
    public void tearDown() throws Exception{
        comparator = null;
        sortedLinkedInt = null;
    }

    @Test
	public void testAddToEnd() {
		try {
			sortedLinkedInt.addToEnd(9999);
			assertTrue("Did not throw an UnsupportedOperationException", false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw an UnsupportedOperationException", true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}

    @Test
	public void testAddToFront() {
		try {
			sortedLinkedInt.addToFront(1);
			assertTrue("Did not throw an UnsupportedOperationException", false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw an UnsupportedOperationException", true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}
	
    @Test
	public void testIteratorSuccessfulNext() {
		sortedLinkedInt.add(1);
		sortedLinkedInt.add(2);
		sortedLinkedInt.add(3);
		ListIterator<Integer> iterator = sortedLinkedInt.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(1, iterator.next(),1);
		assertEquals(2, iterator.next(),1);
		assertEquals(true, iterator.hasNext());
	}

    @Test
	public void testIteratorSuccessfulIntegerPrevious() {
		sortedLinkedInt.add(3);
		sortedLinkedInt.add(2);
		sortedLinkedInt.add(1);
		ListIterator<Integer> iterator = sortedLinkedInt.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(1, iterator.next(),1);
		assertEquals(2, iterator.next(),1);
		assertEquals(3, iterator.next(),1);
		assertEquals(true, iterator.hasPrevious());
		assertEquals(3, iterator.previous(),1);
		assertEquals(2, iterator.previous(),1);
		assertEquals(1, iterator.previous(),1);
	}
    
    private class IntegerComparator implements Comparator<Integer>{
        @Override
		public int compare(Integer arg0, Integer arg1) {
			return arg0.compareTo(arg1);
		}
    }
}
