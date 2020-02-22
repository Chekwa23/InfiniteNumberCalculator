package infiniteCalculator;

//import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.*;

/**
 * An Amusingly Linked List class implementing List interface having some variations from the normal java LinkedList implementation 
 * @author Lucas Onwuchekwa
 * @param <E>
 */
public class AmusingLinkedList<E> implements List<E>
{
	/**
	 * Pointer vatriable pointing to the head of this list
	 */
	private Node head;
	/**
	 * Pointer vatriable pointing to the tail of this list
	 */
	private Node tail;
	/**
	 * Size variable to keep track of the size of the list
	 */
	private int size;
	
	/**
	 * Inner class creating node in the list
	 * @author Lucas Onwuchekwa
	 */
	public class Node
	{
		/**
		 * Variable holding the data content of this node
		 */
		public E data = null;
		/**
		 * Variable pointing to the next node of this node
		 */
	    public Node next = null;
	    /**
	     * Variable pointing to the previous node of this node
	     */
	    public Node prev = null;

	    /**
	     * Constructor which takes in the data content and previous and next node
	     * @param data
	     * @param prev
	     * @param next
	     */
	    public Node(E data, Node prev, Node next)
	    {
	    	this.data = data;
	        this.next = next;
	        this.prev = prev;
	    }
	   
	    /**
	     * Get method to get the data content of this node
	     * @return data
	     */
	    public E getData()
	    {
		    return data;
	    }
	    /**
	     * Get method to get the next node of this node
	     * @return next
	     */
	    public Node getNext()
	    {
	    	return next;
	    }
	    
	    /**
	     * Get method to get the previous node of this node
	     * @return prev
	     */
	    public Node getPrev()
	    {
	    	return prev;
	    }
	}
	
	public AmusingLinkedList()
	{
		head = null;
		tail = null;
		size = 0;
	}
//------------------------------------------------------------------------------
	
	@Override
	public boolean add(E e)//																				1
	{
		Node last = new Node(e, null, null);
		if(size == 0)
		{
			last = new Node(e, last, last);
			head = last;
			tail = last;
		}
		else if(size%2 == 0)//last node is odd
		{
			tail.next = last;
			last.prev = head.prev;
			last.next = head;
			head.prev = last;
			tail = last;
		}
		else if(size%2 != 0)//last node is even
		{
			tail.next = last;
			last.next = head;
			tail = last;
		}
		size += 1;
		return true;
	}
	
	
	
	@Override
	public boolean addAll(Collection<? extends E> c) //																				N
	{
		for(E temp : c)
		{
			if(temp == null)
			{
				throw new NullPointerException();
			}
			add(temp);
		}
		return true;
	}
	

	@Override
	public void clear() //																				1
	{
		head = null;
		tail = null;
		size = 0;
	}
	
	@Override
	public boolean contains(Object o) //																				N
	{
		Node temp = head;
		if(isEmpty())
		{
			return false;
		}
		if(head.getData() == o)
		{
			return true;
		}
		for(int i = 1; i < size; i++)
		{
			temp = temp.getNext();
			Object x = temp.getData();
			if(o == x)
			{
				return true;
			}
		}
		return false;
	}


	
	@Override
	public E get(int index) //																				N
	{
		if(index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException();
		}
		return (E) getNodeAtIndex(index).getData();
	}
	
	/**
	 * Given the index in the list, 
	 * this method gets the Node object at that index
	 * @param index
	 * @return node
	 */
	public Node getNodeAtIndex(int index)//																				N
	{
		if(index >= size)
		{
			throw new IndexOutOfBoundsException();
		}
		Node node = head;
		if(size != 0)
		{
			if(index == 0)
			{
				return head;
			}
			for(int i = 0; i < index; i++)
			{
				node = node.getNext();
			}
		}
		return node;
	}
	

	
	@Override
	public int indexOf(Object o) //																				N
	{
		Node temp = head;
		if(head.getData() == o)
		{
			return 0;
		}
		int i;
		for(i = 1; i < size; i++)
		{
			temp = temp.getNext();
			if(o==null) { if(temp.getData() == null) return i;}
			else
			if( o.equals(temp.getData()) )
			{
				return i;
			}
		}
		return -1;
	}
	
