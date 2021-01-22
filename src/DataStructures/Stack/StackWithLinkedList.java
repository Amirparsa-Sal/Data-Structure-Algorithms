package DataStructures.Stack;

import java.util.LinkedList;
import java.util.Iterator;
import java.lang.StringBuilder;

public class StackWithLinkedList<T> implements Iterable<T>{

	private LinkedList<T> list;

	public StackWithLinkedList(){
		list = new LinkedList<>();
	}

	public int size(){
		return list.size();
	}

	public boolean isEmpty(){
		return size() == 0;
	}

	public void push(T key){
		list.addFirst(key);
	}

	public T pop(){
		if(isEmpty())
			throw new RuntimeException("Stack is empty!");
		return list.removeFirst();
	}

	public T peek(){
		if(isEmpty())
			throw new RuntimeException("Stack is empty!");
		return list.peekFirst();
	}

	@Override public String toString(){
		if (size() == 0)
			return "[]";
		if (size() == 1)
			return "Stack(1)[" + peek().toString() + "]";
		StringBuilder sb = new StringBuilder("Stack(" + size() + ")[");
		Iterator<T> it = list.iterator();
		while(it.hasNext()){
			T key = it.next();
			sb.append(key);
			if (it.hasNext())
				sb.append(" ->  ");
		}
		sb.append("]");
		return sb.toString();
	}

	@Override public Iterator<T> iterator(){
		return list.iterator();
	}
}
