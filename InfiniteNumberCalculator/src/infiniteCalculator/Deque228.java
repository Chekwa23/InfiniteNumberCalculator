package infiniteCalculator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Implements the Deque interface using a Deque implementation in a HAS A relationship
 * @author Lucas Onwuchekwa
 * @param <E>
 */
public class Deque228<E> implements Deque
{
	//Using either linkedList or ArrayList as teh backing data structure
	LinkedList<E> temp = new LinkedList<>();
//	ArrayList<E> temp = new ArrayList<>();
	int head = 0;
	int tail = 0;

	/**
	 * Empty constructor
	 */
	public Deque228()
	{
	}
	
	@Override
	public boolean isEmpty() 
	{
		return temp.isEmpty();
	}

	@Override
	public Object[] toArray() 
	{
		return temp.toArray();
	}

	@Override
	public Object[] toArray(Object[] a) 
	{
		return temp.toArray(a);
	}

	@Override
	public boolean containsAll(Collection c) 
	{
		return temp.containsAll(c);
	}

	@Override
	public boolean removeAll(Collection c) 
	{
		return temp.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection c) 
	{
		return temp.retainAll(c);
	}

	@Override
	public void clear() 
	{
		temp.clear();
	}

	@Override
	public boolean remove(Object o) 
	{
		return temp.remove(o);
	}

	@Override
	public boolean contains(Object o) 
	{
		return temp.contains(o);
	}

	@Override
	public int size() 
	{
		return temp.size();
	}

	@Override
	public Iterator iterator() 
	{
		return temp.iterator();
	}
	
	@Override
	public boolean addAll(Collection c) 
	{
		return temp.addAll(c);
	}
	
	@Override
	public boolean add(Object e) 
	{
		return temp.add((E) e);
	}

	//---------------------------------------------------------------------------------------

	@Override
	public void addLast(Object e) 
	{
		temp.add((E) e);
	}
	
	@Override
	public Object removeFirst() 
	{
		if(isEmpty())
		{
			throw new NullPointerException();
		}
		return temp.remove(0);
	}
	
	@Override
	public Object pop() 
	{
		return removeFirst();
	}
	
	@Override
	public Object remove() 
	{
		if(isEmpty())
		{
			throw new NullPointerException();
		}
		return removeFirst();
	}
	
	@Override
	public void addFirst(Object e) 
	{
//		int i = temp.size();
//		if(i == 0)
//		{
//			temp.add((E) e);
//		}
//		else
//		{
//			temp.add(temp.get(i-1));
//			for(; i > 1 ; i--) 
//			{
//				temp.set(i-1, temp.get(i-2));
//			}
//			temp.set(0, (E) e);
//		}
		temp.addFirst((E) e);
	}

	@Override
	public Object removeLast() 
	{
		if(isEmpty())
		{
			throw new NullPointerException();
		}
		return temp.remove(temp.size()-1);
	}

	@Override
	public boolean offerFirst(Object e) 
	{
		addFirst(e);
		return true;
	}

	@Override
	public boolean offerLast(Object e) 
	{
		return temp.add((E) e);
	}

	@Override
	public Object getLast() 
	{
		if(isEmpty())
		{
			throw new NullPointerException();
		}
		return temp.get(temp.size()-1);
	}

	@Override
	public Object peekLast() 
	{
		if(isEmpty())
		{
			return null;
		}
		return temp.get(temp.size()-1);
	}

	@Override
	public Object pollFirst() 
	{
		if(isEmpty())
		{
			return null;
		}
		Object retVal = temp.remove(0);
		return retVal;
	}

	@Override
	public Object pollLast() 
	{
		if(isEmpty())
		{
			return null;
		}
		Object retVal = remove(temp.size()-1);
		return retVal;
	}

	@Override
	public Object getFirst() 
	{
		if(isEmpty())
		{
			throw new NullPointerException();
		}
		return temp.get(0);
	}

	@Override
	public Object peekFirst() 
	{
		if(isEmpty())
		{
			return null;
		}
		return temp.get(head);
	}



	@Override
	public boolean removeFirstOccurrence(Object o) 
	{		
		if(!contains(o))
		{
			return false;
		}
		return temp.remove(o);
	}

	@Override
	public boolean removeLastOccurrence(Object o) 
	{
		if(!contains(o))
		{
			return false;
		}
		int i = temp.lastIndexOf(o);
		temp.remove(i);
		return true;
	}

	@Override
	public boolean offer(Object e) 
	{
		return offerLast(e);
	}

	@Override
	public Object poll() 
	{
		return pollFirst();
	}

	@Override
	public Object element() 
	{
		return getFirst();
	}

	@Override
	public Object peek() 
	{
		return peekFirst();
	}


	@Override
	public void push(Object e) 
	{
		addFirst(e);
	}

	@Override
	public Iterator descendingIterator() 
	{
		ArrayList<E> rev = new ArrayList<>();
		for(int i = temp.size()-1; i > 0; i--)
		{
			rev.add(temp.get(i));
		}
		return rev.iterator();
	}

}