	@Override
	public boolean isEmpty() //																				1
	{
		return size == 0;
	}

	@Override
	public Iterator<E> iterator() //																				1
	{
		AmusingListIterator temp = new AmusingListIterator();
		return temp;
	}
	
	@Override
	public int lastIndexOf(Object o) //																				N
	{
		if(!contains(o))
		{
			return -1;
		}
		Node temp = head;
		int x = 0;
		if(o == head.getData())
		{
			x = 0;
		}
		for(int i = 1; i < size; i++)
		{
			temp = temp.next;
			if(o == (temp.getData()))
			{
				x = i;
			}
		}
		return x;
	}
	
	@Override
	public ListIterator<E> listIterator() //																				1
	{
		AmusingListIterator temp = new AmusingListIterator();
		return temp;
	}

	@Override
	public ListIterator<E> listIterator(int index) //																				1
	{
		AmusingListIterator temp = new AmusingListIterator(index);
		return temp;
	}
	


	
	@Override
	public E set(int index, E element) //																				N
	{
		E temp = (E) getNodeAtIndex(index).getData();													
		getNodeAtIndex(index).data = element;
		return temp;
	}
	
	@Override
	public int size() //																				1
	{
		return size;
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) //																				1
	{
		
		SubList temp = new SubList(fromIndex, toIndex);
		
		return (List<E>) temp;
	}
	
	/**
	 * An inner class specifically for the sublist method. 
	 * This class references the original list pointing to the 
	 * to and from indexes.
	 * @author Lucas Onwuchekwa
	 */
	public class SubList
	{
		private int from;
		private int to;
		public SubList(int from, int to)
		{
			this.from = from;
			this.to = to;
		}
		
		
	}
	

	
	@Override
	public String toString()
	{
		StringBuilder str = new StringBuilder();
		int temp = 0;
		Node n = null;
		for(int i = 0; i < size; i++)
		{
			str.append(i);
			str.append(" ");
			try
			{
				n = getNodeAtIndex(i).prev;
				temp = indexOf(n.getData());
				str.append(temp);
				str.append(" ");
			}
			catch(Exception e)
			{
				str.append(-1);
				str.append(" ");
			}
			n = getNodeAtIndex(i).next;
			temp = indexOf(n.getData());
			str.append(temp);
			str.append(" ");
			try
			{
				str.append(get(i));
			}
			catch(Exception e)
			{
				str.append("null");
			}
			if(i != size - 1)
			{
				str.append("\n");
			}
		}
		return str.toString();
	}
	

	@Override
	public Object[] toArray() //																				
	{
		Object [] temp = new Object[size];
		for(int i = 0; i < size; i++)
		{
			temp[i] = get(i);
		}
		return temp;
	}
	
	@Override
	public <E> E[] toArray(E[] a) //																				
	{
		if(size > a.length)
		{
			E[] temp  = (E[]) new Object[size];
			for(int i = 0; i < size; i++)
			{
				temp[i] = (E) get(i);
			}
			a = temp;
			return a;
		}
		else
		{
			E[] temp  = (E[]) new Object[a.length];
			for(int i = 0; i < a.length; i++)
			{
				if(i < size)
				{
					temp[i] = (E) get(i);
				}
				else
				{
					temp[i] = null;
				}
			}
			a = temp;
			return a;
		}
		
	}
	
	@Override
	public boolean retainAll(Collection<?> c) //																				
	{
		int count = 0;
		if(c.size() == 0)
		{
			throw new NullPointerException();
		}
		for(int i = 0; i < size; i++)
		{
			if(!c.contains(get(i)))
			{
				remove(i);
				count++;
				i--;
			}
		}
		if(count > 0)
		{
			return true;
		}
		return false;
	}
	
