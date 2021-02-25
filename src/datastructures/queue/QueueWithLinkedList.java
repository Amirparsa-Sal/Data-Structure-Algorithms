package datastructures.queue;

import java.util.LinkedList;
import java.util.Iterator;
import java.lang.StringBuilder;

public class QueueWithLinkedList<T> implements Iterable<T>{

	private LinkedList<T> list;

	public QueueWithLinkedList(){
		list = new LinkedList<>();
	}

	public int size(){
		return list.size();
	}

	public boolean isEmpty(){
		return size() == 0;
	}

	public void enQueue(T key){
		list.addLast(key);
	}

	public T deQueue(){
		if(isEmpty())
			throw new RuntimeException("Queue is empty!");
		return list.removeFirst();
	}

	public T peek(){
		if(isEmpty())
			throw new RuntimeException("Queue is empty!");
		return list.peekFirst();
	}

	@Override public String toString(){
		if (size() == 0)
			return "[]";
		if (size() == 1)
			return "Queue(1)[" + peek().toString() + "]";
		StringBuilder sb = new StringBuilder("Queue(" + size() + ")[");
		Iterator<T> it = list.iterator();
		while(it.hasNext()){
			T key = it.next();
			sb.append(key);
			if (it.hasNext())
				sb.append(" <- ");
		}
		sb.append("]");
		return sb.toString();
	}

	@Override public Iterator<T> iterator(){
		return list.iterator();
	}
}
