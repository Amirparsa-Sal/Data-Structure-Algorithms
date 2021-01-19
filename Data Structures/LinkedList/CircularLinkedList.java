import java.util.Iterator;
import java.lang.StringBuilder;

public class CircularLinkedList<T> implements Iterable<T>{

	private int size;
	private Node<T> head;
	private Node<T> tail;

	public CircularLinkedList(){
		size = 0;
		head = null;
		tail = null;
	}

	public CircularLinkedList(T key){
		this.size = 1;
		head = tail = new Node<T>(key);
		head.prev = tail;
		tail.next = head;
	}

	public int size(){return size;}

	public boolean isEmpty(){return size==0;}

	public void addLast(T key){
		addLast(new Node<T>(tail, key, head));
		++size;
	}

	private void addLast(Node<T> node){
		if (tail==null)
			head = tail = node;
		else{
			tail.next = node;
			tail = tail.next;
		}
	}

	public void addFirst(T key){
		addFirst(new Node<T>(tail ,key, head));
		++size;
	}

	private void addFirst(Node<T> node){
		if (head == null)
			head = tail = node;
		else{
			node.next = head;
			head = node;
		}
	}

	public void insert(int index, T key){
		if (index < 0 || index>size)
			throw new IndexOutOfBoundsException("Invalid index!");
		if (index == 0){
			addFirst(key);
			return;
		}
		if (index == size){
			addLast(key);
			return;
		}
		Node<T> trav = head;
		for (int i = 0; i < index - 1; i++, trav = trav.next);
		Node<T> newNode = new Node<>(trav , key, trav.next);
		trav.next.prev = newNode;
		trav.next = newNode;
		++size;	
	}

	public int indexOf(T key){
		Node<T> trav = head;
		for(int index = 0; trav!=null; trav=trav.next, index++){
			if (key == null){
				if(trav.key == null)
					return index;
			}
			else if(key.equals(trav.key))
				return index;
		}
		return -1;
	}

	public boolean contains(T key){return indexOf(key)!=-1;}

	public boolean remove(T key){
		int index = indexOf(key);
		return removeAt(index);
	}

	public boolean removeAt(int index){
		if (index<0 || index>=size)
			return false;
		if (index == 0){
			removeFirst();
			return true;
		}
		if (index == size-1){
			removeLast();
			return true;
		}
		Node<T> trav = head;
		for(int i = 0; i < index - 1; i++, trav=trav.next);
		trav.next.next.prev = trav;
		trav.next = trav.next.next;
		--size;
		return true;
	}

	public T removeLast(){
		if (size == 0)
			throw new RuntimeException("The list is empty!");
		if (size == 1){
			--size;
			T key = head.key;
			head = tail = null;
			return key;
		}
		T key = tail.key;
		tail  = tail.prev;
		tail.next = head;
		--size;
		return key;
	}

	public T removeFirst(){
		if (size == 0)
			throw new RuntimeException("The list is empty!");
		if (size == 1){
			--size;
			T key = head.key;
			head = tail = null;
			return key;
		}
		T key = head.next.key;
		head = head.next;
		head.prev = tail;
		--size;
		return key;
	}

	public T peekFirst(){
		if (size == 0)
			throw new RuntimeException("The list is empty!");
		return head.key;
	}

	public T peekLast(){
		if (size == 0)
			throw new RuntimeException("The list is empty!");
		return tail.key;
	}

	@Override public String toString(){
		if (size == 0)
			return "LinkedList(0)[]";
		StringBuilder sb = new StringBuilder("LinkedList(" + size + ")[");
		Node<T> trav = head;
		Node<T> trav_next = head.next;
		for (int i = 0; i < size - 1; i++, trav = trav.next, trav_next = trav_next.next)
			sb.append(trav.key + " <-> ");
		return sb.append(trav.key + "]").toString();
	}


	private class Node<T>{

		Node<T> next;
		Node<T> prev;
		T key;

		Node(){
			next = null;
			prev = null;
			key = null;
		}

		Node(Node<T> prev, T key, Node<T> next){
			this.key = key;
			this.prev = prev;
			this.next = next;
		}

		Node(T key, Node<T> next){
			this(null, key, next);
		}

		Node(Node<T> prev, T key){
			this(prev, key, null);
		}

		Node(T key){
			this(null,key,null);
		}

	}

	@Override public Iterator<T> iterator(){
		return new LinkedListIterator<T>(head);
	}

	private class LinkedListIterator<T> implements Iterator<T>{
    	
    	private int index;
    	private Node<T> node;

    	public LinkedListIterator(Node<T> head){
    		index = 0;
    		node = head;
    	}

    	@Override public boolean hasNext() {return index<size;}

    	@Override public T next() {
    		if (index >= size)
    			throw new RuntimeException("Reached end of the list!");
    		T result = node.key;
    		node = node.next;
    		++index;
    		return result;
    	}

    	@Override public void remove() {
    		if(index==0)
    			throw new IllegalStateException();
    		if (index > size)
    			throw new RuntimeException("Reached end of the list!");
    		removeAt(index-1);
    		--index;
    	}
    }

}