	@Override
	public E remove(int index) //																				
	{
		if(index >= size)
		{
			throw new IndexOutOfBoundsException();
		}
		E removed = get(index);
		if(size-1 == 0)
		{
			clear();
		}
		else
		{
			for(int i = index; i < size-1; i++)
			{
				getNodeAtIndex(i).data = getNodeAtIndex(i+1).getData();
			}
			Node temp = tail.getPrev();
			if(size%2 == 0)
			{
				head.prev = getNodeAtIndex(size-2);
			}
			else
			{
				head.prev = temp;
			}
			tail = getNodeAtIndex(size-2);
			tail.next = head;
			size -= 1;
		}
		return removed;
	}

	@Override
	public boolean remove(Object o) //																				
	{
		if(!contains(o))
		{
			return false;
		}
		int x = indexOf(o);
		for(int i = x; i < size-1; i++)
		{
			getNodeAtIndex(i).data = getNodeAtIndex(i+1).getData();
		}
		Node temp;
		temp = tail.prev;
		size -= 1;
		tail = getNodeAtIndex(size-1);
		tail.next = head;
		if(size%2 == 0)
		{
			head.prev = temp;
		}
		else
		{
			head.prev = getNodeAtIndex(size-1);
		}
		return true;
	}

	@Override
	public boolean removeAll(Collection<?> c) //																				
	{
		int count = 0;
		if(c.size() == 0)
		{
			throw new NullPointerException();
		}
		for(int i = 0; i < this.size; i++)
		{
			if(this.get(i) == null)
			{
				throw new NullPointerException();
			}
			if(c.contains(this.get(i)))
			{
				remove(i);
				count++;
				i--;
			}
		}
		if(count > 0)
		{
			return true;
		}
		return false;
	}

	@Override
	public int hashCode()//																				
	{
		int hashCode = 1;
		for(int i = 0; i < size; i++)
		{
			E temp = this.get(i);
			hashCode = 31*hashCode + (temp == null ? 0:temp.hashCode());
		}
		return hashCode;
	}

	@Override
	public boolean containsAll(Collection<?> c) //																				
	{	
		for(Object temp : c)
		{
			if(!contains(temp))
			{
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean equals(Object o)//																				
	{
		if(o == null || o.getClass() != this.getClass())
		{
			return false;
		}
		AmusingLinkedList<E> temp = new AmusingLinkedList<E>();
		temp = (AmusingLinkedList<E>) o;
		for(int i = 0; i < temp.size; i++)
		{
			if(this.get(i) != temp.get(i))
			{
				return false;
			}
		}
		return true;
	}
	
	@Override
	public boolean addAll(int index, Collection<? extends E> c) //																				N^2
	{
		for(E temp : c)
		{
			add(index,temp);
			index++;
		}
		
		return false;
	}
	
	@Override
	public void add(int index, E element) //																																			
	{
		if(index == size)
		{
			add(element);
		}
		else if(index < 0 || index > size)
		{
			throw new IndexOutOfBoundsException();
		}
		else
		{
//			create a new at the end then push it all to the right from the index of addition.
			Node newEnd = new Node(get(size-1),null,head);
			tail.next = newEnd;
			for(int i = size-1; i > index; i--)
			{
				getNodeAtIndex(i).data = get(i-1);
			}
			getNodeAtIndex(index).data = element;
			
			if(size%2 == 0)
			{
				tail.next.prev = head.prev;
				head.prev = newEnd;
			}
			else
			{
//				really not necessary	
				head.prev = tail;
			}
			tail = newEnd;
			size += 1;
		}
	}
	
//------------------------------------------------------------------------------


	

	


	


	
//	public static void main(String[] args)
//	   {
//		AmusingLinkedList<String> list = new AmusingLinkedList<String>();
//	      list.add("a");
//	      list.add("b");
//	      list.add("c");
//	      list.add("d");
//	      list.add("e");
//	      list.add("f");
//	      list.add(null);
//	      list.add("g");
//	      list.add("h");
//	      
////			AmusingLinkedList<Integer> a = new AmusingLinkedList<Integer>();
////			a.add(0);
////			
////			a.add(1);
////			
////			a.add(2);
////			a.add(3);
////			
////			a.add(4);
////			a.add(5);
////			
////			a.add(6);
////			a.add(null);
//	      System.out.println(list.toString());
////	      AmusingLinkedList<Integer> l2 = new AmusingLinkedList<Integer>(); 
////	        l2.add(1); 
////	        l2.add(2); 
////	        l2.add(3); 
//	        
//	        
////	        ListIterator<String> itr = list.listIterator(3);
////	        while(itr.hasPrevious())
////	        {
////	        	String temp =itr.previous();
////	        	System.out.println(temp);
////	       	}
//	        
////	        ListIterator<String> itr = list.listIterator();
////	        while(itr.hasNext())
////	        {
////	        	String temp =itr.next();
////	        	System.out.println(temp);
////	       	}
//	        
////	        for(int j= 0; j<-3; j++)
////	        	for(int i=0; i<list.size; i++)
////	        		System.out.println(list.get(i));
//	        
////	      list.addAll(0, l2);
////	      Integer[] data = {0, 10, 20, 30, 40};
////			
////			assertTrue(a.addAll(0, Arrays.asList(data)));
////	      for(String s: list) System.out.println(s);
////	      AmusingLinkedList<Integer> l1 = new AmusingLinkedList<Integer>(); 
////	        l1.add(0, 1);  // adds 1 at 0 index 
//// 
////	        l1.add(1, 2);  // adds 2 at 1 index 
////	        System.out.println(l1);  // [1, 2] 
////	  
////	        // Creating another list 
////	        AmusingLinkedList<Integer> l2 = new AmusingLinkedList<Integer>(); 
////	        l2.add(1); 
////	        l2.add(2); 
////	        l2.add(3); 
////	  
////	        // Will add list l2 from 1 index 
////	        l1.addAll(1, l2); 
////	        System.out.println(l1); 
////	  
////	        // Removes element from index 1 
////	        l1.remove(1);      
////	        System.out.println(l1); // [1, 2, 3, 2] 
////	  
////	        // Prints element at index 3 
////	        System.out.println(l1.get(3)); 
////	  
////	        // Replace 0th element with 5 
////	        l1.set(0, 5);    
////	        System.out.println(l1);  
//	      
////	      list.add(1,"f");
//	    //  System.out.println(list.hashCode());
//	      
////	      System.out.println(Character.getNumericValue('+'));
////	      ArrayList<Integer> temp = new ArrayList<>();
////	      temp.add((int) '-');
////	      System.out.println(Character.getNumericValue('-')); 
////	      System.out.println(list.getNodeAtIndex(0).next.getData());
////	      System.out.println(list.getNodeAtIndex(1).next.getData());
////	      System.out.println(list.getNodeAtIndex(2).prev.getData());
////	      System.out.println(list.getNodeAtIndex(3).prev.getData());
////	      System.out.println(list.getNodeAtIndex(4).prev.getData());
////	      list.remove("l");
//	      
////	      System.out.println(list.indexOf("l"));
////	      System.out.println(list.lastIndexOf("l"));
////	      list.clear();
////	      System.out.println(list.isEmpty());
////	      System.out.println(list.set(2, "l"));
////	      System.out.println(list.getNodeAtIndex(2).getData());
////	      Object [] temp = list.toArray();
////	      System.out.println(Arrays.toString(temp));
////	      System.out.println(list.remove("e"));
////	      System.out.println(list.hashCode());	
//	      
////	      System.out.println(((AmusingLinkedList<?>) list.subList(1, 3)).toString());
//	      
//	      
//
//	      
////	      System.out.println(list.getNodeAtIndex(0).prev.next.getData());
////	      System.out.println(list.getNodeAtIndex(1).next.getData());
////	      System.out.println(list.getNodeAtIndex(2).prev.getData());
////-------------------------------------------------------------------------------------------------------------------------------
////		Linkedlist<String> twin = list.copy3();
////	      System.out.println(twin);
////
////	      System.out.println(list.get(0));
//////			System.out.println(list.get(4));   //exception
////
////	      list.addLast("s");
////	      Iterator itr = list.iterator();
////	      while(itr.hasNext())
////	         System.out.print(itr.next() + " ");
////	      System.out.println();
////
////	      for(Object x : list)
////	         System.out.print(x + " ");
////	      System.out.println();
////
////	      list.insertAfter("e", "ee");
////	      System.out.println(list);
////	      System.out.println(list.getLast());
////
////	      list.insertBefore("h", "yy");
////	      System.out.println(list);
////
////	      list.remove("p");
////	      System.out.println(list);
//		}
	
	public class AmusingListIterator implements ListIterator<E>
	{
		/**
		 * Instance variable which indicates the location of the iterator.
		 */
		private Node cursor;
		/**
		 * Instance variable which keeps track of the index of the iterator.
		 */
		private int index;
		
		/**
		 * Constructor initializing the instance variables
		 */
		public AmusingListIterator()
		{
			cursor = head;
			index = 0;
		}
		
		/**
		 * Constructor taking in an index argument
		 * and initializing the instance variable using the argument passed in
		 * @param index
		 */
		public AmusingListIterator(int index) 
		{
			this.index = index;
			if(index == size())
			{
//				cursor = new Node(null, getNodeAtIndex(index-1), null);
				cursor = null;
			}
			else if(index < size())
			{
				cursor = getNodeAtIndex(index);
			}
			else
			{
				cursor = null;
			}
		}
		
		@Override
		public boolean hasNext() 
		{
//			if(cursor.getNext() == head )//|| cursor != null)
//			{
//				return false;
//			}
//			return true;
			return cursor != null;	 
		}

		@Override
		public E next() 
		{	
			if(hasNext())
			{
				E temp = cursor.getData();
				cursor = cursor.getNext();
				index += 1;
				if(index == size())
				{
//					cursor = new Node(null, getNodeAtIndex(index-1), null);
					cursor = null;
				}
				return temp;
			}
			else
			{
				throw new NoSuchElementException();
			}
			
		}
		
		@Override
		public int nextIndex() 
		{
			return index;
		}
		
		@Override
		public E previous() 
		{
			if(index == size())
			{
//				cursor = cursor.getPrev();
				Node temp = head;
				cursor = getNodeAtIndex(size()-1);
				index -= 1;
				return cursor.getData();
			}
			if(hasPrevious())
			{
				if(index%2 == 0)
				{
//					Node temp = head;
//					E temp = cursor.getPrev().getNext().getData();
					Node temp = cursor;
					Node temp1 = cursor.getPrev();
					cursor = temp1.getNext();
//					cursor = cursor.getPrev().getNext();
				}
				else
				{
					cursor = cursor.getNext().getPrev();
				}
				index -= 1;
				
			}
			else
			{
				throw new NoSuchElementException();
			}
			
			return cursor.getData();
		}
		
//		---------------------------------------------------------------------------------------------
		

		
		

		@Override
		public boolean hasPrevious() 
		{
			if (cursor == head)
			{
				return false;
			}
			return true;
		}

		@Override
		public int previousIndex() 
		{
			if(cursor == head)
			{
				return -1;
			}
			return index - 1;
		}

		@Override
		public void remove() 
		{
			throw new UnsupportedOperationException();
		}

		@Override
		public void set(E e) 
		{
			throw new UnsupportedOperationException();
			
		}

		@Override
		public void add(E e) 
		{
			throw new UnsupportedOperationException();
		}
	}
}
